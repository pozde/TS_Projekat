package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Karta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KartaService {
    List<Karta> getSveKarte();

    ResponseEntity getKartaByID(int id);

    ResponseEntity spasiKartu(Karta karta);

    ResponseEntity azurirajKartu(int id, Karta karta);

    ResponseEntity obrisiKartu(int id);
}
