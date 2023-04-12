package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Karte;
import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import ba.tim2.RezervacijaKarata.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KarteService {
    @Autowired
    private KarteRepository karteRepository;

    @Autowired
    private TerminSaProjekcijomRepository terminSaProjekcijomRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private SjedistaRepository sjedistaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
//        var pocetakProjekcije = terminSaProjekcijomRepository.findTerminPrekoImenaFilma("Taxi driver");
//        System.out.println(pocetakProjekcije.get(0));
//        var sjediste = sjedistaRepository.findSjedisteByBrojSjedista(1);
//        //var sala = salaRepository.findBrojSalePrekoSale(1);
//        var sala = terminSaProjekcijomRepository.findSalaZaTerminProjekcije(1, pocetakProjekcije.get(0));
//        System.out.println(sjediste);
//        System.out.println(sala);
//            System.out.println(sjediste);
//            var sjedisteVecPostoji = getSjedisteByKarta(sala.getBrojSale(),sjediste.getBrojSjedista());
//            System.out.println(sjedisteVecPostoji);
//        System.out.println(sjedisteVecPostoji);
//        var terminProjekcije = terminSaProjekcijomRepository.findTerminProjekcije(sala.getBrojSale(),pocetakProjekcije.get(0),"Taxi driver");
//        System.out.println(terminProjekcije);
//        System.out.println(terminProjekcije);
//        var korisnik = korisnikRepository.findById(1).orElse(null);
//            if(sala != null){
//
//            }
//            if (sjedisteVecPostoji == null) {
//                karteRepository.save(new Karte(1, korisnik, sjediste, terminProjekcije));
//                System.out.println("dobaaaaaa");
//            }
           //repository.save(new Karte(1,null,new Sjedista()));
//        repository.save(new RecenzijaAplikacija(5, "Najbolja aplikacija ikad."));
//        repository.save(new RecenzijaAplikacija(1, "Uzas, puno bugova."));
//        repository.save(new RecenzijaAplikacija(3, "Solidna aplikacija, može i bolje."));
    }

    public Karte spasiKartu(Karte karte) { return karteRepository.save(karte); }

    public List<Karte> spasiKarte(List<Karte> karte) {
        return karteRepository.saveAll(karte);
    }

    public List<Karte> getSveKarte() {
        return karteRepository.findAll();
    }

    public Karte getKartuById(int id) {
        return karteRepository.findById(id).orElse(null);
    }

    //public RecenzijaAplikacija getRecenzijaAplikacijaByName(String name) {
    //  return repository.findByName(name);
    //}

    public Sjedista getSjedisteByKarta(int brojSale, int brojSjedista){
        return karteRepository.postojiLiSjedisteNaKarti(brojSale, brojSjedista);
    }
    public String obrisiKartu(int id) {
        karteRepository.deleteById(id);
        return "Karta uklonjena !!" + id;
    }

//    public RecenzijaAplikacija azurirajRecenzijuZaAplikaciju(RecenzijaAplikacija recenzija) {
//        RecenzijaAplikacija existingRecenzijaAplikacija = repository.findById(recenzija.getId()).orElse(null);
//        //existingRecenzijaAplikacija.setName(RecenzijaAplikacija.getName());
//        //existingRecenzijaAplikacija.setPrice(RecenzijaAplikacija.getPrice());
//        //existingRecenzijaAplikacija.setQuantity(RecenzijaAplikacija.getQuantity());
//        return repository.save(existingRecenzijaAplikacija);
//    }
}
