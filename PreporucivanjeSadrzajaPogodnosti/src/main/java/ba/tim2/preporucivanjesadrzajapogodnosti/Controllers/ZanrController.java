package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Zanr;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.ZanrService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZanrController {
    @Autowired
    private ZanrService zanrService;

    @GetMapping(value = "/zanrovi", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Zanr> sviZanrovi() {
        return zanrService.getSviZanrovi();
    }

    @GetMapping(value = "/zanr/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getZanrById(@PathVariable int id) {
        return zanrService.getZanrByID(id);
    }

    @PostMapping(value = "/dodajZanr", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajZanr(@Valid @RequestBody Zanr noviZanr) {
        return zanrService.spasiZanr(noviZanr);
    }

    @PutMapping(value = "/azurirajZanr/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity azurirajZanr(@Valid @RequestBody Zanr zanr, @PathVariable int id) {
        return zanrService.azurirajZanr(id, zanr);
    }

    @DeleteMapping(value = "/obrisiZanr/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obrisiZanr(@PathVariable int id) {
        return zanrService.obrisiZanr(id);
    }
}
