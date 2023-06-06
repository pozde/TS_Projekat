package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Clanarina;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.ClanarinaRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.grpc.GrpcClient;
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
        GrpcClient.log("Clanarina", "GET /clanarine/", "SUCCESS");
        return clanarinaRepository.findAll();
    }

    @Override
    public ResponseEntity getClanarinaByID(int id) {
        if (clanarinaRepository.existsById(id)) {
            GrpcClient.log("Clanarina", "GET /clanarine/{id}", "SUCCESS");
            return new ResponseEntity(clanarinaRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log("Clanarina", "GET /clanarine/{id}", "FAIL");
            throw new NePostojiException("Clanarina sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiClanarinu(Clanarina clanarina) {
        clanarinaRepository.save(clanarina);
        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Članarina je uspješno dodana!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Clanarina> request = new HttpEntity<>(clanarina, headers);
        //restTemplate.postForObject("http://localhost:8081/dodajClanarinu", request, Clanarina.class);
        GrpcClient.log("Clanarina", "POST /clanarine/dodaj", "SUCCESS");
        return new ResponseEntity(clanarina, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajClanarinu(int id, Clanarina clanarina) {
        Clanarina c = clanarinaRepository.findByID(id);
        JSONObject objekat = new JSONObject();
        if (c == null || !clanarinaRepository.existsById(id)) {
            GrpcClient.log("Clanarina", "PUT /clanarine/azuriraj/{id}", "FAIL");
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
        //restTemplate.put("http://localhost:8081/azurirajClanarinu/" + id, request);
        clanarinaRepository.save(c);
        try {
            objekat.put("message", "Članarina je uspješno ažurirana!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GrpcClient.log("Clanarina", "PUT /clanarine/azuriraj/{id}", "SUCCESS");
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
            //restTemplate.delete("http://localhost:8081/obrisiClanarinu/" + id);
            GrpcClient.log("Clanarina", "DELETE /clanarine/obrisi/{id}", "SUCCESS");
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log("Clanarina", "DELETE /clanarine/obrisi/{id}", "FAIL");
            throw new NePostojiException("Članarina sa id-em " + id + " ne postoji!");
        }
    }
}
