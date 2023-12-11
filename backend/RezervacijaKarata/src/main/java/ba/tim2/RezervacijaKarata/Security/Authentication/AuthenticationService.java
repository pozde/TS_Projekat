package ba.tim2.RezervacijaKarata.Security.Authentication;

import ba.tim2.RezervacijaKarata.Entity.Auth.Role;
import ba.tim2.RezervacijaKarata.Entity.Auth.Token;
import ba.tim2.RezervacijaKarata.Entity.Auth.TokenType;
import ba.tim2.RezervacijaKarata.Entity.Auth.User;
import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.Repository.Auth.TokenRepository;
import ba.tim2.RezervacijaKarata.Repository.Auth.UserRepository;
import ba.tim2.RezervacijaKarata.Repository.KorisnikRepository;
import ba.tim2.RezervacijaKarata.Security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .ime(request.getIme())
                .prezime(request.getPrezime())
                .datumRodjenja(request.getDatumRodjenja())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .brojTelefona(request.getBrojTelefona())
                .spol(request.getSpol())
                .role(request.getRole())
                .build();

        var korisnik = new Korisnik();
        korisnik.setIme(request.getIme());
        korisnik.setPrezime(request.getPrezime());
        korisnik.setSpol(request.getSpol());
        korisnik.setDatumRodjenja(request.getDatumRodjenja());
        korisnik.setBrojTelefona(request.getBrojTelefona());
        korisnik.setEmail(request.getEmail());

        var savedUser = userRepository.save(user);
        var saveKorisnik = korisnikRepository.save(korisnik);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(savedUser, jwtToken);

        var restUser = new User();
        restUser.setIme(request.getIme());
        restUser.setPrezime(request.getPrezime());
        restUser.setDatumRodjenja(request.getDatumRodjenja());
        restUser.setBrojTelefona(request.getBrojTelefona());
        restUser.setEmail(request.getEmail());
        restUser.setRole(Role.USER);
        restUser.setSpol(request.getSpol());

        var token = tokenRepository.findAllValidTokenByUser(user.getID()).get(0).token;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<User> headerForRest = new HttpEntity<>(restUser, headers);
        //restTemplate.postForObject("http://preporucivanje-sadrzaja-pogodnosti/korisnici/dodaj", headerForRest, User.class);
        //restTemplate.postForObject("http://rezervacija-karata/dodajKorisnika", headerForRest, User.class);

        //GrpcClient.log(user.getId(),"AuthService","register","Success");

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        //GrpcClient.log(user.getId(),"AuthService","authenticate","Success");

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getID());

        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public ResponseEntity<String> resetPassword(String email, String oldPassword, String newPassword) {
        try {
            var user = userRepository.findByEmail(email).orElseThrow(() -> new NePostojiException("User not found"));

            if (!passwordEncoder.matches(oldPassword, user.getPassword()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong current password!");

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok("Password reset successfully");
        } catch (NePostojiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
