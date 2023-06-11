package ba.tim2.upravljanjefilmovimakartama.Controller;

import ba.tim2.upravljanjefilmovimakartama.Entity.Karta;
import ba.tim2.upravljanjefilmovimakartama.Service.KarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class KartaController {

    @Autowired
    private KarteService karteService;



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

    @GetMapping("/karte/{id}")
    public List<Karta> getKarteById(@PathVariable int id) {
        return karteService.getKarteById(id);
    }

    @DeleteMapping("/obrisiKartu/{id}")
    public ResponseEntity obrisiKartu(@PathVariable int id) {
        return karteService.obrisiKartu(id);
    }


}
