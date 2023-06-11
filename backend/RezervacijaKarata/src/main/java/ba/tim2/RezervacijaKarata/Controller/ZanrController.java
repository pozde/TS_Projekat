package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Zanr;
import ba.tim2.RezervacijaKarata.Service.ZanrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zanrovi")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ZanrController {
    @Autowired
    private ZanrService zanrService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Zanr> sviZanrovi() {
        return zanrService.getSviZanrovi();
    }

    @PutMapping(value = "/film/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postaviFilmZaZanr(@PathVariable int id, @RequestBody List<Zanr> zanroviZaFilm) {
        return zanrService.postaviFilmZaZanr(id, zanroviZaFilm);
    }
}
