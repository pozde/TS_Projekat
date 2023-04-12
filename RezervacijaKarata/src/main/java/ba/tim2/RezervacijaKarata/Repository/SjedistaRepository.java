package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SjedistaRepository extends JpaRepository<Sjedista,Integer> {
    @Query("SELECT sj.sala.id FROM Sjedista sj WHERE sj.id = :sjediste_id")
    int findSalaIdBySjedisteId(@Param("sjediste_id") int id);

    @Query("SELECT sj.sala.brojSale FROM Sjedista sj WHERE sj.sala.brojSale = :broj_sale")
    int findBrojSalePrekoSjedista(@Param("broj_sale") int broj_sale);

    @Query("SELECT sj FROM Sjedista sj WHERE sj.brojSjedista = :broj_sjedista")
    Sjedista findSjedisteByBrojSjedista(@Param("broj_sjedista") int broj);
}
