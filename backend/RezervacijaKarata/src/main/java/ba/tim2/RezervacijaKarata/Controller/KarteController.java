package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Karte;
import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import ba.tim2.RezervacijaKarata.Service.KarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KarteController {

    @Autowired
    private KarteService karteService;

    @PostMapping("/dodajKartu")
    public ResponseEntity dodajKartu(@RequestBody Karte karta) {
        return karteService.spasiKartu(karta);
    }

//    @PostMapping("/dodajKarte")
//    public List<Karte> dodajKarte(@RequestBody List<Karte> karte) {
//        return karteService.spasiKarte(karte);
//    }

    @GetMapping("/karte")
    public List<Karte> getSveKarte() {
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
