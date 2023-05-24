package ba.tim2.preporucivanjesadrzajapogodnosti.Controllers;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Film;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmovi")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> sviFilmovi() {
        return filmService.getSviFilmovi();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFilmById(@PathVariable int id) {
        return filmService.getFilmByID(id);
    }

    @PostMapping(value = "/dodaj", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dodajFilm(@Valid @RequestBody Film noviFilm) {
        return filmService.spasiFilm(noviFilm);
    }

    @PutMapping(value = "/azuriraj/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity azurirajFilm(@Valid @RequestBody Film film, @PathVariable int id) {
        return filmService.azurirajFilm(id, film);
    }

    @DeleteMapping(value = "/obrisi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obrisiFilm(@PathVariable int id) {
        return filmService.obrisiFilm(id);
    }
}
