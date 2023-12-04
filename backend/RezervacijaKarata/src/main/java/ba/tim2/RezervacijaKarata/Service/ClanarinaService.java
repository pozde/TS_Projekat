package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Clanarina;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClanarinaService {
    List<Clanarina> getSveClanarine();

    ResponseEntity<Clanarina> getClanarinaByID(int id);

    ResponseEntity<Clanarina> spasiClanarinu(Clanarina clanarina);

    ResponseEntity<Clanarina> azurirajClanarinu(int id, Clanarina clanarina);

    ResponseEntity<String> obrisiClanarinu(int id);
}
