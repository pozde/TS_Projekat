package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Film;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmService {
    List<Film> getSviFilmovi();

    ResponseEntity<Film> getFilmByID(int id);

    ResponseEntity<Film> spasiFilm(Film film);

    ResponseEntity<Film> azurirajFilm(int id, Film film);

    ResponseEntity<String> obrisiFilm(int id);
}
