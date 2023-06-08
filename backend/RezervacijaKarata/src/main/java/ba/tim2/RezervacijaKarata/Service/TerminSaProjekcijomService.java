package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.TerminSaProjekcijom;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TerminSaProjekcijomService {
    ResponseEntity azuzirajTerminFilma(int id, LocalDateTime noviDatum);

    ResponseEntity obrisiTerminZaFilm(int id);

    List<TerminSaProjekcijom> getSveTermineZaFilm();

    List<LocalDateTime> getTerminPrekoFilma(String nazivFilma);

    ResponseEntity getSalaPrekoTerminaISale(int brojSale, LocalDateTime pocetak);

    int getBrojSalePrekoTermina(int brojSale);

    ResponseEntity spasiProjekciju(TerminSaProjekcijom terminSaProjekcijom);
}
