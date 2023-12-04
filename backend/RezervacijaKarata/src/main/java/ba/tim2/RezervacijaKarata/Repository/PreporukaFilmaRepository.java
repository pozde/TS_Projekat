package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.PreporukaFilma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreporukaFilmaRepository extends JpaRepository<PreporukaFilma, Integer> {
    PreporukaFilma findByID(int id);
}
