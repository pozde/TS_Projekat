package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.Entity.PreporukaFilma;
import ba.tim2.RezervacijaKarata.Repository.PreporukaFilmaRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreporukaFilmaServiceImpl implements PreporukaFilmaService {
    @Autowired
    private PreporukaFilmaRepository preporukaFilmaRepository;

    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_FAIL = "FAIL";
    private static final String RESOURCE_NAME = "Preporuka filma";

    private void throwNePostojiException(int id) {
        throw new NePostojiException(RESOURCE_NAME + " sa id-em " + id + " ne postoji!");
    }

    @Override
    public List<PreporukaFilma> getSvePreporukeFilmova() {
        return preporukaFilmaRepository.findAll();
    }

    @Override
    public ResponseEntity<PreporukaFilma> getPreporukaFilmaByID(int id) {
        if (preporukaFilmaRepository.existsById(id)) {
            return new ResponseEntity<>(preporukaFilmaRepository.findByID(id), HttpStatus.OK);
        } else {
            throwNePostojiException(id);
        }
        return new ResponseEntity<>(preporukaFilmaRepository.findByID(0), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<PreporukaFilma> spasiPreporukuFilma(PreporukaFilma preporukaFilma) {
        preporukaFilmaRepository.save(preporukaFilma);
        return new ResponseEntity<>(preporukaFilma, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PreporukaFilma> azurirajPreporukuFilma(int id, PreporukaFilma preporukaFilma) {
        PreporukaFilma p = preporukaFilmaRepository.findByID(id);

        if (p == null || !preporukaFilmaRepository.existsById(id)) {
            throwNePostojiException(id);
        }

        if (preporukaFilma.getZanr() != null) {
            p.setZanr(preporukaFilma.getZanr());
        }
        if (preporukaFilma.getKorisnik() != null) {
            p.setKorisnik(preporukaFilma.getKorisnik());
        }

        preporukaFilmaRepository.save(p);
        return new ResponseEntity<>(preporukaFilma, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> obrisiPreporukuFilma(int id) {
        if (preporukaFilmaRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            preporukaFilmaRepository.deleteById(id);
            try {
                objekat.put("message", "Preporuka filma je uspješno obrisana!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(objekat.toString(), HttpStatus.OK);
        } else {
            throwNePostojiException(id);
        }
        return new ResponseEntity<>("Greška!", HttpStatus.NOT_FOUND);
    }
}
