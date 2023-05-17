package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
}
