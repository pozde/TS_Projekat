package ba.tim2.RezervacijaKarata.Service;


import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.Sjedista;
import ba.tim2.RezervacijaKarata.Repository.SalaRepository;
import ba.tim2.RezervacijaKarata.Repository.SjedistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SjedistaService {
    @Autowired
    private SjedistaRepository repository;
    @Autowired
    private SalaRepository salaRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
//        var salaPrva = new Sala(3,1);
//        var salaPrva = salaRepository.findById(14).orElse(null);
//        if(salaPrva != null) {
//            repository.save(new Sjedista(1, salaPrva));
//            repository.save(new Sjedista(2, salaPrva));
//            repository.save(new Sjedista(3, salaPrva));
//            repository.save(new Sjedista(4, salaPrva));
//            repository.save(new Sjedista(5, salaPrva));
//            repository.save(new Sjedista(6, salaPrva));
//        }
    }
    public Sjedista spasiSjediste(Sjedista sjediste) {
        return repository.save(sjediste);
    }

    public List<Sjedista> spasiSjedista(List<Sjedista> sjedista) {
        return repository.saveAll(sjedista);
    }

    public List<Sjedista> getSvaSjedista() {
        return repository.findAll();
    }

    public int getSalaIdPrekoSjedista(int id) {
        return repository.findSalaIdBySjedisteId(id);
    }

    public int getBrojSalePrekoSjedista(int brojSale) {
        return repository.findBrojSalePrekoSjedista(brojSale);
    }

    public Sjedista getSjedistePrekoBrojaSjedista(int broj) {
        return repository.findSjedisteByBrojSjedista(broj);
    }
    public String obrisiSvaSjedista() {
        repository.deleteAll();
        return "Obrisana sva sjedista";
    }

    public String obrisiSjediste(int id) {
        repository.deleteById(id);
        return "Sjediste uklonjeno!!" + id;
    }
}
