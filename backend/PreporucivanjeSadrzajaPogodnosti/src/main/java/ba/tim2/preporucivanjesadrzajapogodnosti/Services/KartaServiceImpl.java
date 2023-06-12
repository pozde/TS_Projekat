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

    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_FAIL = "FAIL";
    private static final String RESOURCE_NAME = "Karta";

    private void throwNePostojiException(int id) {
        throw new NePostojiException(RESOURCE_NAME + " sa id-em " + id + " ne postoji!");
    }

    @Override
    public List<Karta> getSveKarte() {
        GrpcClient.log(RESOURCE_NAME, "GET /karte/", STATUS_SUCCESS);
        return kartaRepository.findAll();
    }

    @Override
    public ResponseEntity<Karta> getKartaByID(int id) {
        if (kartaRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "GET /karte/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(kartaRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "GET /karte/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>(kartaRepository.findByID(0), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Karta> spasiKartu(Karta karta) {
        kartaRepository.save(karta);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Karta> request = new HttpEntity<>(karta, headers);
        restTemplate.postForObject("http:://localhost:8081/dodajKartu", request, Karta.class);
        GrpcClient.log(RESOURCE_NAME, "POST /karte/dodaj", STATUS_SUCCESS);
        return new ResponseEntity<>(karta, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajKartu(int id, Karta karta) {
        Karta k = kartaRepository.findByID(id);

        if (k == null || !kartaRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "PUT /karte/azuriraj/{id}", STATUS_SUCCESS);
            throwNePostojiException(id);
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

        kartaRepository.save(k);
        GrpcClient.log(RESOURCE_NAME, "PUT /karte/azuriraj/{id}", STATUS_SUCCESS);
        return new ResponseEntity<>(karta, HttpStatus.OK);
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
            GrpcClient.log(RESOURCE_NAME, "DELETE /karte/obrisi/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "DELETE /karte/obrisi/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>("Greška!", HttpStatus.NOT_FOUND);
    }
}
