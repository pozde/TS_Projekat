package ba.tim2.upravljanjefilmovimakartama.Repository;

import ba.tim2.upravljanjefilmovimakartama.Entity.Karta;
import ba.tim2.upravljanjefilmovimakartama.Entity.Sjediste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KartaRepository extends JpaRepository<Karta, Integer> {

    @Query("SELECT k.sjediste FROM Karta k " +
            "WHERE k.sjediste.brojSjedista = :broj_sjedista AND k.sjediste.sala.brojSale = :broj_sale")
    Sjediste postojiLiSjedisteNaKarti(@Param("broj_sjedista") int brojSjedista, @Param("broj_sale") int brojSale);

    Karta findByID(int id);
    List<Karta> findKartasByID(int id);
}
