package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import ba.tim2.RezervacijaKarata.Repository.SalaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;
//    @PersistenceContext
//    private EntityManager entityManager;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        //entityManager
        //            .createNativeQuery("ALTER TABLE SomeTable AUTO_INCREMENT = 1")
        //            .executeUpdate();
       //repository.save(new Sala(2));
    }

    public Sala getSaluPrekoBrojaSale(int brojSale) {
        return repository.findBrojSalePrekoSale(brojSale);
    }

    public Sala spasiSalu(Sala sala) {
        return repository.save(sala);
    }

    public List<Sala> spasiSale(List<Sala> filmovi) {
        return repository.saveAll(filmovi);
    }

    public List<Sala> getSveSale() {
        return repository.findAll();
    }

    public String obrisiSveSale() {
        repository.deleteAll();
        return "Sve sale obrisane";
    }

    public String obrisiSalu(int id) {
        repository.deleteById(id);
        return "Sala uklonjena!!" + id;
    }
}
