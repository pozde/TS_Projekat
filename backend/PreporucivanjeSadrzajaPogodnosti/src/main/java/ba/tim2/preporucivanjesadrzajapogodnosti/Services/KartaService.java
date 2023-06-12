package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Karta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KartaService {
    List<Karta> getSveKarte();

    ResponseEntity<Karta> getKartaByID(int id);

    ResponseEntity<Karta> spasiKartu(Karta karta);

    ResponseEntity<Karta> azurirajKartu(int id, Karta karta);

    ResponseEntity<String> obrisiKartu(int id);
}
