package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import ba.tim2.RezervacijaKarata.Service.SjedistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class SjedistaController {
    @Autowired
    private SjedistaService sjedistaService;

    @PostMapping("/dodajSjediste")
    public ResponseEntity dodajSjediste(@RequestBody Sjedista sjediste) {
        return sjedistaService.spasiSjediste(sjediste);
    }

//    @PostMapping("/dodajSjedista")
//    public List<Sjedista> dodajSjedista(@RequestBody List<Sjedista> sjedista) {
//        return sjedistaService.spasiSjedista(sjedista);
//    }

    @GetMapping("/sjedista")
    public List<Sjedista> getSveSjedista() {
        return sjedistaService.getSvaSjedista();
    }

    @GetMapping("/sjedista/{id}")
    public int getSalaIdPrekoSjedista(@PathVariable int id) {
        return sjedistaService.getSalaIdPrekoSjedista(id);
    }

    @GetMapping("/sjedista/sala/{brojSale}")
    public int getBrojSalePrekoSjedista(@PathVariable int brojSale) {
        return sjedistaService.getBrojSalePrekoSjedista(brojSale);
    }

    @GetMapping("/brojSjedista/{brojSjedista}")
    public ResponseEntity getSjedistePrekoBrojaSjedista(@PathVariable int brojSjedista) {
        return sjedistaService.getSjedistePrekoBrojaSjedista(brojSjedista);
    }
    @DeleteMapping("/deleteSjediste/{id}")
    public ResponseEntity obrisiSjediste(@PathVariable int id) {
        return sjedistaService.obrisiSjediste(id);
    }

    @DeleteMapping("/deleteSjedista")
    public ResponseEntity obrisiSvaSjedista() {
        return sjedistaService.obrisiSvaSjedista();
    }
}
