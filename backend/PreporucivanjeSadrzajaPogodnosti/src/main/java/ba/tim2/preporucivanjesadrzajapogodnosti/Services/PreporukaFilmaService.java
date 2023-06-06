package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.PreporukaFilma;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PreporukaFilmaService {
    List<PreporukaFilma> getSvePreporukeFilmova();

    ResponseEntity getPreporukaFilmaByID(int id);

    ResponseEntity spasiPreporukuFilma(PreporukaFilma preporukaFilma);

    ResponseEntity azurirajPreporukuFilma(int id, PreporukaFilma preporukaFilma);

    ResponseEntity obrisiPreporukuFilma(int id);
}
