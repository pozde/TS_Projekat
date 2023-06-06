package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KorisnikService {
    List<Korisnik> getSviKorisnici();

    ResponseEntity getKorisnikByID(int id);

    ResponseEntity getKorisnikByEmail(String email);

    ResponseEntity spasiKorisnika(Korisnik korisnik);

    ResponseEntity azurirajKorisnika(int id, Korisnik korisnik);

    ResponseEntity obrisiKorisnika(int id);
}
