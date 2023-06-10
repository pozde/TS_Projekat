package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.Karta;
import ba.tim2.RezervacijaKarata.Entity.Sjediste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KartaRepository extends JpaRepository<Karta, Integer> {
    //RecenzijaAplikacija findByName(String name);
    @Query("SELECT k.sjediste FROM Karta k " +
            "WHERE k.sjediste.brojSjedista = :broj_sjedista AND k.sjediste.sala.brojSale = :broj_sale")
    Sjediste postojiLiSjedisteNaKarti(@Param("broj_sjedista") int brojSjedista, @Param("broj_sale") int brojSale);

    Karta findByID(int id);
}
