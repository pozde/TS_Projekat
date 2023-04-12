package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import ba.tim2.RezervacijaKarata.Repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository repository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
    //    repository.save(new Korisnik("Benjamin", "Pasic", new Date(2000, Calendar.FEBRUARY,24),"bpasic1@etf.unsa.ba","062623259","M"));
    }

    public Korisnik spasiKorisnika(Korisnik korisnik) {
        return repository.save(korisnik);
    }

    public List<Korisnik> spasiKorisnike(List<Korisnik> korisnici) {
        return repository.saveAll(korisnici);
    }

    public List<Korisnik> getSviKorisnici() {
        return repository.findAll();
    }

    public Korisnik getKorisnikById(int id) {
        return repository.findById(id).orElse(null);
    }

    //public Korisnik getKorisnikByName(String name) {
    //  return repository.findByName(name);
    //}

    public String obrisiKorisnika(int id) {
        repository.deleteById(id);
        return "Korisnik removed !!" + id;
    }

    public Korisnik azurirajKorisnika(Korisnik korisnik) {
        Korisnik existingKorisnik = repository.findById(korisnik.getId()).orElse(null);
        //existingKorisnik.setName(Korisnik.getName());
        //existingKorisnik.setPrice(Korisnik.getPrice());
        //existingKorisnik.setQuantity(Korisnik.getQuantity());
        return repository.save(existingKorisnik);
    }
}
