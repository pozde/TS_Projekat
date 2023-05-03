package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Clanarina;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.ClanarinaRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClanarinaServiceImpl implements ClanarinaService {
    @Autowired
    private ClanarinaRepository clanarinaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Clanarina> getSveClanarine() {
        return clanarinaRepository.findAll();
    }

    @Override
    public ResponseEntity getClanarinaByID(int id) {
        if (clanarinaRepository.existsById(id)) {
            return new ResponseEntity(clanarinaRepository.findByID(id), HttpStatus.OK);
        }
        else {
            throw new NePostojiException("Clanarina sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiClanarinu(Clanarina clanarina) {
        clanarinaRepository.save(clanarina);
        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Članarina uspješno dodana!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Clanarina> request = new HttpEntity<>(clanarina, headers);
        Clanarina clanarina1 = restTemplate.postForObject("http://localhost/dodajClanarinu", request, Clanarina.class);
        return new ResponseEntity(clanarina, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajClanarinu(int id, Clanarina clanarina) {
        Clanarina c = clanarinaRepository.findByID(id);
        JSONObject objekat = new JSONObject();
        if (c == null || !clanarinaRepository.existsById(id)) {
            throw new NePostojiException("Clanarina sa id-em " + id + " ne postoji!");
        }

        if (clanarina.getDatumIsteka() != null) {
            c.setDatumIsteka(clanarina.getDatumIsteka());
        }
        if (!clanarina.getVrsta().isEmpty()) {
            c.setVrsta(clanarina.getVrsta());
        }
        if (clanarina.getKorisnici() != null) {
            c.setKorisnici(clanarina.getKorisnici());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Clanarina> request = new HttpEntity<>(clanarina, httpHeaders);
        restTemplate.put("http://localhost/azurirajClanarinu/" + id, request);
        clanarinaRepository.save(clanarina);
        try {
            objekat.put("message", "Članarina je uspješno ažurirana!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(clanarina, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiClanarinu(int id) {
        if (clanarinaRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            clanarinaRepository.deleteById(id);
            try {
                objekat.put("message", "Članarina je uspješno obrisana!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            restTemplate.delete("http://localhost/obrisiClanarinu/" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        }
        else {
            throw new NePostojiException("Članarina sa id-em " + id + " ne postoji!");
        }
    }
}
