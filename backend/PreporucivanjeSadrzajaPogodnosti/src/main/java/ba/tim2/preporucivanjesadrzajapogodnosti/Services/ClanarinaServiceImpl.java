package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Clanarina;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.ClanarinaRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.grpc.GrpcClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClanarinaServiceImpl implements ClanarinaService {
    @Autowired
    private ClanarinaRepository clanarinaRepository;

    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_FAIL = "FAIL";
    private static final String RESOURCE_NAME = "Clanarina";

    private void throwNePostojiException(int id) {
        throw new NePostojiException(RESOURCE_NAME + " sa id-em " + id + " ne postoji!");
    }

    @Override
    public List<Clanarina> getSveClanarine() {
        GrpcClient.log(RESOURCE_NAME, "GET /clanarine/", STATUS_SUCCESS);
        return clanarinaRepository.findAll();
    }

    @Override
    public ResponseEntity<Clanarina> getClanarinaByID(int id) {
        if (clanarinaRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "GET /clanarine/{id}", STATUS_SUCCESS);
            return new ResponseEntity<Clanarina>(clanarinaRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "GET /clanarine/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>(clanarinaRepository.findByID(0), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Clanarina> spasiClanarinu(Clanarina clanarina) {
        clanarinaRepository.save(clanarina);
        GrpcClient.log(RESOURCE_NAME, "POST /clanarine/dodaj", STATUS_SUCCESS);
        return new ResponseEntity<>(clanarina, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Clanarina> azurirajClanarinu(int id, Clanarina clanarina) {
        Clanarina c = clanarinaRepository.findByID(id);
        if (c == null || !clanarinaRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "PUT /clanarine/azuriraj/{id}", STATUS_FAIL);
            throwNePostojiException(id);
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

        clanarinaRepository.save(c);
        GrpcClient.log(RESOURCE_NAME, "PUT /clanarine/azuriraj/{id}", STATUS_SUCCESS);
        return new ResponseEntity<>(clanarina, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> obrisiClanarinu(int id) {
        if (clanarinaRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            clanarinaRepository.deleteById(id);
            try {
                objekat.put("message", "Članarina je uspješno obrisana!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GrpcClient.log(RESOURCE_NAME, "DELETE /clanarine/obrisi/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "DELETE /clanarine/obrisi/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>("Greška!", HttpStatus.NOT_FOUND);
    }
}
