package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.PreporukaFilma;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.PreporukaFilmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PreporukaFilmaController {
    @Autowired
    private PreporukaFilmaService preporukaFilmaService;

    @GetMapping(value = "/preporukeFilmova", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PreporukaFilma> sviKorisnici() {
        return preporukaFilmaService.getSvePreporukeFilmova();
    }

    @GetMapping(value = "/preporukaFilma/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPreporukaFilmaById(@PathVariable int id) {
        return preporukaFilmaService.getPreporukaFilmaByID(id);
    }

    @PostMapping(value = "/dodajPreporukuFilma", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajPreporukuFilma(@Valid @RequestBody PreporukaFilma novaPreporukaFilma) {
        return preporukaFilmaService.spasiPreporukuFilma(novaPreporukaFilma);
    }

    @PutMapping(value = "/azurirajPreporukuFilma/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity azurirajPreporukuFilma(@Valid @RequestBody PreporukaFilma preporukaFilma, @PathVariable int id) {
        return preporukaFilmaService.azurirajPreporukuFilma(id, preporukaFilma);
    }

    @DeleteMapping(value = "/obrisiPreporukuFilma/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obrisiPreporukuFilma(@PathVariable int id) {
        return preporukaFilmaService.obrisiPreporukuFilma(id);
    }
}
