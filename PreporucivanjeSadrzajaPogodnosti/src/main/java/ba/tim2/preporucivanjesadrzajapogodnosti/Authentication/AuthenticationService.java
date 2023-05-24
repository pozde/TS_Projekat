package ba.tim2.preporucivanjesadrzajapogodnosti.Authentication;

import ba.tim2.preporucivanjesadrzajapogodnosti.Config.JwtService;
import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Role;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.KorisnikRepository;
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

    public AuthenticationResponse register(RegisterRequest request) {

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(request.getIme());
        korisnik.setPrezime(request.getPrezime());
        korisnik.setEmail(request.getEmail());
        korisnik.setPassword(passwordEncoder.encode(request.getPassword()));
        korisnik.setDatumRodjenja(request.getDatumRodjenja());
        korisnik.setBrojTelefona(request.getBrojTelefona());
        korisnik.setSpol(request.getSpol());
        korisnik.setRole(Role.KORISNIK);

        repository.save(korisnik);

        var jwtToken = jwtService.generateToken(korisnik);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
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
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
