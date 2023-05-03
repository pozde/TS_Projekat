package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Clanarina;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.ClanarinaRepository;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ClanarinaService {

    @Autowired
    private ClanarinaRepository clanarinaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    /*@EventListener
    public void appReady(ApplicationReadyEvent event) {
        Clanarina clanarina = new Clanarina(null, "Family");
        clanarinaRepository.save(clanarina);
    }*/
}
