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
@RequestMapping("/zanrovi")
public class ZanrController {
    @Autowired
    private ZanrService zanrService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Zanr> sviZanrovi() {
        return zanrService.getSviZanrovi();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Zanr> getZanrById(@PathVariable int id) {
        return zanrService.getZanrByID(id);
    }

    @PostMapping(value = "/dodaj", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Zanr> dodajZanr(@Valid @RequestBody Zanr noviZanr) {
        return zanrService.spasiZanr(noviZanr);
    }

    @PutMapping(value = "/azuriraj/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Zanr> azurirajZanr(@Valid @RequestBody Zanr zanr, @PathVariable int id) {
        return zanrService.azurirajZanr(id, zanr);
    }

    @DeleteMapping(value = "/obrisi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obrisiZanr(@PathVariable int id) {
        return zanrService.obrisiZanr(id);
    }
}
