package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.Popust;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopustRepository extends JpaRepository<Popust, Integer> {
    Popust findByID(int id);
}
