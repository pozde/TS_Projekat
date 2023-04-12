package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalaController {

    @Autowired
    private SalaService service;

    @PostMapping("/dodajSalu")
    public Sala dodajSalu(@RequestBody Sala sala) {
        return service.spasiSalu(sala);
    }

    @PostMapping("/dodajSale")
    public List<Sala> dodajSale(@RequestBody List<Sala> sale) {
        return service.spasiSale(sale);
    }

    @GetMapping("/sala/{brojSale}")
    public Sala getSaluPrekoBrojaSale(@PathVariable int brojSale) {
        return service.getSaluPrekoBrojaSale(brojSale);
    }

    @GetMapping("/sale")
    public List<Sala> getSveSale() {
        return service.getSveSale();
    }

    @DeleteMapping("/deleteSale")
    public String obrisiSveSale() {
        return service.obrisiSveSale();
    }

    @DeleteMapping("/deleteSalu/{id}")
    public String obrisiSalu(@PathVariable int id) {
        return service.obrisiSalu(id);
    }
}
