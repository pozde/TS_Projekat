package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Zanr;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ZanrService {
    List<Zanr> getSviZanrovi();

    ResponseEntity postaviFilmZaZanr(int id, List<Zanr> zanroviZaFilm);
}
