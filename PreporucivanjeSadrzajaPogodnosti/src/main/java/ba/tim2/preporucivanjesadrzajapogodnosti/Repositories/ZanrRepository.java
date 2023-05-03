package ba.tim2.preporucivanjesadrzajapogodnosti.Repositories;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Zanr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZanrRepository extends JpaRepository<Zanr, Integer> {
}
