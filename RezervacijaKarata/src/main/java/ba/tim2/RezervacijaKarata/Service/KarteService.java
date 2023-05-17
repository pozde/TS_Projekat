package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Karte;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface KarteService {

    ResponseEntity getKartuById(int id);
    ResponseEntity obrisiKartu(int id);
    ResponseEntity getSjedisteByKarta(int broj_sale, int broj_sjedista);
    ResponseEntity spasiKartu(Karte karta);
    List<Karte> getSveKarte();

}
