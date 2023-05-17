package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import ba.tim2.RezervacijaKarata.Service.KorisnikServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KorisnikController {
    @Autowired
    private KorisnikServiceImpl service;

    @PostMapping(value = "/dodajKorisnika", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajKorisnika(@RequestBody Korisnik korisnik) {
        return service.spasiKorisnika(korisnik);
    }

//    @PostMapping("/dodajKorisnike")
//    public List<Korisnik> dodajKorisnike(@RequestBody List<Korisnik> korisnici) {
//        return service.spasiKorisnike(korisnici);
//    }

    @GetMapping(value = "/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Korisnik> getSviKorisnici() {
        return service.getSviKorisnici();
    }

    @GetMapping(value = "/korisnik/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getKorisnikById(@PathVariable int id) {
        return service.getKorisnikById(id);
    }

    @GetMapping(value = "/korisnik/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getKorisnikByEmail(@PathVariable String email) {
        return service.getKorisnikByEmail(email);
    }

    @PutMapping(value = "/azurirajKorisnika/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity azuzirajKorisnika(@PathVariable int id, @RequestBody Korisnik korisnik) {
        return service.azurirajKorisnika(id, korisnik);
    }

    @DeleteMapping(value = "/obrisiKorisnika/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obrisiKorisnika(@PathVariable int id) {
        return service.obrisiKorisnika(id);
    }
}
