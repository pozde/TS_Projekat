package ba.tim2.upravljanjefilmovimakartama.Controller;

import ba.tim2.upravljanjefilmovimakartama.Entity.Film;
import ba.tim2.upravljanjefilmovimakartama.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class FilmController {
    @Autowired
    private FilmService filmService;

    @PostMapping("/dodajFilm")
    public ResponseEntity dodajFilm(@RequestBody Film film) {
        return filmService.spasiFilm(film);
    }


    @GetMapping("/filmovi")
    public List<Film> getSviFilmovi() {
        return filmService.getSviFilmovi();
    }

    @GetMapping("/films")
    public List<Film> getSviFilms() {
        return filmService.getSviFilmovi();
    }

    @GetMapping("/film/{id}")
    public ResponseEntity getFilmById(@PathVariable int id) {
        return filmService.getFilmByID(id);
    }

    @PutMapping("/azurirajFilm")
    public ResponseEntity azuzirajFilm(@PathVariable int id, @RequestBody Film film) {
        return filmService.azurirajFilm(id, film);
    }

    @DeleteMapping("/deleteFilm/{id}")
    public ResponseEntity obrisiFilm(@PathVariable int id) {
        return filmService.obrisiFilm(id);
    }
}
