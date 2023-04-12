package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import ba.tim2.RezervacijaKarata.Service.SjedistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SjedistaController {
    @Autowired
    private SjedistaService service;

    @PostMapping("/dodajSjediste")
    public Sjedista dodajSjediste(@RequestBody Sjedista sjediste) {
        return service.spasiSjediste(sjediste);
    }

    @PostMapping("/dodajSjedista")
    public List<Sjedista> dodajSjedista(@RequestBody List<Sjedista> sjedista) {
        return service.spasiSjedista(sjedista);
    }

    @GetMapping("/sjedista")
    public List<Sjedista> getSveSjedista() {
        return service.getSvaSjedista();
    }

    @GetMapping("/sjedista/{id}")
    public int getSalaIdPrekoSjedista(@PathVariable int id) {
        return service.getSalaIdPrekoSjedista(id);
    }

    @GetMapping("/sjedista/sala/{brojSale}")
    public int getBrojSalePrekoSjedista(@PathVariable int brojSale) {
        return service.getBrojSalePrekoSjedista(brojSale);
    }

    @GetMapping("/brojSjedista/{brojSjedista}")
    public Sjedista getSjedistePrekoBrojaSjedista(@PathVariable int brojSjedista) {
        return service.getSjedistePrekoBrojaSjedista(brojSjedista);
    }
    @DeleteMapping("/deleteSjediste/{id}")
    public String obrisiSjediste(@PathVariable int id) {
        return service.obrisiSjediste(id);
    }

    @DeleteMapping("/deleteSjedista")
    public String obrisiSvaSjedista() {
        return service.obrisiSvaSjedista();
    }
}
