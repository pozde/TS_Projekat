package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Clanarina;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClanarinaService {
    List<Clanarina> getSveClanarine();

    ResponseEntity<Clanarina> getClanarinaByID(int id);

    ResponseEntity<Clanarina> spasiClanarinu(Clanarina clanarina);

    ResponseEntity<Clanarina> azurirajClanarinu(int id, Clanarina clanarina);

    ResponseEntity<String> obrisiClanarinu(int id);
}
