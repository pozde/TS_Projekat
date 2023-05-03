package ba.tim2.preporucivanjesadrzajapogodnosti.Repositories;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Karta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KartaRepository extends JpaRepository<Karta, Integer> {
}
