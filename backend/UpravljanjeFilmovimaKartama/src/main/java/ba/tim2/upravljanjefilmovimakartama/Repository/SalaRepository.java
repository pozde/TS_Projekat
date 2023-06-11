package ba.tim2.upravljanjefilmovimakartama.Repository;

import ba.tim2.upravljanjefilmovimakartama.Entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalaRepository extends JpaRepository<Sala, Integer> {
    Sala findByID(int id);

    @Query("SELECT sa FROM Sala sa WHERE sa.brojSale = :broj_sale")
    Sala findBrojSalePrekoSale(@Param("broj_sale") int broj_sale);
}
