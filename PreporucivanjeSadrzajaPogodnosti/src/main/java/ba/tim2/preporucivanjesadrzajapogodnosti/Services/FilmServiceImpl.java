package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Film;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.FilmRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.grpc.GrpcClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Film> getSviFilmovi() {
        GrpcClient.log("Film", "GET /filmovi/", "SUCCESS");
        return filmRepository.findAll();
    }

    @Override
    public ResponseEntity getFilmByID(int id) {
        if (filmRepository.existsById(id)) {
            GrpcClient.log("Film", "GET /filmovi/{id}", "SUCCESS");
            return new ResponseEntity(filmRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log("Film", "GET /filmovi/{id}", "FAIL");
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }

    @Override
    public ResponseEntity spasiFilm(Film film) {
        filmRepository.save(film);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Film je uspješno dodan!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Film> request = new HttpEntity<>(film, headers);
        //restTemplate.postForObject("http:://localhost:8081/dodajFilm", request, Film.class);
        GrpcClient.log("Film", "GET /filmovi/dodaj", "SUCCESS");
        return new ResponseEntity(film, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity azurirajFilm(int id, Film film) {
        Film f = filmRepository.findByID(id);

        if (f == null || !filmRepository.existsById(id)) {
            GrpcClient.log("Film", "PUT /filmovi/azuriraj/{id}", "FAIL");
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }

        if (!film.getNazivFilma().isEmpty()) {
            f.setNazivFilma(film.getNazivFilma());
        }
        if (film.getKarta() != null) {
            f.setKarta(film.getKarta());
        }
        if (film.getZanrovi() != null) {
            f.setZanrovi(film.getZanrovi());
        }

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Film je uspješno ažuriran!");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Film> request = new HttpEntity<>(film, headers);
        //restTemplate.put("http:://localhost:8081/azurirajFilm", request, Film.class);
        GrpcClient.log("Film", "PUT /filmovi/azuriraj/{id}", "SUCCESS");
        return new ResponseEntity(film, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obrisiFilm(int id) {
        if (filmRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            filmRepository.deleteById(id);
            try {
                objekat.put("message", "Film je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8081/obrisiFilm" + id);
            GrpcClient.log("Film", "DELETE /filmovi/obrisi/{id}", "SUCCESS");
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log("Film", "DELETE /filmovi/obrisi/{id}", "FAIL");
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }
}
