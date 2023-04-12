package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    @Autowired
    private FilmRepository repository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
           //repository.save(new Film("Taxi driver", 110));
//        repository.save(new Film("Shutter Island"));
//        repository.save(new Film("Fast and Furious"));
    }

    public Film spasiFilm(Film Film) {
        return repository.save(Film);
    }

    public List<Film> spasiFilmove(List<Film> filmovi) {
        return repository.saveAll(filmovi);
    }

    public List<Film> getSviFilmovi() {
        return repository.findAll();
    }

    public Film getFilmById(int id) {
        return repository.findById(id).orElse(null);
    }

    //public Film getFilmByName(String name) {
      //  return repository.findByName(name);
    //}

    public String obrisiFilm(int id) {
        repository.deleteById(id);
        return "Film removed !!" + id;
    }

    public String obrisiSveFilmove() {
        repository.deleteAll();
        return "Filmovi obrisani";
    }

    public Film azurirajFilm(Film film) {
        Film existingFilm = repository.findById(film.getId()).orElse(null);
        //existingFilm.setName(film.getName());
        //existingFilm.setPrice(film.getPrice());
        //existingFilm.setQuantity(film.getQuantity());
        return repository.save(existingFilm);
    }
}
