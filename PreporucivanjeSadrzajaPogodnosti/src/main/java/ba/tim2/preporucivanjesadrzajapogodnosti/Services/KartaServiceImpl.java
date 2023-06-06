package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Karta;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.KartaRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.grpc.GrpcClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class KartaServiceImpl implements KartaService {
    @Autowired
    private KartaRepository kartaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Karta> getSveKarte() {
        GrpcClient.log("Karta", "GET /karte/", "SUCCESS");
        return kartaRepository.findAll();
    }

    @Override
    public ResponseEntity getKartaByID(int id) {
        if (kartaRepository.existsById(id)) {
            GrpcClient.log("Karta", "GET /karte/{id}", "SUCCESS");
            return new ResponseEntity(kartaRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log("Karta", "GET /karte/{id}", "FAIL");
            throw new NePostojiException("Karta sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiKartu(Karta karta) {
        kartaRepository.save(karta);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Karta je uspješno dodana!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Karta> request = new HttpEntity<>(karta, headers);
        //restTemplate.postForObject("http:://localhost:8081/dodajKartu", request, Karta.class);
        GrpcClient.log("Karta", "POST /karte/dodaj", "SUCCESS");
        return new ResponseEntity(karta, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajKartu(int id, Karta karta) {
        Karta k = kartaRepository.findByID(id);

        if (k == null || !kartaRepository.existsById(id)) {
            GrpcClient.log("Karta", "PUT /karte/azuriraj/{id}", "SUCCESS");
            throw new NePostojiException("Karta sa id-em " + id + " ne postoji!");
        }

        if (karta.getKorisnik() != null) {
            k.setKorisnik(karta.getKorisnik());
        }

        if (karta.getFilm() != null) {
            k.setFilm(karta.getFilm());
        }

        if (karta.getPopust() != null) {
            k.setPopust(karta.getPopust());
        }

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Karta je uspješno ažurirana!");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Karta> request = new HttpEntity<>(karta, headers);
        //restTemplate.put("http:://localhost:8081/azurirajKartu", request, Karta.class);
        kartaRepository.save(k);
        GrpcClient.log("Karta", "PUT /karte/azuriraj/{id}", "SUCCESS");
        return new ResponseEntity(karta, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiKartu(int id) {
        if (kartaRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            kartaRepository.deleteById(id);
            try {
                objekat.put("message", "Karta je uspješno obrisana!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8081/obrisiKartu" + id);
            GrpcClient.log("Karta", "DELETE /karte/obrisi/{id}", "SUCCESS");
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log("Karta", "DELETE /karte/obrisi/{id}", "FAIL");
            throw new NePostojiException("Karta sa id-em " + id + " ne postoji!");
        }
    }
}
