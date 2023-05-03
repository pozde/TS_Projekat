package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Clanarina;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClanarinaService {
    List<Clanarina> getSveClanarine();
    ResponseEntity getClanarinaByID(int id);
    ResponseEntity spasiClanarinu(Clanarina clanarina);
    ResponseEntity azurirajClanarinu(int id, Clanarina clanarina);
    ResponseEntity obrisiClanarinu(int id);
}
