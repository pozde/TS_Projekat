package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Karte;
import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import ba.tim2.RezervacijaKarata.Service.KarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KarteController {

    @Autowired
    private KarteService service;

    @PostMapping("/dodajKartu")
    public Karte dodajKartu(@RequestBody Karte karta) {
        return service.spasiKartu(karta);
    }

    @PostMapping("/dodajKarte")
    public List<Karte> dodajKarte(@RequestBody List<Karte> karte) {
        return service.spasiKarte(karte);
    }

    @GetMapping("/karte")
    public List<Karte> getSveKarte() {
        return service.getSveKarte();
    }

    @GetMapping("/karte/sjediste/{broj_sale}/{broj_sjedista}")
    public Sjedista getSjedisteByKartaSjediste(@PathVariable int broj_sale, @PathVariable int broj_sjedista) {
        return service.getSjedisteByKarta(broj_sale, broj_sjedista);
    }

    @GetMapping("/karta/{id}")
    public Karte getFilmById(@PathVariable int id) {
        return service.getKartuById(id);
    }

    @DeleteMapping("/deleteKartu/{id}")
    public String obrisiKartu(@PathVariable int id) {
        return service.obrisiKartu(id);
    }


}
