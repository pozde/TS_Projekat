package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.PreporukaFilma;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.PreporukaFilmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preporukeFilmova")
public class PreporukaFilmaController {
    @Autowired
    private PreporukaFilmaService preporukaFilmaService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PreporukaFilma> svePreporukeFilmova() {
        return preporukaFilmaService.getSvePreporukeFilmova();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PreporukaFilma> getPreporukaFilmaById(@PathVariable int id) {
        return preporukaFilmaService.getPreporukaFilmaByID(id);
    }

    @PostMapping(value = "/dodaj", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PreporukaFilma> dodajPreporukuFilma(@Valid @RequestBody PreporukaFilma novaPreporukaFilma) {
        return preporukaFilmaService.spasiPreporukuFilma(novaPreporukaFilma);
    }

    @PutMapping(value = "/azuriraj/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PreporukaFilma> azurirajPreporukuFilma(@Valid @RequestBody PreporukaFilma preporukaFilma, @PathVariable int id) {
        return preporukaFilmaService.azurirajPreporukuFilma(id, preporukaFilma);
    }

    @DeleteMapping(value = "/obrisi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obrisiPreporukuFilma(@PathVariable int id) {
        return preporukaFilmaService.obrisiPreporukuFilma(id);
    }
}
