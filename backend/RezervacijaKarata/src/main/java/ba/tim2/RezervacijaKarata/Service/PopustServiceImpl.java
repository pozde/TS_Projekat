package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.Entity.Popust;
import ba.tim2.RezervacijaKarata.Repository.PopustRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopustServiceImpl implements PopustService {
    @Autowired
    private PopustRepository popustRepository;

    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_FAIL = "FAIL";
    private static final String RESOURCE_NAME = "Popust";

    private void throwNePostojiException(int id) {
        throw new NePostojiException(RESOURCE_NAME + " sa id-em " + id + " ne postoji!");
    }

    @Override
    public List<Popust> getSviPopusti() {
        return popustRepository.findAll();
    }

    @Override
    public ResponseEntity<Popust> getPopustByID(int id) {
        if (popustRepository.existsById(id)) {
            return new ResponseEntity<>(popustRepository.findByID(id), HttpStatus.OK);
        } else {
            throwNePostojiException(id);
        }
        return new ResponseEntity<>(popustRepository.findByID(0), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Popust> spasiPopust(Popust popust) {
        popustRepository.save(popust);
        return new ResponseEntity<>(popust, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajPopust(int id, Popust popust) {
        Popust p = popustRepository.findByID(id);

        if (p == null || !popustRepository.existsById(id)) {
            throwNePostojiException(id);
        }

        if (popust.getKarta() != null) {
            p.setKarta(popust.getKarta());
        }

        if (popust.getVrijednostPopusta() != 0) {
            p.setVrijednostPopusta(popust.getVrijednostPopusta());
        }

        popustRepository.save(p);
        return new ResponseEntity<>(popust, HttpStatus.OK);
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
            return new ResponseEntity<>(objekat.toString(), HttpStatus.OK);
        } else {
            throwNePostojiException(id);
        }
        return new ResponseEntity<>("Greška!", HttpStatus.NOT_FOUND);
    }
}
