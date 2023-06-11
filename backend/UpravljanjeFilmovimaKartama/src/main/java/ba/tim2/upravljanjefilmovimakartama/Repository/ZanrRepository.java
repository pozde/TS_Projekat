package ba.tim2.upravljanjefilmovimakartama.Repository;

import ba.tim2.upravljanjefilmovimakartama.Entity.Zanr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZanrRepository extends JpaRepository<Zanr, Integer> {
}
