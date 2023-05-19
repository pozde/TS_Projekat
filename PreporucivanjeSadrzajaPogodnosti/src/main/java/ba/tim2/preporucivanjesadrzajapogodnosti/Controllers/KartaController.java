package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Karta;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.KartaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KartaController {
    @Autowired
    private KartaService kartaService;

    @GetMapping(value = "/karte", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Karta> sveKarte() {
        return kartaService.getSveKarte();
    }

    @GetMapping(value = "/karte/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getKartaById(@PathVariable int id) {
        return kartaService.getKartaByID(id);
    }

    @PostMapping(value = "/dodajKartu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajKartu(@Valid @RequestBody Karta novaKarta) {
        return kartaService.spasiKartu(novaKarta);
    }

    @PutMapping(value = "/azurirajKartu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity azurirajKartu(@Valid @RequestBody Karta karta, @PathVariable int id) {
        return kartaService.azurirajKartu(id, karta);
    }

    @DeleteMapping(value = "/obrisiKartu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obrisiKartu(@PathVariable int id) {
        return kartaService.obrisiKartu(id);
    }
}
