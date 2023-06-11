package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.Sjediste;
import ba.tim2.RezervacijaKarata.Service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping("/dodajSalu")
    public ResponseEntity dodajSalu(@RequestBody Sala sala) {
        return salaService.spasiSalu(sala);
    }

//    @PostMapping("/dodajSale")
//    public List<Sala> dodajSale(@RequestBody List<Sala> sale) {
//        return salaService.spasiSale(sale);
//    }

    @GetMapping("/sala/{brojSale}")
    public ResponseEntity getSaluPrekoBrojaSale(@PathVariable int brojSale) {
        return salaService.getSaluPrekoBrojaSale(brojSale);
    }

    @GetMapping("/sale")
    public List<Sala> getSveSale() {
        return salaService.getSveSale();
    }

    @DeleteMapping("/deleteSale")
    public ResponseEntity obrisiSveSale() {
        return salaService.obrisiSveSale();
    }

    @DeleteMapping("/deleteSalu/{id}")
    public ResponseEntity obrisiSalu(@PathVariable int id) {
        return salaService.obrisiSalu(id);
    }

    @PutMapping(value = "/sale/film/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postaviFilmZaSalu(@PathVariable int id, @RequestBody List<Sala> saleZaFilm) {
        return salaService.postaviFilmZaSalu(id, saleZaFilm);
    }

    @PostMapping("/dodajSjediste/{sala_id}")
    public ResponseEntity dodajSjedisteZaSalu(@PathVariable int sala_id, @RequestBody Sjediste sjediste) {
        return salaService.dodajSjedisteZaSalu(sala_id, sjediste);
    }
}
