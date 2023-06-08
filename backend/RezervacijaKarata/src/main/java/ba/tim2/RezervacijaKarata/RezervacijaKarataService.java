package ba.tim2.RezervacijaKarata;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Zanr;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import ba.tim2.RezervacijaKarata.Repository.ZanrRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class RezervacijaKarataService {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ZanrRepository zanrRepository;

    @Transactional
    public void insertStaticData() {
        Film film1 = new Film();
        Film film2 = new Film();
        Film film3 = new Film();
        Film film4 = new Film();
        Film film5 = new Film();
        Film film6 = new Film();
        Film film7 = new Film();
        Film film8 = new Film();
        Film film9 = new Film();
        Film film10 = new Film();
        Film film11 = new Film();
        Film film12 = new Film();
        Film film13 = new Film();

        Zanr akcija = new Zanr("Akcija");
        Zanr triler = new Zanr("Triler");
        Zanr horor = new Zanr("Horor");
        Zanr komedija = new Zanr("Komedija");
        Zanr drama = new Zanr("Drama");
        Zanr scifi = new Zanr("Sci-fi");
        Zanr avantura = new Zanr("Avantura");

        film1.setNazivFilma("Guardians Of The Galaxy Vol. 3");
        film2.setNazivFilma("Fast And Furious 10");
        film3.setNazivFilma("The Little Mermaid");
        film4.setNazivFilma("The Super Mario Bros. Movie");
        film5.setNazivFilma("Boogeyman");
        film6.setNazivFilma("Hypnotic");
        film7.setNazivFilma("Sisu");
        film8.setNazivFilma("The Whale");
        film9.setNazivFilma("Transformers: Rise Of The Beasts");
        film10.setNazivFilma("Pacific Rim");
        film11.setNazivFilma("Killers Of The Flower Moon");
        film12.setNazivFilma("Interstellar");
        film13.setNazivFilma("Evil Dead Rise");

        film1.setZanr(Arrays.asList(akcija,komedija));
        film2.setZanr(Arrays.asList(akcija));
        film3.setZanr(Arrays.asList(avantura));
        film4.setZanr(Arrays.asList(komedija,avantura));
        film5.setZanr(Arrays.asList(horor,triler));
        film6.setZanr(Arrays.asList(akcija,triler));
        film7.setZanr(Arrays.asList(akcija));
        film8.setZanr(Arrays.asList(drama));
        film9.setZanr(Arrays.asList(akcija,avantura));
        film10.setZanr(Arrays.asList(akcija,avantura,scifi));
        film11.setZanr(Arrays.asList(drama));
        film12.setZanr(Arrays.asList(scifi));
        film13.setZanr(Arrays.asList(horor));

        akcija.setFilm(Arrays.asList(film1, film2, film6, film7, film9, film10));
        komedija.setFilm(Arrays.asList(film1, film4));
        avantura.setFilm(Arrays.asList(film2, film3, film9, film10));
        horor.setFilm(Arrays.asList(film5, film13));
        scifi.setFilm(Arrays.asList(film10, film12));
        drama.setFilm(Arrays.asList(film8, film11));
        triler.setFilm(Arrays.asList(film5, film6));

        film1.setDuration(149);
        film2.setDuration(141);
        film3.setDuration(135);
        film4.setDuration(92);
        film5.setDuration(98);
        film6.setDuration(93);
        film7.setDuration(91);
        film8.setDuration(117);
        film9.setDuration(127);
        film10.setDuration(131);
        film11.setDuration(206);
        film12.setDuration(169);
        film13.setDuration(96);

        film1.setOpis("Čuvari galaksije Vol. 3 prati Čuvare nakon 'Osvetnika: Endgame', sa istom grupom i alternativnom verzijom Gamore (Zoe Saldaña).");
        film2.setOpis("Godine 2011. Dante Reyes, sin narkobosa Hernana Reyesa, pokušava spriječiti Dominica 'Dom' Toretta i Briana O'Connera da ukradu trezor u kojem se nalazi bogatstvo njegovog oca.");
        film3.setOpis("Mala sirena prati princezu sirene Ariel koja je fascinirana ljudskim svijetom i sklapa dogovor s izdajničkom morskom vješticom Ursulom da zamijeni svoj glas za ljudske noge kako bi impresionirala princa Erica.");
        film4.setOpis("Film sadrži priču o porijeklu braće Maria i Luigija, italijansko-američkih vodoinstalatera koji su prebačeni u alternativni svijet i upleteni u bitku između Kraljevstva gljiva, predvođenog princezom Peach, i Koopas, predvođenih Bowserom.");
        film5.setOpis("Boogeyman je američki natprirodni horor film iz 2023. režirao Rob Savage po scenariju Scotta Becka, Bryana Woodsa i Marka Heymana.");
        film6.setOpis("Odlučan da pronađe svoju nestalu ćerku, detektiv iz Austina zatiče se kako kruži niz zečju rupu istražujući seriju zločina koji savijaju stvarnost gdje će na kraju dovesti u pitanje svoje najosnovnije pretpostavke o svemu i svakome u svom svijetu.");
        film7.setOpis("Sisu je istorijski akcioni film iz 2022. koji je napisao i režirao Jalmari Helander. Radnja filma smještena u finsku Laponiju tokom Drugog svjetskog rata, prati bivšeg legendarnog finskog komandosa i tragača za zlatom.");
        film8.setOpis("U filmu glume Brendan Fraser, Sadie Sink, Hong Chau, Ty Simpkins i Samantha Morton. Radnja prati povučenog, morbidno gojaznog učitelja engleskog koji pokušava da obnovi svoj odnos sa ćerkom tinejdžerkom.");
        film9.setOpis("Najprijatniji film o Transformersima od prvog. Transformers: Rise of the Beasts je uzbudljiv i akcijski nastavak Bumblebeeja koji uvodi nove frakcije robota i novu prijetnju Zemlji.");
        film10.setOpis("Radnja filma smještena je u budućnosti, kada je Zemlja u ratu s Kaiju, kolosalnim morskim čudovištima koja su izašla iz međudimenzionalnog portala na dnu Tihog okeana.");
        film11.setOpis("Ubice cvjetnog mjeseca priča priču o brutalnim ubistvima iza pokušaja bijelih doseljenika da odu posjed zemlje porodice Osage u Oklahomi, ispod kojih su ležala neka od najvrednijih naftnih polja na svijetu.");
        film12.setOpis("Interstellar govori o posljednjoj šansi Zemlje da pronađe nastanjivu planetu prije nego što nedostatak resursa dovede do izumiranja ljudske rase. Cooper ima zadatak da vodi misiju kroz crvotočinu kako bi pronašao nastanjivu planetu u drugoj galaksiji.");
        film13.setOpis("Evil Dead Rise je američki natprirodni horor film iz 2023. koji je napisao i režirao Lee Cronin. U filmu glume Lily Sullivan i Alyssa Sutherland kao dvije otuđene sestre koje pokušavaju preživjeti i spasiti svoju porodicu od mrtvih.");


        zanrRepository.save(akcija);
        zanrRepository.save(horor);
        zanrRepository.save(triler);
        zanrRepository.save(drama);
        zanrRepository.save(avantura);
        zanrRepository.save(komedija);
        zanrRepository.save(scifi);

        filmRepository.save(film1);
        filmRepository.save(film2);
        filmRepository.save(film3);
        filmRepository.save(film4);
        filmRepository.save(film5);
        filmRepository.save(film6);
        filmRepository.save(film7);
        filmRepository.save(film8);
        filmRepository.save(film9);
        filmRepository.save(film10);
        filmRepository.save(film11);
        filmRepository.save(film12);
        filmRepository.save(film13);
    }
}
