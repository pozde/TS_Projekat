package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Popust;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PopustService {
    List<Popust> getSviPopusti();

    ResponseEntity getPopustByID(int id);

    ResponseEntity spasiPopust(Popust popust);

    ResponseEntity azurirajPopust(int id, Popust popust);

    ResponseEntity obrisiPopust(int id);
}
