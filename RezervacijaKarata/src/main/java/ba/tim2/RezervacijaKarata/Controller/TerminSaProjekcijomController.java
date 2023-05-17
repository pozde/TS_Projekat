//package ba.tim2.RezervacijaKarata.Controller;
//
//
//import ba.tim2.RezervacijaKarata.Entity.TerminSaProjekcijom;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//public class TerminSaProjekcijomController {
//    @Autowired
//    private TerminSaProjekcijomService service;
//
//    @PostMapping("/dodajTerminSaProjekcijom")
//    public TerminSaProjekcijom dodajTerminSaProjekcijom(TerminSaProjekcijom terminSaProjekcijom) { return service.spasiProjekciju(terminSaProjekcijom); }
//
//    @GetMapping("/terminiZaFilm/{id}")
//    public List<TerminSaProjekcijom> getSveTermineZaFilm(@PathVariable int id) {
//        return service.getSveTermineZaFilm();
//    }
//
//    @GetMapping("/termin/sala/{brojSale}")
//    public int getBrojSalePrekoTermina(@PathVariable int brojSale) {
//        return service.getBrojSalePrekoTermina(brojSale);
//    }
//
//    @GetMapping("/termin/{film}")
//    public List<LocalDateTime> getTerminPrekoFilma(@PathVariable String film) {
//        return service.getTerminPrekoFilma(film);
//    }
//
////    @GetMapping("/termin/{film}")
////    public Sala getSalaPrekoTerminaISale(@PathVariable String film, ) {
////        return service.getSalaPrekoTerminaISale(brojSale, pocetak);
////    }
//
//    @DeleteMapping("/obrisiTermin/{id}")
//    public String obrisiTerminZaFilm(@PathVariable int id) {
//        return service.obrisiTerminZaFilm(id);
//    }
//    @PutMapping("/updateTerminZaFilm")
//    public String azuzirajTerminFilma(@RequestBody  TerminSaProjekcijom terminSaProjekcijom, @RequestBody LocalDateTime noviDatum) {
//        return service.azuzirajTerminFilma(terminSaProjekcijom, noviDatum);
//    }
//
//}
