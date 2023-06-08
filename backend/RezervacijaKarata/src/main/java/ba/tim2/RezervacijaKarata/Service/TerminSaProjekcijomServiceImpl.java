package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.TerminSaProjekcijom;
import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import ba.tim2.RezervacijaKarata.Repository.SalaRepository;
import ba.tim2.RezervacijaKarata.Repository.TerminSaProjekcijomRepository;
import ba.tim2.RezervacijaKarata.grpc.GrpcClient;
import org.apache.coyote.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
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

    public ResponseEntity spasiProjekciju(TerminSaProjekcijom terminSaProjekcijom) {
        return new ResponseEntity<>(terminSaProjekcijomRepository.save(terminSaProjekcijom), HttpStatus.OK);
    }

    public int getBrojSalePrekoTermina(int brojSale) {
        GrpcClient.log("Termin Sa Projekcijom", "GET /termin/sala/{brojSale}", "Success");
        return terminSaProjekcijomRepository.findBrojSalePrekoTermina(brojSale);
    }

    public ResponseEntity getSalaPrekoTerminaISale(int brojSale, LocalDateTime pocetak) {
        Sala sala = terminSaProjekcijomRepository.findSalaZaTerminProjekcije(brojSale, pocetak);
        if(sala == null){
            GrpcClient.log("Termin Sa Projekcijom", "GET /obrisiTermin/{id}", "Fail");
            throw new NePostojiException("Sala za film sa id-em ne postoji!");
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        GrpcClient.log("Termin Sa Projekcijom", "GET /termin/{film}", "Success");
        HttpEntity<Sala> request = new HttpEntity<>(sala, httpHeaders);
        return new ResponseEntity<>(sala, HttpStatus.OK);
    }

//    public TerminSaProjekcijom getTerminProjekcije(int brojSale, LocalDateTime pocetak, String nazivFilma) {
//        TerminSaProjekcijom terminSaProjekcijom = terminSaProjekcijomRepository.findTerminProjekcije(brojSale, pocetak, nazivFilma);
//        if (terminSaProjekcijom == null) {
//            GrpcClient.log("Termin Sa Projekcijom", "Update /updateTerminZaFilm/{id}", "Fail");
//            throw new NePostojiException("Termin ne postoji!");
//        }
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        GrpcClient.log("Termin Sa Projekcijom", "Update /updateTerminZaFilm/{id}", "Success");
//        HttpEntity<TerminSaProjekcijom> request = new HttpEntity<>(terminSaProjekcijom, httpHeaders);
//        return new ResponseEntity<>(terminSaProjekcijom, HttpStatus.OK);
//    }

    public List<LocalDateTime> getTerminPrekoFilma(String nazivFilma) {
        GrpcClient.log("Termin Sa Projekcijom", "GET /terminiZaFilm/{id}", "Success");
        return terminSaProjekcijomRepository.findTerminPrekoImenaFilma(nazivFilma);
    }

    public List<TerminSaProjekcijom> getSveTermineZaFilm() {
        return terminSaProjekcijomRepository.findAll();
    }

    public ResponseEntity obrisiTerminZaFilm(int id) {
        if(terminSaProjekcijomRepository.existsById(id)){
            JSONObject objekat = new JSONObject();
            GrpcClient.log("Termin Sa Projekcijom", "DELETE /obrisiTermin/{id}", "Success");
            terminSaProjekcijomRepository.deleteById(id);
            try {
                objekat.put("message", "Termin za film je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        }
        else {
            GrpcClient.log("Termin Sa Projekcijom", "DELETE /obrisiTermin/{id}", "Fail");
            throw new NePostojiException("Termin za film sa id-em " + id + " ne postoji!");
        }
    }

    public ResponseEntity azuzirajTerminFilma(int id, LocalDateTime noviDatum) {
//        if(terminSaProjekcijom == null)
//            return "Termin koji treba azurirati ne postoji!";
//        terminPostoji.setTerminProjekcije(noviTermin);
        TerminSaProjekcijom terminSaProjekcijom = terminSaProjekcijomRepository.findByID(id);
        if (terminSaProjekcijom == null || !terminSaProjekcijomRepository.existsById(id)) {
            GrpcClient.log("Termin Sa Projekcijom", "UPDATE /updateTerminZaFilm/{id}", "Fail");
            throw new NePostojiException("Termin koji treba azurirati ne postoji!");
        }
        terminSaProjekcijom.setDatumProjekcije(noviDatum);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        GrpcClient.log("Termin Sa Projekcijom", "UPDATE /updateTerminZaFilm/{id}", "Success");
        HttpEntity<TerminSaProjekcijom> request = new HttpEntity<>(terminSaProjekcijom, httpHeaders);
        return new ResponseEntity<>(terminSaProjekcijom, HttpStatus.OK);
    }

}
