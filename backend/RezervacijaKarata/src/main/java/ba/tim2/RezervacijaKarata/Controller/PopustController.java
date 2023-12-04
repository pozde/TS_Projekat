package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Popust;
import ba.tim2.RezervacijaKarata.Service.PopustService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/popusti")
public class PopustController {
    @Autowired
    private PopustService popustService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Popust> sviPopusti() {
        return popustService.getSviPopusti();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Popust> getPopustById(@PathVariable int id) {
        return popustService.getPopustByID(id);
    }

    @PostMapping(value = "/dodaj", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Popust> dodajPopust(@Valid @RequestBody Popust noviPopust) {
        return popustService.spasiPopust(noviPopust);
    }

    @PutMapping(value = "/azuriraj/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Popust> azurirajPopust(@Valid @RequestBody Popust popust, @PathVariable int id) {
        return popustService.azurirajPopust(id, popust);
    }

    @DeleteMapping(value = "/obrisi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obrisiPopust(@PathVariable int id) {
        return popustService.obrisiPopust(id);
    }
}
