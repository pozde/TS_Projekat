package ba.tim2.preporucivanjesadrzajapogodnosti.Repositories;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Film findByID(int id);

    @Query("SELECT f FROM Film f WHERE f.nazivFilma  = :naziv_film")
    Film postojiLiFilm(@Param("naziv_film") String nazivFilm);


    @Query("SELECT COUNT(f) > 0 FROM Film f WHERE f.nazivFilma = :naziv_filma")
    boolean postojiLiFilm1(@Param("naziv_filma") String nazivFilma);
}
