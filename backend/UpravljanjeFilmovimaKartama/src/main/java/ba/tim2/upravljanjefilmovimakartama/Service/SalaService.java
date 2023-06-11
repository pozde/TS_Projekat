package ba.tim2.upravljanjefilmovimakartama.Service;

import ba.tim2.upravljanjefilmovimakartama.Entity.Sala;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalaService {
    List<Sala> getSveSale();

    ResponseEntity getSaluPrekoBrojaSale(int brojSale);

    ResponseEntity spasiSalu(Sala sala);

    ResponseEntity obrisiSalu(int id);

    ResponseEntity obrisiSveSale();

    ResponseEntity postaviFilmZaSalu(int id, List<Sala> saleZaFilm);
}
