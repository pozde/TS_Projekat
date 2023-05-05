package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Film;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping(value = "/filmovi", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> sviFilmovi() {
        return filmService.getSviFilmovi();
    }

    @GetMapping(value = "/film/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFilmById(@PathVariable int id) {
        return filmService.getFilmByID(id);
    }

    @PostMapping(value = "/dodajFilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajFilm(@Valid @RequestBody Film noviFilm) {
        return filmService.spasiFilm(noviFilm);
    }

    @PutMapping(value = "/azurirajFilm/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity azurirajFilm(@Valid @RequestBody Film film, @PathVariable int id) {
        return filmService.azurirajFilm(id, film);
    }

    @DeleteMapping(value = "/obrisiFilm/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obrisiFilm(@PathVariable int id) {
        return filmService.obrisiFilm(id);
    }
}
