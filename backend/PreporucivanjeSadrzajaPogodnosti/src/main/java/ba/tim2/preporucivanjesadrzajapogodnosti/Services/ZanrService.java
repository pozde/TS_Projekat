package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Zanr;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ZanrService {
    List<Zanr> getSviZanrovi();

    ResponseEntity<Zanr> getZanrByID(int id);

    ResponseEntity<Zanr> spasiZanr(Zanr Zanr);

    ResponseEntity<Zanr> azurirajZanr(int id, Zanr Zanr);

    ResponseEntity<String> obrisiZanr(int id);
}
