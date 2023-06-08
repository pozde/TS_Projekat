package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    //Korisnik findByName(String name);
    Korisnik findByID(int id);
    Korisnik findByEmail(String email);
    boolean existsByEmail(String email);

    Korisnik deleteByEmail(String email);
}
