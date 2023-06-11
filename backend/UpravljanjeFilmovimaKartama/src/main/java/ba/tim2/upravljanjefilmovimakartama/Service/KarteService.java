package ba.tim2.upravljanjefilmovimakartama.Service;

import ba.tim2.upravljanjefilmovimakartama.Entity.Karta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KarteService {

    ResponseEntity getKartuById(int id);
    ResponseEntity obrisiKartu(int id);
    ResponseEntity getSjedisteByKarta(int broj_sale, int broj_sjedista);

    List<Karta> getSveKarte();

    List<Karta> getKarteById(int id);
}
