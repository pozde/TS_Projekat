package ba.tim2.RezervacijaKarata.Controller;


import ba.tim2.RezervacijaKarata.Entity.TerminSaProjekcijom;
import ba.tim2.RezervacijaKarata.Service.TerminSaProjekcijomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class TerminSaProjekcijomController {
    @Autowired
    private TerminSaProjekcijomService terminSaProjekcijomService;

    @PostMapping("/dodajTerminSaProjekcijom")
    public ResponseEntity dodajTerminSaProjekcijom( @RequestBody TerminSaProjekcijom terminSaProjekcijom) {
        return terminSaProjekcijomService.spasiProjekciju(terminSaProjekcijom); }

    @GetMapping("/terminiZaFilm/{id}")
    public List<TerminSaProjekcijom> getSveTermineZaFilm(@PathVariable int id) {
        return terminSaProjekcijomService.getSveTermineZaFilm();
    }

    @GetMapping("/termin/sala/{brojSale}")
    public int getBrojSalePrekoTermina(@PathVariable int brojSale) {
        return terminSaProjekcijomService.getBrojSalePrekoTermina(brojSale);
    }

    @GetMapping("/termin/{film}")
    public List<LocalDateTime> getTerminPrekoFilma(@PathVariable String film) {
        return terminSaProjekcijomService.getTerminPrekoFilma(film);
    }

//    @GetMapping("/termin/{brojSale}")
//    public ResponseEntity getSalaPrekoTerminaISale(@PathVariable int brojSale, LocalDateTime pocetak) {
//        return terminSaProjekcijomService.getSalaPrekoTerminaISale(brojSale, pocetak);
//    }

    @DeleteMapping("/obrisiTermin/{id}")
    public ResponseEntity obrisiTerminZaFilm(@PathVariable int id) {
        return terminSaProjekcijomService.obrisiTerminZaFilm(id);
    }
    @PutMapping("/updateTerminZaFilm/{id}")
    public ResponseEntity azuzirajTerminFilma(@PathVariable int id, @RequestBody LocalDateTime noviDatum) {
        return terminSaProjekcijomService.azuzirajTerminFilma(id, noviDatum);
    }

}
