package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Karta;
import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.Zanr;
import ba.tim2.RezervacijaKarata.ErrorHandling.NePostojiException;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import ba.tim2.RezervacijaKarata.Repository.KartaRepository;
import ba.tim2.RezervacijaKarata.Repository.SalaRepository;
import ba.tim2.RezervacijaKarata.Repository.ZanrRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ZanrRepository zanrRepository;

    @Autowired
    private KartaRepository kartaRepository;

    @Autowired
    private SalaRepository salaRepository;


    @Autowired
    private EntityManager entityManager;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        //repository.save(new Film("Taxi driver", 110));
//        repository.save(new Film("Shutter Island"));
//        repository.save(new Film("Fast and Furious"));
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


        return new ResponseEntity(film, HttpStatus.CREATED);
    }

//    public List<Film> spasiFilmove(List<Film> filmovi) {
//        return filmRepository.saveAll(filmovi);
//    }

    @Override
    public List<Film> getSviFilmovi() {
        return filmRepository.findAll();
    }

    @Override
    public ResponseEntity getFilmByID(int id) {
        if (filmRepository.existsById(id)) {
            return new ResponseEntity(filmRepository.findByID(id), HttpStatus.OK);
        } else {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }

    //public Film getFilmByName(String name) {
    //  return repository.findByName(name);
    //}

    @Override
    @Transactional
    public ResponseEntity obrisiFilm(int id) {
        if (filmRepository.existsById(id)) {
            JSONObject objekat = new JSONObject();
            Film film = filmRepository.findByID(id);

            List<Karta> karte = kartaRepository.findAll();
            for (Karta karta : karte) {
                if (karta.getFilm() != null && karta.getFilm().equals(film)) {
                    karta.setFilm(null);
                }
            }

            List<Sala> sale = salaRepository.findAll();
            sale.remove(film);

            List<Zanr> zanrovi = zanrRepository.findAll();
            zanrovi.remove(film);

            film.setKarta(null);
            film.setSale(null);
            film.setZanrovi(null);

            Film mergedFilm = entityManager.merge(film);
            entityManager.remove(mergedFilm);
            //filmRepository.deleteById(id);
            try {
                objekat.put("message", "Film je uspješno obrisan!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //restTemplate.delete("http://localhost:8080/obrisiFilm" + id);
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }
    }

//    public String obrisiSveFilmove() {
//        repository.deleteAll();
//        return "Filmovi obrisani";
//    }

    @Override
    public ResponseEntity azurirajFilm(int id, Film film) {
        Film f = filmRepository.findByID(id);

        if (f == null || !filmRepository.existsById(id)) {
            throw new NePostojiException("Film sa id-em " + id + " ne postoji!");
        }

        if (!film.getNazivFilma().isEmpty()) {
            f.setNazivFilma(film.getNazivFilma());
        }
        if (film.getTrajanje() > 0) {
            f.setTrajanje(film.getTrajanje());
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
        //restTemplate.put("http://preporucivanje-sadrzaja-pogodnosti/filmovi/dodaj", request, Film.class);
        return new ResponseEntity(film, HttpStatus.OK);
    }
}
