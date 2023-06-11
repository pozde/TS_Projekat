package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.ErrorHandling.VecPostojiException;
import ba.tim2.RezervacijaKarata.Repository.KorisnikRepository;
import ba.tim2.RezervacijaKarata.grpc.GrpcClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private RestTemplate restTemplate;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
    //    repository.save(new Korisnik("Benjamin", "Pasic", new Date(2000, Calendar.FEBRUARY,24),"bpasic1@etf.unsa.ba","062623259","M"));
    }

    @Override
    public ResponseEntity getKorisnikByEmail(String email) {
        if (korisnikRepository.existsByEmail(email)) {
            GrpcClient.log("Korisnik", "Get /korisnik/email/{email}", "Success");
            return new ResponseEntity(korisnikRepository.findByEmail(email), HttpStatus.OK);
        } else {
            GrpcClient.log("Korisnik", "Get /korisnik/email/{email}", "Fail");
            throw new NePostojiException("Korisnik sa email-om " + email + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity getKorisnikById(int id) {
        if (korisnikRepository.existsById(id)) {
            GrpcClient.log("Korisnik", "Get /korisnik/{id}", "Success");
            return new ResponseEntity(korisnikRepository.findById(id), HttpStatus.OK);
        }
        else {
            GrpcClient.log("Korisnik", "Get /korisnik/{id}", "Fail");
            throw new NePostojiException("Korisnik sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiKorisnika(Korisnik korisnik) {
        List<Korisnik> sviKorisnici = korisnikRepository.findAll();
        for (int i = 0; i < sviKorisnici.size(); i++) {
            Korisnik k = sviKorisnici.get(i);
            if (korisnik.getEmail().equals(k.getEmail())) {
                GrpcClient.log("Korisnik", "Create /dodajKorisnika", "Fail");
                throw new VecPostojiException("Korisnik sa tim email-om već postoji!");
            }
        }
        korisnikRepository.save(korisnik);
        GrpcClient.log("Korisnik", "Create /dodajKorisnika", "Success");
        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Korisnik je uspješno dodan!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Korisnik> request = new HttpEntity<>(korisnik, headers);
        //restTemplate.postForObject("http://preporucivanje-sadrzaja-pogodnosti/korisnici/dodaj", request, Korisnik.class);
        return new ResponseEntity(korisnik, HttpStatus.CREATED);
    }

//    public List<Korisnik> spasiKorisnike(List<Korisnik> korisnici) {
//        return korisnikRepository.saveAll(korisnici);
//    }

    @Override
    public List<Korisnik> getSviKorisnici() {
        GrpcClient.log("Korisnik", "Get /korisnici", "Success");
        return korisnikRepository.findAll();
    }

    //public Korisnik getKorisnikByName(String name) {
    //  return repository.findByName(name);
    //}

    @Override
    public ResponseEntity obrisiKorisnika(int id) {
        if(korisnikRepository.existsById(id)){
            JSONObject objekat = new JSONObject();
            GrpcClient.log("Korisnik", "Delete /obrisiKorisnika/{id}", "Success");
            korisnikRepository.deleteById(id);
            try {
                objekat.put("message", "Korisnik je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8080/obrisiKorisnika/" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        }
        else {
            GrpcClient.log("Korisnik", "Delete /obrisiKorisnika/{id}", "Fail");
            throw new NePostojiException("Korisnik sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity obrisiKorisnikaPrekoMaila(String email){
        if(korisnikRepository.existsByEmail(email)){
            JSONObject objekat = new JSONObject();
            GrpcClient.log("Korisnik", "Delete /obrisiKorisnika/{email}", "Success");
            korisnikRepository.deleteByEmail(email);
            try {
                objekat.put("message", "Korisnik je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            restTemplate.delete("http://preporucivanje-sadrzaja-pogodnosti/korisnici/obrisiKorisnika/" + email);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        }
        else {
            GrpcClient.log("Korisnik", "Delete /obrisiKorisnika/{email}", "Fail");
            throw new NePostojiException("Korisnik sa email-om " + email + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity azurirajKorisnika(int id, Korisnik korisnik) {
        Korisnik k = korisnikRepository.findByID(id);
        JSONObject objekat = new JSONObject();
        if (k == null || !korisnikRepository.existsById(id)) {
            GrpcClient.log("Korisnik", "Update /azurirajKorisnika/{id}", "Fail");
            throw new NePostojiException("Korisnik sa id-em " + id + " ne postoji!");
        }
        List<Korisnik> sviKorisnici = korisnikRepository.findAll();
        for (int i = 0; i < sviKorisnici.size(); i++) {
            Korisnik korisnik1 = sviKorisnici.get(i);
            if (k.getID() != korisnik1.getID() && korisnik1.getEmail().equals(korisnik.getEmail())) {
                GrpcClient.log("Korisnik", "Update /azurirajKorisnika/{id}", "Fail");
                throw new VecPostojiException("Korisnik sa tim email-om već postoji!");
            }
        }

        if (!korisnik.getIme().isEmpty()) {
            k.setIme(korisnik.getIme());
        }
        if (!korisnik.getPrezime().isEmpty()) {
            k.setPrezime(korisnik.getPrezime());
        }
        if (korisnik.getEmail() != null && !korisnik.getEmail().isEmpty()) {
            k.setEmail(korisnik.getEmail());
        }
        if (!korisnik.getBrojTelefona().isEmpty()) {
            k.setBrojTelefona(korisnik.getBrojTelefona());
        }
        if (!korisnik.getSpol().isEmpty()) {
            k.setSpol(korisnik.getSpol());
        }
        if (korisnik.getDatumRodjenja() != null) {
            k.setDatumRodjenja(korisnik.getDatumRodjenja());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Korisnik> request = new HttpEntity<>(korisnik, httpHeaders);
        //restTemplate.put("http://localhost:8080/azurirajKorisnika/" + id, request);
        GrpcClient.log("Korisnik", "Update /azurirajKorisnika/{id}", "Success");
        korisnikRepository.save(k);
        try {
            objekat.put("message", "Korisnik je uspješno ažuriran!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }
}
