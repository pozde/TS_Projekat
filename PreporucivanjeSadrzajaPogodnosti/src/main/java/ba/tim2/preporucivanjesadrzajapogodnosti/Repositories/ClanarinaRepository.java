package ba.tim2.preporucivanjesadrzajapogodnosti.Repositories;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Clanarina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanarinaRepository extends JpaRepository<Clanarina, Integer> {
    Clanarina findByID(int id);
}
