package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Karta {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column
    private int brojKarte;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "korisnik_id", nullable = true)
    private Korisnik korisnik;

    @OneToOne(mappedBy = "karta")
    private Film Film;

    @OneToOne
    @JoinColumn(name = "sjediste_id", nullable = false)
    private Sjediste sjediste;

    public Karta() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBrojKarte() {
        return brojKarte;
    }

    public void setBrojKarte(int brojKarte) {
        this.brojKarte = brojKarte;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public ba.tim2.RezervacijaKarata.Entity.Film getFilm() {
        return Film;
    }

    public void setFilm(ba.tim2.RezervacijaKarata.Entity.Film film) {
        Film = film;
    }

    public Sjediste getSjediste() {
        return sjediste;
    }

    public void setSjediste(Sjediste sjediste) {
        this.sjediste = sjediste;
    }
}
