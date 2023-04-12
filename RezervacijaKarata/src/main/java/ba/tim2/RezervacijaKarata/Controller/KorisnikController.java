package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import ba.tim2.RezervacijaKarata.Service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KorisnikController {
    @Autowired
    private KorisnikService service;

    @PostMapping("/dodajKorisnika")
    public Korisnik dodajKorisnika(@RequestBody Korisnik korisnik) {
        return service.spasiKorisnika(korisnik);
    }

    @PostMapping("/dodajKorisnike")
    public List<Korisnik> dodajKorisnike(@RequestBody List<Korisnik> korisnici) {
        return service.spasiKorisnike(korisnici);
    }

    @GetMapping("/korisnici")
    public List<Korisnik> getSviKorisnici() {
        return service.getSviKorisnici();
    }

    @GetMapping("/korisnik/{id}")
    public Korisnik getKorisnikById(@PathVariable int id) {
        return service.getKorisnikById(id);
    }

    @PutMapping("/updateKorisnik")
    public Korisnik azuzirajKorisnika(@RequestBody Korisnik korisnik) {
        return service.azurirajKorisnika(korisnik);
    }

    @DeleteMapping("/deleteKorisnik/{id}")
    public String obrisiKorisnika(@PathVariable int id) {
        return service.obrisiKorisnika(id);
    }
}
