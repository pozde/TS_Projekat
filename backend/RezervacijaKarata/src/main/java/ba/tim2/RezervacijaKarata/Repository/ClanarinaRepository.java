package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.Clanarina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanarinaRepository extends JpaRepository<Clanarina, Integer> {
    Clanarina findByID(int id);
}
