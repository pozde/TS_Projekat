package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Popust;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.PopustService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PopustController {
    @Autowired
    private PopustService popustService;

    @GetMapping(value = "/popusti", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Popust> sviKorisnici() {
        return popustService.getSviPopusti();
    }

    @GetMapping(value = "/popust/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPopustById(@PathVariable int id) {
        return popustService.getPopustByID(id);
    }

    @PostMapping(value = "/dodajPopust", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajPopust(@Valid @RequestBody Popust noviPopust) {
        return popustService.spasiPopust(noviPopust);
    }

    @PutMapping(value = "/azurirajPopust/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity azurirajPopust(@Valid @RequestBody Popust popust, @PathVariable int id) {
        return popustService.azurirajPopust(id, popust);
    }

    @DeleteMapping(value = "/obrisiPopust/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obrisiPopust(@PathVariable int id) {
        return popustService.obrisiPopust(id);
    }
}
