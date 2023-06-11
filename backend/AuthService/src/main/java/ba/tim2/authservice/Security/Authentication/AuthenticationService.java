package ba.tim2.authservice.Security.Authentication;

import ba.tim2.authservice.Models.Token.Token;
import ba.tim2.authservice.Models.User.Role;
import ba.tim2.authservice.Repositories.TokenRepository;
import ba.tim2.authservice.Models.Token.TokenType;
import ba.tim2.authservice.Models.User.User;
import ba.tim2.authservice.Repositories.UserRepository;
import ba.tim2.authservice.Security.Config.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    private final RestTemplate restTemplate;

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

        var savedUser = userRepository.save(user);

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
        restTemplate.postForObject("http://preporucivanje-sadrzaja-pogodnosti/korisnici/dodaj", headerForRest, User.class);

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
}
