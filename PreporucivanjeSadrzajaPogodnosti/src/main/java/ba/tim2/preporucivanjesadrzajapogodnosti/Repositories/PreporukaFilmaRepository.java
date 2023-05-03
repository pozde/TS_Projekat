package ba.tim2.preporucivanjesadrzajapogodnosti.Repositories;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.PreporukaFilma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreporukaFilmaRepository extends JpaRepository<PreporukaFilma, Integer> {
}
