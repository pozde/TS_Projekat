package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.PreporukaFilma;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.PreporukaFilmaRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PreporukaFilmaServiceImpl implements PreporukaFilmaService {
    @Autowired
    private PreporukaFilmaRepository preporukaFilmaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<PreporukaFilma> getSvePreporukeFilmova() {
        return preporukaFilmaRepository.findAll();
    }

    @Override
    public ResponseEntity getPreporukaFilmaByID(int id) {
        if (preporukaFilmaRepository.existsById(id)) {
            return new ResponseEntity(preporukaFilmaRepository.findByID(id), HttpStatus.OK);
        } else {
            throw new NePostojiException("Preporuka filma sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiPreporukuFilma(PreporukaFilma preporukaFilma) {
        preporukaFilmaRepository.save(preporukaFilma);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Preporuka filma je uspješno dodana!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PreporukaFilma> request = new HttpEntity<>(preporukaFilma, headers);
        restTemplate.postForObject("http:://localhost:8081/dodajPreporukuFilma", request, PreporukaFilma.class);
        return new ResponseEntity(preporukaFilma, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajPreporukuFilma(int id, PreporukaFilma preporukaFilma) {
        PreporukaFilma p = preporukaFilmaRepository.findByID(id);

        if (p == null || !preporukaFilmaRepository.existsById(id)) {
            throw new NePostojiException("Preporuka filma sa id-em " + id + " ne postoji!");
        }

        if (preporukaFilma.getZanr() != null) {
            p.setZanr(preporukaFilma.getZanr());
        }
        if (preporukaFilma.getKorisnik() != null) {
            p.setKorisnik(preporukaFilma.getKorisnik());
        }


        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Preporuka filma je uspješno ažurirana!");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PreporukaFilma> request = new HttpEntity<>(preporukaFilma, headers);
        restTemplate.put("http:://localhost:8081/azurirajPreporukuFilma", request, PreporukaFilma.class);
        return new ResponseEntity(preporukaFilma, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiPreporukuFilma(int id) {
        if (preporukaFilmaRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            preporukaFilmaRepository.deleteById(id);
            try {
                objekat.put("message", "Preporuka filma je uspješno obrisana!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            restTemplate.delete("http://localhost:8081/obrisiPreporukuFilma" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Preporuka filma sa id-em " + id + " ne postoji!");
        }
    }
}
