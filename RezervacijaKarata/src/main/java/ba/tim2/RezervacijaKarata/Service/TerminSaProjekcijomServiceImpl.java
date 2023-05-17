package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.TerminSaProjekcijom;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import ba.tim2.RezervacijaKarata.Repository.SalaRepository;
import ba.tim2.RezervacijaKarata.Repository.TerminSaProjekcijomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

@Service
public class TerminSaProjekcijomServiceImpl implements TerminSaProjekcijomService {
    @Autowired
    private TerminSaProjekcijomRepository terminSaProjekcijomRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private SalaRepository salaRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
//        var pronadjiSalu = salaRepository.findById(14).orElse(null);
//        var pronadjiFilm = filmRepository.findById(13).orElse(null);
//        if(pronadjiFilm != null && pronadjiSalu != null){
//            terminSaProjekcijomRepository.save(new TerminSaProjekcijom(LocalDateTime.of(2023, Month.MAY, 1, 15,00), pronadjiSalu, pronadjiFilm));
//        }
//        repository.save(new Film("Inception"));
//        repository.save(new Film("Shutter Island"));
//        repository.save(new Film("Fast and Furious"));
    }

    public TerminSaProjekcijom spasiProjekciju(TerminSaProjekcijom terminSaProjekcijom) {
        return terminSaProjekcijomRepository.save(terminSaProjekcijom);
    }

    public int getBrojSalePrekoTermina(int brojSale) {
        return terminSaProjekcijomRepository.findBrojSalePrekoTermina(brojSale);
    }

    public Sala getSalaPrekoTerminaISale(int brojSale, LocalDateTime pocetak) {
        return terminSaProjekcijomRepository.findSalaZaTerminProjekcije(brojSale, pocetak);
    }

    public TerminSaProjekcijom getTerminProjekcije(int brojSale, LocalDateTime pocetak, String nazivFilma) {
        return terminSaProjekcijomRepository.findTerminProjekcije(brojSale, pocetak, nazivFilma);
    }

    public List<LocalDateTime> getTerminPrekoFilma(String nazivFilma) {
        return terminSaProjekcijomRepository.findTerminPrekoImenaFilma(nazivFilma);
    }

    public List<TerminSaProjekcijom> getSveTermineZaFilm() {
        return terminSaProjekcijomRepository.findAll();
    }

    public String obrisiTerminZaFilm(int id) {
        terminSaProjekcijomRepository.deleteById(id);
        return "Film removed !!" + id;
    }

    public String azuzirajTerminFilma(TerminSaProjekcijom terminSaProjekcijom, LocalDateTime noviDatum) {
//        if(terminSaProjekcijom == null)
//            return "Termin koji treba azurirati ne postoji!";
//        terminPostoji.setTerminProjekcije(noviTermin);
        terminSaProjekcijom.setDatumProjekcije(noviDatum);
        return "Termin filma izmijenjen!";
    }

}
