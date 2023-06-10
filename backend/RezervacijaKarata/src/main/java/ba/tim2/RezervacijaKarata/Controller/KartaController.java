package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Karta;
import ba.tim2.RezervacijaKarata.Service.KarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class KartaController {

    @Autowired
    private KarteService karteService;

    @PostMapping("/dodajKartu/{korisnik_id}/{film_id}/{sala_id}/{sjediste_id}")
    public ResponseEntity dodajKartu(@PathVariable int korisnik_id, @PathVariable int film_id, @PathVariable int sala_id, @PathVariable int sjediste_id) {
        return karteService.spasiKartu(korisnik_id, film_id, sala_id, sjediste_id);
    }

    @GetMapping("/karte")
    public List<Karta> getSveKarte() {
        return karteService.getSveKarte();
    }

    @GetMapping("/karte/sjediste/{broj_sale}/{broj_sjedista}")
    public ResponseEntity getSjedisteByKartaSjediste(@PathVariable int broj_sale, @PathVariable int broj_sjedista) {
        return karteService.getSjedisteByKarta(broj_sale, broj_sjedista);
    }

    @GetMapping("/karta/{id}")
        public ResponseEntity getFilmById(@PathVariable int id) {
        return karteService.getKartuById(id);
    }

    @DeleteMapping("/obrisiKartu/{id}")
    public ResponseEntity obrisiKartu(@PathVariable int id) {
        return karteService.obrisiKartu(id);
    }


}
