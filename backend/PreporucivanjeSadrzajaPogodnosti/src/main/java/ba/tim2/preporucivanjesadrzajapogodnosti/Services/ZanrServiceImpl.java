package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Zanr;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.ZanrRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.grpc.GrpcClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZanrServiceImpl implements ZanrService {
    @Autowired
    private ZanrRepository zanrRepository;

    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_FAIL = "FAIL";
    private static final String RESOURCE_NAME = "Zanr";

    private void throwNePostojiException(int id) {
        throw new NePostojiException(RESOURCE_NAME + " sa id-em " + id + " ne postoji!");
    }

    @Override
    public List<Zanr> getSviZanrovi() {
        GrpcClient.log(RESOURCE_NAME, "GET /zanrovi/", STATUS_SUCCESS);
        return zanrRepository.findAll();
    }

    @Override
    public ResponseEntity<Zanr> getZanrByID(int id) {
        if (zanrRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "GET /zanrovi/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(zanrRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "GET /zanrovi/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>(zanrRepository.findByID(0), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Zanr> spasiZanr(Zanr zanr) {
        zanrRepository.save(zanr);
        GrpcClient.log(RESOURCE_NAME, "POST /zanrovi/dodaj", STATUS_SUCCESS);
        return new ResponseEntity<>(zanr, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Zanr> azurirajZanr(int id, Zanr zanr) {
        Zanr z = zanrRepository.findByID(id);

        if (z == null || !zanrRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "PUT /zanrovi/azuriraj/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }

        if (!zanr.getNaziv().isEmpty()) {
            z.setNaziv(zanr.getNaziv());
        }
        if (zanr.getPreporukaFilma() != null) {
            z.setPreporukaFilma(zanr.getPreporukaFilma());
        }
        zanrRepository.save(z);
        GrpcClient.log(RESOURCE_NAME, "PUT /zanrovi/azuriraj/{id}", STATUS_SUCCESS);
        return new ResponseEntity<>(zanr, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> obrisiZanr(int id) {
        if (zanrRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            zanrRepository.deleteById(id);
            try {
                objekat.put("message", "Žanr je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GrpcClient.log(RESOURCE_NAME, "DELETE /zanrovi/obrisi/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "DELETE /zanrovi/obrisi/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>("Greška!", HttpStatus.NOT_FOUND);
    }
}
