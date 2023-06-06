package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Karte;
import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.Repository.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KarteServiceImpl implements KarteService{
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

    @Override
    public ResponseEntity spasiKartu(Karte karta) {
        karteRepository.save(karta);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Karta je uspješno dodan!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Karte> request = new HttpEntity<>(karta, headers);
        //restTemplate.postForObject("http:://localhost:8080/dodajFilm", request, Film.class);
        return new ResponseEntity(karta, HttpStatus.CREATED);
    }

//    public List<Karte> spasiKarte(List<Karte> karte) {
//        return karteRepository.saveAll(karte);
//    }

    @Override
    public List<Karte> getSveKarte() {
        return karteRepository.findAll();
    }

    @Override
    public ResponseEntity getKartuById(int id) {
        if (karteRepository.existsById(id)) {
            return new ResponseEntity(karteRepository.findByID(id), HttpStatus.OK);
        } else {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }

    //public RecenzijaAplikacija getRecenzijaAplikacijaByName(String name) {
    //  return repository.findByName(name);
    //}

    @Override
    public ResponseEntity getSjedisteByKarta(int brojSale, int brojSjedista){       //OVO vidi za if
        return new ResponseEntity(karteRepository.postojiLiSjedisteNaKarti(brojSale, brojSjedista), HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiKartu(int id) {
        if (karteRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            karteRepository.deleteById(id);
            try {
                objekat.put("message", "Karta je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8080/obrisiKartu" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Karta sa id-em " + id + " ne postoji!");
        }
    }

//    public RecenzijaAplikacija azurirajRecenzijuZaAplikaciju(RecenzijaAplikacija recenzija) {
//        RecenzijaAplikacija existingRecenzijaAplikacija = repository.findById(recenzija.getId()).orElse(null);
//        //existingRecenzijaAplikacija.setName(RecenzijaAplikacija.getName());
//        //existingRecenzijaAplikacija.setPrice(RecenzijaAplikacija.getPrice());
//        //existingRecenzijaAplikacija.setQuantity(RecenzijaAplikacija.getQuantity());
//        return repository.save(existingRecenzijaAplikacija);
//    }
}
