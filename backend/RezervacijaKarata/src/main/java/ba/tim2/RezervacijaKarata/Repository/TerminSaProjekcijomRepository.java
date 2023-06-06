package ba.tim2.RezervacijaKarata.Repository;

import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.TerminSaProjekcijom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TerminSaProjekcijomRepository extends JpaRepository<TerminSaProjekcijom,Integer> {
    @Query("SELECT t.sala.brojSale FROM TerminSaProjekcijom t WHERE t.sala.brojSale = :broj_sale")
    int findBrojSalePrekoTermina(@Param("broj_sale") int broj_sale);

    @Query("SELECT t.pocetakProjekcije FROM TerminSaProjekcijom t WHERE t.film.nazivFilma = :naziv_filma")
    List<LocalDateTime> findTerminPrekoImenaFilma(@Param("naziv_filma") String naziv_filma);

    //Trazi se preko broja sale i termina projekcije
    @Query("SELECT t.sala FROM TerminSaProjekcijom t " +
            "WHERE t.pocetakProjekcije = :poc_termin AND t.sala.brojSale =:br_sala")
    Sala findSalaZaTerminProjekcije(@Param("br_sala") int broj_sale, @Param("poc_termin") LocalDateTime pocetak_projekcije);

    @Query("SELECT t FROM TerminSaProjekcijom t " +
            "WHERE t.pocetakProjekcije = :poc_termin AND t.sala.brojSale =:br_sala " +
            "AND t.film.nazivFilma = :naziv_filma")
    TerminSaProjekcijom findTerminProjekcije(@Param("br_sala") int broj_sale, @Param("poc_termin") LocalDateTime pocetak_projekcije, @Param("naziv_filma") String naziv_filma);
}
