package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.PreporukaFilma;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PreporukaFilmaService {
    List<PreporukaFilma> getSvePreporukeFilmova();

    ResponseEntity<PreporukaFilma> getPreporukaFilmaByID(int id);

    ResponseEntity<PreporukaFilma> spasiPreporukuFilma(PreporukaFilma preporukaFilma);

    ResponseEntity<PreporukaFilma> azurirajPreporukuFilma(int id, PreporukaFilma preporukaFilma);

    ResponseEntity<String> obrisiPreporukuFilma(int id);
}
