package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Zanr;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.ZanrRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ZanrServiceImpl implements ZanrService {
    @Autowired
    private ZanrRepository zanrRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Zanr> getSviZanrovi() {
        return zanrRepository.findAll();
    }

    @Override
    public ResponseEntity getZanrByID(int id) {
        if (zanrRepository.existsById(id)) {
            return new ResponseEntity(zanrRepository.findByID(id), HttpStatus.OK);
        } else {
            throw new NePostojiException("Žanr sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiZanr(Zanr zanr) {
        zanrRepository.save(zanr);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Žanr je uspješno dodan!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Zanr> request = new HttpEntity<>(zanr, headers);
        restTemplate.postForObject("http:://localhost:8081/dodajZanr", request, Zanr.class);
        return new ResponseEntity(zanr, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajZanr(int id, Zanr zanr) {
        Zanr z = zanrRepository.findByID(id);

        if (z == null || !zanrRepository.existsById(id)) {
            throw new NePostojiException("Žanr sa id-em " + id + " ne postoji!");
        }

        if (!zanr.getNaziv().isEmpty()) {
            z.setNaziv(zanr.getNaziv());
        }
        if (zanr.getFilm() != null) {
            z.setFilm(zanr.getFilm());
        }
        if (zanr.getPreporukaFilma() != null) {
            z.setPreporukaFilma(zanr.getPreporukaFilma());
        }

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Žanr je uspješno ažuriran!");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Zanr> request = new HttpEntity<>(zanr, headers);
        restTemplate.put("http:://localhost:8081/azurirajZanr", request, Zanr.class);
        return new ResponseEntity(zanr, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiZanr(int id) {
        if (zanrRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            zanrRepository.deleteById(id);
            try {
                objekat.put("message", "Žanr je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            restTemplate.delete("http://localhost:8081/obrisiZanr" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Žanr sa id-em " + id + " ne postoji!");
        }
    }
}
