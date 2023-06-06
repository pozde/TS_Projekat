package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.VecPostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.KorisnikRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.grpc.GrpcClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Korisnik> getSviKorisnici() {
        GrpcClient.log("Korisnik", "GET /korisnici/", "SUCCESS");
        return korisnikRepository.findAll();
    }

    @Override
    public ResponseEntity getKorisnikByID(int id) {
        if (korisnikRepository.existsById(id)) {
            GrpcClient.log("Korisnik", "GET /korisnici/{id}", "SUCCESS");
            return new ResponseEntity(korisnikRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log("Korisnik", "GET /korisnici/{id}", "FAIL");
            throw new NePostojiException("Korisnik sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity getKorisnikByEmail(String email) {
        if (korisnikRepository.existsByEmail(email)) {
            GrpcClient.log("Korisnik", "GET /korisnici/{email}", "SUCCESS");
            return new ResponseEntity(korisnikRepository.findByEmail(email), HttpStatus.OK);
        } else {
            GrpcClient.log("Korisnik", "GET /korisnici/{email}", "FAIL");
            throw new NePostojiException("Korisnik sa email-om " + email + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiKorisnika(Korisnik korisnik) {
        List<Korisnik> sviKorisnici = korisnikRepository.findAll();
        for (int i = 0; i < sviKorisnici.size(); i++) {
            Korisnik k = sviKorisnici.get(i);
            if (korisnik.getEmail().equals(k.getEmail())) {
                throw new VecPostojiException("Korisnik sa tim email-om već postoji!");
            }
        }
        korisnikRepository.save(korisnik);
        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Korisnik je uspješno dodan!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Korisnik> request = new HttpEntity<>(korisnik, headers);
        //restTemplate.postForObject("http://localhost:8081/dodajKorisnika", request, Korisnik.class);
        GrpcClient.log("Korisnik", "POST /korisnici/dodaj", "SUCCESS");
        return new ResponseEntity(korisnik, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajKorisnika(int id, Korisnik korisnik) {
        Korisnik k = korisnikRepository.findByID(id);
        JSONObject objekat = new JSONObject();
        if (k == null || !korisnikRepository.existsById(id)) {
            GrpcClient.log("Korisnik", "PUT /korisnici/azuriraj/{id}", "FAIL");
            throw new NePostojiException("Korisnik sa id-em " + id + " ne postoji!");
        }

        /*
        List<Korisnik> sviKorisnici = korisnikRepository.findAll();
        for (int i = 0; i < sviKorisnici.size(); i++) {
            Korisnik korisnik1 = sviKorisnici.get(i);
            if (k.getId() != korisnik1.getId() && korisnik1.getEmail().equals(korisnik.getEmail())) {
                throw new VecPostojiException("Korisnik sa tim email-om već postoji!");
            }
        } */
        if (korisnikRepository.existsByEmail(korisnik.getEmail())) {
            GrpcClient.log("Korisnik", "GET /korisnici/azuriraj/{id}", "FAIL");
            throw new VecPostojiException("Korisnik sa tim email-om već postoji!");
        }

        if (!korisnik.getIme().isEmpty()) {
            k.setIme(korisnik.getIme());
        }
        if (!korisnik.getPrezime().isEmpty()) {
            k.setPrezime(korisnik.getPrezime());
        }
        if (!korisnik.getEmail().isEmpty()) {
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
        //restTemplate.put("http://localhost:8081/azurirajKorisnika/" + id, request);
        korisnikRepository.save(k);
        try {
            objekat.put("message", "Korisnik je uspješno ažuriran!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GrpcClient.log("Korisnik", "GET /korisnici/azuriraj/{id}", "SUCCESS");
        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiKorisnika(int id) {
        if (korisnikRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            korisnikRepository.deleteById(id);
            try {
                objekat.put("message", "Korisnik je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8081/obrisiKorisnika/" + id);
            GrpcClient.log("Korisnik", "DELETE /korisnici/obrisi/{id}", "SUCCESS");
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log("Korisnik", "DELETE /korisnici/obrisi/{id}", "FAIL");
            throw new NePostojiException("Korisnik sa id-em " + id + " ne postoji!");
        }
    }
}
