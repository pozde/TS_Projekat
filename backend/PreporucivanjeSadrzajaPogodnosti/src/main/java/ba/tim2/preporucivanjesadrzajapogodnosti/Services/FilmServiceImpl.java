package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.ErrorHandling.NePostojiException;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Film;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.FilmRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.grpc.GrpcClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_FAIL = "FAIL";
    private static final String RESOURCE_NAME = "Film";

    private void throwNePostojiException(int id) {
        throw new NePostojiException(RESOURCE_NAME + " sa id-em " + id + " ne postoji!");
    }

    @Override
    public List<Film> getSviFilmovi() {
        GrpcClient.log(RESOURCE_NAME, "GET /filmovi/", STATUS_SUCCESS);
        return filmRepository.findAll();
    }

    @Override
    public ResponseEntity<Film> getFilmByID(int id) {
        if (filmRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "GET /filmovi/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(filmRepository.findByID(id), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "GET /filmovi/{id}", STATUS_FAIL);
            throwNePostojiException(id);
        }
        return new ResponseEntity<>(filmRepository.findByID(0), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Film> spasiFilm(Film film) {
        filmRepository.save(film);
        GrpcClient.log(RESOURCE_NAME, "GET /filmovi/dodaj", STATUS_SUCCESS);
        return new ResponseEntity<>(film, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Film> azurirajFilm(int id, Film film) {
        Film f = filmRepository.findByID(id);

        if (f == null || !filmRepository.existsById(id)) {
            GrpcClient.log(RESOURCE_NAME, "PUT /filmovi/azuriraj/{id}", STATUS_FAIL);
            throwNePostojiException(id);
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
        GrpcClient.log(RESOURCE_NAME, "PUT /filmovi/azuriraj/{id}", STATUS_SUCCESS);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> obrisiFilm(int id) {
        if (filmRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            filmRepository.deleteById(id);
            try {
                objekat.put("message", "Film je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GrpcClient.log(RESOURCE_NAME, "DELETE /filmovi/obrisi/{id}", STATUS_SUCCESS);
            return new ResponseEntity<>(objekat.toString(), HttpStatus.OK);
        } else {
            GrpcClient.log(RESOURCE_NAME, "DELETE /filmovi/obrisi/{id}", STATUS_FAIL);
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }
}
