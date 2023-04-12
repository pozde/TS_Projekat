package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.Karte;
import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KarteRepository extends JpaRepository<Karte, Integer> {
    //RecenzijaAplikacija findByName(String name);
    @Query("SELECT k.sjedista FROM Karte k " +
            "WHERE k.sjedista.brojSjedista = :broj_sjedista AND k.sjedista.sala.brojSale = :broj_sale")
    Sjedista postojiLiSjedisteNaKarti(@Param("broj_sjedista") int brojSjedista, @Param("broj_sale") int brojSale);
}
