package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Clanarina;
import ba.tim2.RezervacijaKarata.Service.ClanarinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clanarine")
public class ClanarinaController {
    @Autowired
    private ClanarinaService clanarinaService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Clanarina> sveClanarine() {
        return clanarinaService.getSveClanarine();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clanarina> getClanarinaById(@PathVariable int id) {
        return clanarinaService.getClanarinaByID(id);
    }

    @PostMapping(value = "/dodaj", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clanarina> dodajClanarinu(@Valid @RequestBody Clanarina novaClanarina) {
        return clanarinaService.spasiClanarinu(novaClanarina);
    }

    @PutMapping(value = "/azuriraj/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clanarina> azurirajClanarinu(@Valid @RequestBody Clanarina clanarina, @PathVariable int id) {
        return clanarinaService.azurirajClanarinu(id, clanarina);
    }

    @DeleteMapping(value = "/obrisi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obrisiClanarinu(@PathVariable int id) {
        return clanarinaService.obrisiClanarinu(id);
    }
}
