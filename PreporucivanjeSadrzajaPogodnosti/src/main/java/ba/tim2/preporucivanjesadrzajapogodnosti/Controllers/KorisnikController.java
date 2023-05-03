package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping(value = "/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Korisnik> sviKorisnici() {
        return korisnikService.getSviKorisnici();
    }

    @GetMapping(value = "/korisnici/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable int id) {
        return korisnikService.getKorisnikByID(id);
    }

    @GetMapping(value = "/korisnici/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByEmail(@PathVariable String email) {
        return korisnikService.getKorisnikByEmail(email);
    }

    @PostMapping(value = "/korisnik", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajKorisnika(@Valid @RequestBody Korisnik noviKorisnik) {
        return korisnikService.spasiKorisnika(noviKorisnik);
    }

    @PutMapping(value = "/azurirajKorisnika/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajKorisnika(@Valid @RequestBody Korisnik korisnik, @PathVariable int id) {
        return korisnikService.azurirajKorisnika(id, korisnik);
    }

    @DeleteMapping(value = "/obrisiKorisnika/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obrisiKorisnika(@PathVariable int id) {
        return korisnikService.obrisiKorisnika(id);
    }
}
