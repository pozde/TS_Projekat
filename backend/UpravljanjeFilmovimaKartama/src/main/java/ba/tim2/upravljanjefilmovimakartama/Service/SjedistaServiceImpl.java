package ba.tim2.upravljanjefilmovimakartama.Service;


import ba.tim2.upravljanjefilmovimakartama.Entity.Sjediste;
import ba.tim2.upravljanjefilmovimakartama.ErrorHandling.NePostojiException;
import ba.tim2.upravljanjefilmovimakartama.Repository.SalaRepository;
import ba.tim2.upravljanjefilmovimakartama.Repository.SjedisteRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SjedistaServiceImpl implements SjedistaService {
    @Autowired
    private SjedisteRepository sjedisteRepository;
    @Autowired
    private SalaRepository salaRepository;


    public ResponseEntity spasiSjediste(Sjediste sjediste) {
        sjedisteRepository.save(sjediste);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Sjedište je uspješno dodan!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Sjediste> request = new HttpEntity<>(sjediste, headers);

        return new ResponseEntity(sjediste, HttpStatus.CREATED);
    }


    public List<Sjediste> getSvaSjedista() {
        return sjedisteRepository.findAll();
    }

    public int getSalaIdPrekoSjedista(int id) {
        return sjedisteRepository.findSalaIdBySjedisteId(id);
    }

    public int getBrojSalePrekoSjedista(int brojSale) {
        return sjedisteRepository.findBrojSalePrekoSjedista(brojSale);
    }

    public ResponseEntity getSjedistePrekoBrojaSjedista(int broj) {
        return new ResponseEntity(sjedisteRepository.findSjedisteByBrojSjedista(broj), HttpStatus.OK);
    }
    public ResponseEntity obrisiSvaSjedista() {
        if(salaRepository.count() > 0) {
            JSONObject objekat = new JSONObject();
            salaRepository.deleteAll();
            try {
                objekat.put("message", "Sjedišta su uspješno obrisana!");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Nema sjedišta dostupnih za brisanje");
        }
    }

    public ResponseEntity obrisiSjediste(int id) {
        if (sjedisteRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            sjedisteRepository.deleteById(id);
            try {
                objekat.put("message", "Sjedište je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }
}
