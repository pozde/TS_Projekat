package ba.tim2.preporucivanjesadrzajapogodnosti.Authentication;

import ba.tim2.preporucivanjesadrzajapogodnosti.Config.JwtService;
import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Role;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.KorisnikRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.Token.Token;
import ba.tim2.preporucivanjesadrzajapogodnosti.Token.TokenRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.Token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final KorisnikRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(request.getIme());
        korisnik.setPrezime(request.getPrezime());
        korisnik.setEmail(request.getEmail());
        korisnik.setPassword(passwordEncoder.encode(request.getPassword()));
        korisnik.setDatumRodjenja(request.getDatumRodjenja());
        korisnik.setBrojTelefona(request.getBrojTelefona());
        korisnik.setSpol(request.getSpol());
        korisnik.setRole(request.getRole());

        var korisnikSpaseni = repository.save(korisnik);

        var jwtToken = jwtService.generateToken(korisnik);
        spasiKorisnikToken(korisnikSpaseni, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    private void spasiKorisnikToken(Korisnik spaseniKorisnik, String jwtToken) {
        var token = Token.builder()
                .korisnik(spaseniKorisnik)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Korisnik korisnik = repository.findByEmail(request.getEmail());

        if (korisnik == null) {
            throw new NePostojiException("Korisnik sa tim emailom ne postoji!");
        }

        var jwtToken = jwtService.generateToken(korisnik);

        revokeSveKorisnickeTokene(korisnik);
        spasiKorisnikToken(korisnik, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeSveKorisnickeTokene(Korisnik korisnik) {
        var validKorisnikTokens = tokenRepository.nadjiSveTokeneZaKorisnika(korisnik.getID());
        if (validKorisnikTokens.isEmpty())
            return;
        validKorisnikTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validKorisnikTokens);
    }
}
