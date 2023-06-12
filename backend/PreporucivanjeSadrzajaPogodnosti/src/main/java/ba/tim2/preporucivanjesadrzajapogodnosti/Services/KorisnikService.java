package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KorisnikService {
    List<Korisnik> getSviKorisnici();

    ResponseEntity<Korisnik> getKorisnikByID(int id);

    ResponseEntity<Korisnik> getKorisnikByEmail(String email);

    ResponseEntity<Korisnik> spasiKorisnika(Korisnik korisnik);

    ResponseEntity<Korisnik> azurirajKorisnika(int id, Korisnik korisnik);

    ResponseEntity<String> obrisiKorisnika(int id);
}
