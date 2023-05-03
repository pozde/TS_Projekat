package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import javax.persistence.*;

@Entity
@Table
public class Karta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="korisnik_id", nullable = false)
    private Korisnik korisnik;

    @OneToOne(mappedBy = "karta")
    private Film film;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "popust_id", referencedColumnName = "id")
    private Popust popust;

    public Karta() {

    }

    public Karta(int id, Korisnik korisnik, Film film, Popust popust) {
        this.id = id;
        this.korisnik = korisnik;
        this.film = film;
        this.popust = popust;
    }

    public Karta(Korisnik korisnik, Film film, Popust popust) {
        this.korisnik = korisnik;
        this.film = film;
        this.popust = popust;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Popust getPopust() {
        return popust;
    }

    public void setPopust(Popust popust) {
        this.popust = popust;
    }
}
