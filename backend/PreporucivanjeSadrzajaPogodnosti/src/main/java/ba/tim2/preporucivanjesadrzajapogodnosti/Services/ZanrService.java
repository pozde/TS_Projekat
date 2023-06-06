package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Zanr;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ZanrService {
    List<Zanr> getSviZanrovi();

    ResponseEntity getZanrByID(int id);

    ResponseEntity spasiZanr(Zanr Zanr);

    ResponseEntity azurirajZanr(int id, Zanr Zanr);

    ResponseEntity obrisiZanr(int id);
}
