package ba.tim2.preporucivanjesadrzajapogodnosti;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.*;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


@Service
public class PreporucivanjeSadrzajaPogodnostiService {
    @Autowired
    private ClanarinaRepository clanarinaRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private KartaRepository kartaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PopustRepository popustRepository;

    @Autowired
    private PreporukaFilmaRepository preporukaFilmaRepository;

    @Autowired
    private ZanrRepository zanrRepository;

    @Transactional
    public void insertStaticData() {
        Korisnik korisnik1 = new Korisnik();
        Korisnik korisnik2 = new Korisnik();
        Korisnik korisnik3 = new Korisnik();

        Zanr zanr1 = new Zanr();
        Zanr zanr2 = new Zanr();
        Zanr zanr3 = new Zanr();
        Zanr zanr4 = new Zanr();
        Zanr zanr5 = new Zanr();
        Zanr zanr6 = new Zanr();
        Zanr zanr7 = new Zanr();
        Zanr zanr8 = new Zanr();
        Zanr zanr9 = new Zanr();

        Film film1 = new Film();
        Film film2 = new Film();
        Film film3 = new Film();
        Film film4 = new Film();
        Film film5 = new Film();
        Film film6 = new Film();
        Film film7 = new Film();
        Film film8 = new Film();
        Film film9 = new Film();

        Clanarina clanarina1 = new Clanarina();
        Clanarina clanarina2 = new Clanarina();

        Karta karta1 = new Karta();
        Karta karta2 = new Karta();
        Karta karta3 = new Karta();
        Karta karta4 = new Karta();
        Karta karta5 = new Karta();
        Karta karta6 = new Karta();
        Karta karta7 = new Karta();
        Karta karta8 = new Karta();
        Karta karta9 = new Karta();

        Popust popust1 = new Popust();
        Popust popust2 = new Popust();
        Popust popust3 = new Popust();
        Popust popust4 = new Popust();
        Popust popust5 = new Popust();
        Popust popust6 = new Popust();
        Popust popust7 = new Popust();
        Popust popust8 = new Popust();
        Popust popust9 = new Popust();


        korisnik1.setIme("Muhamed");
        korisnik1.setPrezime("Borovac");
        korisnik1.setDatumRodjenja(new Date(100, Calendar.FEBRUARY, 8));
        korisnik1.setEmail("mborovac1@etf.unsa.ba");
        korisnik1.setBrojTelefona("0603239393");
        korisnik1.setSpol("M");

        korisnik2.setIme("Benjamin");
        korisnik2.setPrezime("Pašić");
        korisnik2.setDatumRodjenja(new Date(100, Calendar.FEBRUARY, 25));
        korisnik2.setEmail("bpasic1@etf.unsa.ba");
        korisnik2.setBrojTelefona("062623259");
        korisnik2.setSpol("M");

        korisnik3.setIme("Admir");
        korisnik3.setPrezime("Pozderac");
        korisnik3.setDatumRodjenja(new Date(100, Calendar.MARCH, 24));
        korisnik3.setEmail("apozderac1@etf.unsa.ba");
        korisnik3.setBrojTelefona("062788916");
        korisnik3.setSpol("M");


        zanr1.setNaziv("Akcija");
        zanr2.setNaziv("Avantura");
        zanr3.setNaziv("Komedija");
        zanr4.setNaziv("Drama");
        zanr5.setNaziv("Fantazija");
        zanr6.setNaziv("Horor");
        zanr7.setNaziv("Misterija");
        zanr8.setNaziv("Romantika");
        zanr9.setNaziv("Triler");

        film1.setNazivFilma("Iron Man 2");
        film1.dodajZanrove(Arrays.asList(zanr1, zanr2, zanr4));
        zanr1.dodajFilm(film1);
        zanr2.dodajFilm(film1);
        zanr4.dodajFilm(film1);

        film2.setNazivFilma("The Shawshank Redemption");
        film2.dodajZanrove(Arrays.asList(zanr3, zanr4, zanr5));
        zanr3.dodajFilm(film2);
        zanr4.dodajFilm(film2);
        zanr5.dodajFilm(film2);

        film3.setNazivFilma("Pulp Fiction");
        film3.dodajZanrove(Arrays.asList(zanr1, zanr6, zanr7));
        zanr1.dodajFilm(film3);
        zanr6.dodajFilm(film3);
        zanr7.dodajFilm(film3);

        film4.setNazivFilma("Fight Club");
        film4.dodajZanrove(Arrays.asList(zanr3, zanr8, zanr9));
        zanr3.dodajFilm(film4);
        zanr8.dodajFilm(film4);
        zanr9.dodajFilm(film4);

        film5.setNazivFilma("Inception");
        film5.dodajZanrove(Arrays.asList(zanr2, zanr5, zanr6));
        zanr2.dodajFilm(film5);
        zanr5.dodajFilm(film5);
        zanr6.dodajFilm(film5);

        film6.setNazivFilma("Seven");
        film6.dodajZanrove(Arrays.asList(zanr7, zanr8, zanr9));
        zanr7.dodajFilm(film6);
        zanr8.dodajFilm(film6);
        zanr9.dodajFilm(film6);

        film7.setNazivFilma("Interstellar");
        film7.dodajZanrove(Arrays.asList(zanr1, zanr6, zanr9));
        zanr1.dodajFilm(film7);
        zanr6.dodajFilm(film7);
        zanr9.dodajFilm(film7);

        film8.setNazivFilma("Whiplash");
        film8.dodajZanrove(Arrays.asList(zanr3, zanr5, zanr8));
        zanr3.dodajFilm(film8);
        zanr5.dodajFilm(film8);
        zanr8.dodajFilm(film8);

        film9.setNazivFilma("Parasite");
        film9.dodajZanrove(Arrays.asList(zanr2, zanr4, zanr7));
        zanr2.dodajFilm(film9);
        zanr4.dodajFilm(film9);
        zanr7.dodajFilm(film9);


        clanarina1.setDatumIsteka(new Date(2023, Calendar.MARCH, 4));
        clanarina1.setVrsta("Porodična");
        clanarina1.dodajKorisnike(Arrays.asList(korisnik1, korisnik3));
        korisnik1.setClanarina(clanarina1);
        korisnik3.setClanarina(clanarina1);

        clanarina2.setDatumIsteka(new Date(2022, Calendar.DECEMBER, 25));
        clanarina2.setVrsta("Studentska");
        clanarina2.dodajKorisnika(korisnik2);
        korisnik2.setClanarina(clanarina2);


        karta1.setKorisnik(korisnik1);
        korisnik1.dodajKartu(karta1);
        karta1.setFilm(film1);
        film1.setKarta(karta1);

        karta2.setKorisnik(korisnik1);
        korisnik1.dodajKartu(karta2);
        karta2.setFilm(film2);
        film2.setKarta(karta2);

        karta3.setKorisnik(korisnik1);
        korisnik1.dodajKartu(karta3);
        karta3.setFilm(film8);
        film8.setKarta(karta3);

        karta4.setKorisnik(korisnik2);
        korisnik2.dodajKartu(karta4);
        karta4.setFilm(film9);
        film9.setKarta(karta4);

        karta5.setKorisnik(korisnik2);
        korisnik2.dodajKartu(karta5);
        karta5.setFilm(film4);
        film4.setKarta(karta5);

        karta6.setKorisnik(korisnik2);
        korisnik2.dodajKartu(karta6);
        karta6.setFilm(film3);
        film3.setKarta(karta6);

        karta7.setKorisnik(korisnik3);
        korisnik3.dodajKartu(karta7);
        karta7.setFilm(film5);
        film5.setKarta(karta7);

        karta8.setKorisnik(korisnik3);
        korisnik3.dodajKartu(karta8);
        karta8.setFilm(film6);
        film6.setKarta(karta8);

        karta9.setKorisnik(korisnik3);
        korisnik3.dodajKartu(karta9);
        karta9.setFilm(film7);
        film7.setKarta(karta9);


        popust1.setVrijednostPopusta(0.5);
        popust1.setKarta(karta1);
        karta1.setPopust(popust1);

        popust2.setVrijednostPopusta(0.25);
        popust2.setKarta(karta2);
        karta2.setPopust(popust2);

        popust3.setVrijednostPopusta(0.2);
        popust3.setKarta(karta3);
        karta3.setPopust(popust3);

        popust4.setVrijednostPopusta(0.1);
        popust4.setKarta(karta4);
        karta4.setPopust(popust4);

        popust5.setVrijednostPopusta(0.75);
        popust5.setKarta(karta5);
        karta5.setPopust(popust5);

        popust6.setVrijednostPopusta(0.33);
        popust6.setKarta(karta6);
        karta6.setPopust(popust6);

        popust7.setVrijednostPopusta(0.66);
        popust7.setKarta(karta7);
        karta7.setPopust(popust7);

        popust8.setVrijednostPopusta(0.9);
        popust8.setKarta(karta8);
        karta8.setPopust(popust8);

        popust9.setVrijednostPopusta(0.8);
        popust9.setKarta(karta9);
        karta9.setPopust(popust9);


        clanarinaRepository.save(clanarina1);
        clanarinaRepository.save(clanarina2);

        filmRepository.save(film1);
        filmRepository.save(film2);
        filmRepository.save(film3);
        filmRepository.save(film4);
        filmRepository.save(film5);
        filmRepository.save(film6);
        filmRepository.save(film7);
        filmRepository.save(film8);
        filmRepository.save(film9);

        kartaRepository.save(karta1);
        kartaRepository.save(karta2);
        kartaRepository.save(karta3);
        kartaRepository.save(karta4);
        kartaRepository.save(karta5);
        kartaRepository.save(karta6);
        kartaRepository.save(karta7);
        kartaRepository.save(karta8);
        kartaRepository.save(karta9);

        korisnikRepository.save(korisnik1);
        korisnikRepository.save(korisnik2);
        korisnikRepository.save(korisnik3);

        popustRepository.save(popust1);
        popustRepository.save(popust2);
        popustRepository.save(popust3);
        popustRepository.save(popust4);
        popustRepository.save(popust5);
        popustRepository.save(popust6);
        popustRepository.save(popust7);
        popustRepository.save(popust8);
        popustRepository.save(popust9);

        zanrRepository.save(zanr1);
        zanrRepository.save(zanr2);
        zanrRepository.save(zanr3);
        zanrRepository.save(zanr4);
        zanrRepository.save(zanr5);
        zanrRepository.save(zanr6);
        zanrRepository.save(zanr7);
        zanrRepository.save(zanr8);
        zanrRepository.save(zanr9);
    }
}
