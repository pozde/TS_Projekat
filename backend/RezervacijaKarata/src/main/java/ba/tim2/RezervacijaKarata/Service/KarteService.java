package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Karta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KarteService {

    ResponseEntity getKartuById(int id);
    ResponseEntity obrisiKartu(int id);
    ResponseEntity getSjedisteByKarta(int broj_sale, int broj_sjedista);
    ResponseEntity spasiKartu(int korisnik_id, int film_id, int sjediste_id);
    List<Karta> getSveKarte();

}
