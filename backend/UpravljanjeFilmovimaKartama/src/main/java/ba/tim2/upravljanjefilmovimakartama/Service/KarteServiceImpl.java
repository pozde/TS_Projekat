package ba.tim2.upravljanjefilmovimakartama.Service;

import ba.tim2.upravljanjefilmovimakartama.Entity.*;
import ba.tim2.upravljanjefilmovimakartama.ErrorHandling.NePostojiException;
import ba.tim2.upravljanjefilmovimakartama.Repository.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KarteServiceImpl implements KarteService{
    @Autowired
    private KartaRepository kartaRepository;



    @Autowired
    private SjedisteRepository sjedisteRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private FilmRepository filmRepository;




    @Override
    public List<Karta> getSveKarte() {
        return kartaRepository.findAll();
    }

    @Override
    public List<Karta> getKarteById(int id) {
        if (kartaRepository.existsById(id)) {
            return kartaRepository.findAll().stream()
                    .filter(karta -> karta.getID() == id)
                    .collect(Collectors.toList());
        } else {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity getKartuById(int id) {
        if (kartaRepository.existsById(id)) {
            return new ResponseEntity(kartaRepository.findByID(id), HttpStatus.OK);
        } else {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }

    //public RecenzijaAplikacija getRecenzijaAplikacijaByName(String name) {
    //  return repository.findByName(name);
    //}

    @Override
    public ResponseEntity getSjedisteByKarta(int brojSale, int brojSjedista){       //OVO vidi za if
        return new ResponseEntity(kartaRepository.postojiLiSjedisteNaKarti(brojSale, brojSjedista), HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiKartu(int id) {
        if (kartaRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            kartaRepository.deleteById(id);
            try {
                objekat.put("message", "Karta je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8080/obrisiKartu" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Karta sa id-em " + id + " ne postoji!");
        }
    }

//    public RecenzijaAplikacija azurirajRecenzijuZaAplikaciju(RecenzijaAplikacija recenzija) {
//        RecenzijaAplikacija existingRecenzijaAplikacija = repository.findById(recenzija.getId()).orElse(null);
//        //existingRecenzijaAplikacija.setName(RecenzijaAplikacija.getName());
//        //existingRecenzijaAplikacija.setPrice(RecenzijaAplikacija.getPrice());
//        //existingRecenzijaAplikacija.setQuantity(RecenzijaAplikacija.getQuantity());
//        return repository.save(existingRecenzijaAplikacija);
//    }
}
