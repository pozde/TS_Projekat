package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.KorisnikService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/korisnici")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Korisnik> sviKorisnici() {
        return korisnikService.getSviKorisnici();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getKorisnikById(@PathVariable int id) {
        return korisnikService.getKorisnikByID(id);
    }

    @GetMapping(value = "/mail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getKorisnikByEmail(@PathVariable String email) {
        return korisnikService.getKorisnikByEmail(email);
    }

    @PostMapping(value = "/dodaj", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> dodajKorisnika(@Valid @RequestBody Korisnik noviKorisnik) {
        return korisnikService.spasiKorisnika(noviKorisnik);
    }

    @PutMapping(value = "/azuriraj/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> azurirajKorisnika(@Valid @RequestBody Korisnik korisnik, @PathVariable int id) {
        return korisnikService.azurirajKorisnika(id, korisnik);
    }

    @DeleteMapping(value = "/obrisi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obrisiKorisnika(@PathVariable int id) {
        return korisnikService.obrisiKorisnika(id);
    }
}
