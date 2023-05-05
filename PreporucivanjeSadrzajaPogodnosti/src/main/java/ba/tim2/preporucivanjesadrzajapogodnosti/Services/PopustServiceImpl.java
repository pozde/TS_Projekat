package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Popust;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.PopustRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PopustServiceImpl implements PopustService {

    @Autowired
    private PopustRepository popustRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Popust> getSviPopusti() {
        return popustRepository.findAll();
    }

    @Override
    public ResponseEntity getPopustByID(int id) {
        if (popustRepository.existsById(id)) {
            return new ResponseEntity(popustRepository.findByID(id), HttpStatus.OK);
        } else {
            throw new NePostojiException("Popust sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiPopust(Popust popust) {
        popustRepository.save(popust);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Popust je uspješno dodan!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Popust> request = new HttpEntity<>(popust, headers);
        restTemplate.postForObject("http:://localhost:8081/dodajPopust", request, Popust.class);
        return new ResponseEntity(popust, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajPopust(int id, Popust popust) {
        Popust p = popustRepository.findByID(id);

        if (p == null || !popustRepository.existsById(id)) {
            throw new NePostojiException("Popust sa id-em " + id + " ne postoji!");
        }

        if (popust.getKarta() != null) {
            p.setKarta(popust.getKarta());
        }

        if (popust.getVrijednostPopusta() != 0) {
            p.setVrijednostPopusta(popust.getVrijednostPopusta());
        }

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Popust je uspješno ažuriran!");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Popust> request = new HttpEntity<>(popust, headers);
        restTemplate.put("http:://localhost:8081/azurirajPopust", request, Popust.class);
        return new ResponseEntity(popust, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiPopust(int id) {
        if (popustRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            popustRepository.deleteById(id);
            try {
                objekat.put("message", "Popust je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            restTemplate.delete("http://localhost:8081/obrisiPopust" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Popust sa id-em " + id + " ne postoji!");
        }
    }
}
