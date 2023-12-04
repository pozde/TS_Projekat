package ba.tim2.RezervacijaKarata;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Entity.Sala;
import ba.tim2.RezervacijaKarata.Entity.Zanr;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import ba.tim2.RezervacijaKarata.Repository.SalaRepository;
import ba.tim2.RezervacijaKarata.Repository.ZanrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInjector implements CommandLineRunner {

    private final FilmRepository filmRepository;
    private final ZanrRepository zanrRepository;
    private final SalaRepository salaRepository;

    private boolean praznaBaza() {
        return filmRepository.findAll().isEmpty() &&
                zanrRepository.findAll().isEmpty() &&
                salaRepository.findAll().isEmpty();
    }

    @Override
    public void run(String... args) {
        if (praznaBaza()) {
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

            Sala sala1 = new Sala();
            Sala sala2 = new Sala();
            Sala sala3 = new Sala();
            Sala sala4 = new Sala();
            Sala sala5 = new Sala();

            sala1.setPocetakProjekcije(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)));
            sala2.setPocetakProjekcije(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 30)));
            sala3.setPocetakProjekcije(LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0)));
            sala4.setPocetakProjekcije(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)));
            sala5.setPocetakProjekcije(LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 30)));

            film1.setSale(Arrays.asList(sala3, sala5));
            film2.setSale(Arrays.asList(sala1, sala4));
            film3.setSale(Arrays.asList(sala2));
            film4.setSale(Arrays.asList(sala1));
            film5.setSale(Arrays.asList(sala5));
            film6.setSale(Arrays.asList(sala3, sala4));
            film7.setSale(Arrays.asList(sala3, sala5));
            film8.setSale(Arrays.asList(sala1, sala4));
            film9.setSale(Arrays.asList(sala2, sala3));
            film10.setSale(Arrays.asList(sala4, sala5));
            film11.setSale(Arrays.asList(sala3, sala5));
            film12.setSale(Arrays.asList(sala1, sala3));
            film13.setSale(Arrays.asList(sala5));

            sala1.setFilmovi(Arrays.asList(film2, film4, film8, film12));
            sala2.setFilmovi(Arrays.asList(film3, film9));
            sala3.setFilmovi(Arrays.asList(film1, film6, film7, film9, film11, film12));
            sala4.setFilmovi(Arrays.asList(film2, film6, film8, film10));
            sala5.setFilmovi(Arrays.asList(film1, film5, film7, film10, film11, film13));

            sala1.setBrojSale(1);
            sala2.setBrojSale(2);
            sala3.setBrojSale(3);
            sala4.setBrojSale(4);
            sala5.setBrojSale(5);


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

            film1.setZanrovi(Arrays.asList(akcija, komedija));
            film2.setZanrovi(Arrays.asList(akcija));
            film3.setZanrovi(Arrays.asList(avantura));
            film4.setZanrovi(Arrays.asList(komedija, avantura));
            film5.setZanrovi(Arrays.asList(horor, triler));
            film6.setZanrovi(Arrays.asList(akcija, triler));
            film7.setZanrovi(Arrays.asList(akcija));
            film8.setZanrovi(Arrays.asList(drama));
            film9.setZanrovi(Arrays.asList(akcija, avantura));
            film10.setZanrovi(Arrays.asList(akcija, avantura, scifi));
            film11.setZanrovi(Arrays.asList(drama));
            film12.setZanrovi(Arrays.asList(scifi));
            film13.setZanrovi(Arrays.asList(horor));

            film1.setPosterPath("https://www.themoviedb.org/t/p/original/r2J02Z2OpNTctfOSN1Ydgii51I3.jpg");
            film2.setPosterPath("https://www.themoviedb.org/t/p/original/jmdi62j5uJV60EtYY8zsE1LRYEf.jpg");
            film3.setPosterPath("https://www.themoviedb.org/t/p/original/ym1dxyOk4jFcSl4Q2zmRrA5BEEN.jpg");
            film4.setPosterPath("https://www.themoviedb.org/t/p/original/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg");
            film5.setPosterPath("https://www.themoviedb.org/t/p/original/156VTRLwgoUQZnmGmqtuhKuK8vy.jpg");
            film6.setPosterPath("https://www.themoviedb.org/t/p/original/3IhGkkalwXguTlceGSl8XUJZOVI.jpg");
            film7.setPosterPath("https://www.themoviedb.org/t/p/original/zehhtcLHnTOE0lw9rLTIRsJeeW2.jpg");
            film8.setPosterPath("https://www.themoviedb.org/t/p/original/jQ0gylJMxWSL490sy0RrPj1Lj7e.jpg");
            film9.setPosterPath("https://www.themoviedb.org/t/p/original/gPbM0MK8CP8A174rmUwGsADNYKD.jpg");
            film10.setPosterPath("https://www.themoviedb.org/t/p/original/8wo4eN8dWKaKlxhSvBz19uvj8gA.jpg");
            film11.setPosterPath("https://www.themoviedb.org/t/p/original/yKzDnjsuLhh9B4xc0vNgz1YzYsT.jpg");
            film12.setPosterPath("https://www.themoviedb.org/t/p/original/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg");
            film13.setPosterPath("https://www.themoviedb.org/t/p/original/5ik4ATKmNtmJU6AYD0bLm56BCVM.jpg");

            akcija.setFilmovi(Arrays.asList(film1, film2, film6, film7, film9, film10));
            komedija.setFilmovi(Arrays.asList(film1, film4));
            avantura.setFilmovi(Arrays.asList(film2, film3, film9, film10));
            horor.setFilmovi(Arrays.asList(film5, film13));
            scifi.setFilmovi(Arrays.asList(film10, film12));
            drama.setFilmovi(Arrays.asList(film8, film11));
            triler.setFilmovi(Arrays.asList(film5, film6));

            film1.setTrajanje(149);
            film2.setTrajanje(141);
            film3.setTrajanje(135);
            film4.setTrajanje(92);
            film5.setTrajanje(98);
            film6.setTrajanje(93);
            film7.setTrajanje(91);
            film8.setTrajanje(117);
            film9.setTrajanje(127);
            film10.setTrajanje(131);
            film11.setTrajanje(206);
            film12.setTrajanje(169);
            film13.setTrajanje(96);

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
}
