package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.VecPostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.KorisnikRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.grpc.GrpcClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_FAIL = "FAIL";
    private static final String RESOURCE_NAME = "Korisnik";

    private void throwNePostojiException(int id) {
        throw new NePostojiException(RESOURCE_NAME + " sa id-em " + id + " ne postoji!");
    }

    @Override
    public List<Korisnik> getSviKorisnici() {
        GrpcClient.log(RESOURCE_NAME, "GET /korisnici/", STATUS_SUCCESS);
        return korisnikRepository.findAll();
    }

    @Override
    public ResponseEntity<Korisnik> getKorisnikByID(int id) {
        if (korisnikRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "GET /korisnici/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(korisnikRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "GET /korisnici/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>(korisnikRepository.findByID(0), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity getKorisnikByEmail(String email) {
        if (korisnikRepository.existsByEmail(email)) {
            GrpcClient.log(RESOURCE_NAME, "GET /korisnici/{email}", STATUS_SUCCESS);
            return new ResponseEntity<>(korisnikRepository.findByEmail(email), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "GET /korisnici/{email}", STATUS_FAIL);
            throw new NePostojiException("Korisnik sa email-om " + email + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiKorisnika(Korisnik korisnik) {
        korisnikRepository.save(korisnik);
        GrpcClient.log(RESOURCE_NAME, "POST /korisnici/dodaj", STATUS_SUCCESS);
        return new ResponseEntity<>(korisnik, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajKorisnika(int id, Korisnik korisnik) {
        Korisnik k = korisnikRepository.findByID(id);
        if (k == null || !korisnikRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "PUT /korisnici/azuriraj/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        if (korisnikRepository.existsByEmail(korisnik.getEmail())) {
            GrpcClient.log(RESOURCE_NAME, "GET /korisnici/azuriraj/{id}", STATUS_FAIL);
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

        GrpcClient.log(RESOURCE_NAME, "GET /korisnici/azuriraj/{id}", STATUS_SUCCESS);
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
            GrpcClient.log(RESOURCE_NAME, "DELETE /korisnici/obrisi/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "DELETE /korisnici/obrisi/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>("Greška!", HttpStatus.NOT_FOUND);
    }
}
