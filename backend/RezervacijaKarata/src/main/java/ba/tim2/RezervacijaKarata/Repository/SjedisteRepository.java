package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.Sjediste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SjedisteRepository extends JpaRepository<Sjediste, Integer> {
    Sjediste findByID(int id);

    @Query("SELECT sj.sala.ID FROM Sjediste sj WHERE sj.ID = :sjediste_id")
    int findSalaIdBySjedisteId(@Param("sjediste_id") int id);

    @Query("SELECT sj.sala.brojSale FROM Sjediste sj WHERE sj.sala.brojSale = :broj_sale")
    int findBrojSalePrekoSjedista(@Param("broj_sale") int broj_sale);

    @Query("SELECT DISTINCT sj FROM Sjediste sj WHERE sj.brojSjedista = :broj_sjedista")
    Sjediste findSjedisteByBrojSjedista(@Param("broj_sjedista") int broj);
}
