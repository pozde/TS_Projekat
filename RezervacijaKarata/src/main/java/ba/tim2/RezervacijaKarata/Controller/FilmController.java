package ba.tim2.RezervacijaKarata.Controller;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {
    @Autowired
    private FilmService service;

    @PostMapping("/dodajFilm")
    public Film dodajFilm(@RequestBody Film film) {
        return service.spasiFilm(film);
    }

    @PostMapping("/dodajFilmove")
    public List<Film> dodajFilmove(@RequestBody List<Film> filmovi) {
        return service.spasiFilmove(filmovi);
    }

    @GetMapping("/filmovi")
    public List<Film> getSviFilmovi() {
        return service.getSviFilmovi();
    }

    @GetMapping("/film/{id}")
    public Film getFilmById(@PathVariable int id) {
        return service.getFilmById(id);
    }

    @PutMapping("/updateFilm")
    public Film azuzirajFilm(@RequestBody Film film) {
        return service.azurirajFilm(film);
    }

    @DeleteMapping("/deleteFilm/{id}")
    public String obrisiFilm(@PathVariable int id) {
        return service.obrisiFilm(id);
    }
}
