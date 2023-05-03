package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Zanr;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ZanrService {
    @Autowired
    private ZanrRepository repository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        //repository.save(new Zanr("akcija"));




        //var clanarina = clanarinaRepository.findById(1).orElse(null);
        //if (clanarina != null) {
        //    Korisnik korisnik = new Korisnik("Benjamin", "Pasic", null, "bpasic1@etf.unsa.ba",
        //            "062623259", "M", clanarina);
        //    repository.save(korisnik);
        //    //clanarina.dodajKorisnika(korisnik);
        //    //clanarinaRepository.save(clanarina);

        //}
    }
}
