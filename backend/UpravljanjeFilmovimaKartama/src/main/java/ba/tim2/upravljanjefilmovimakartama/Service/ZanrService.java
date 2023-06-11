package ba.tim2.upravljanjefilmovimakartama.Service;

import ba.tim2.upravljanjefilmovimakartama.Entity.Zanr;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ZanrService {
    List<Zanr> getSviZanrovi();

    ResponseEntity postaviFilmZaZanr(int id, List<Zanr> zanroviZaFilm);
}
