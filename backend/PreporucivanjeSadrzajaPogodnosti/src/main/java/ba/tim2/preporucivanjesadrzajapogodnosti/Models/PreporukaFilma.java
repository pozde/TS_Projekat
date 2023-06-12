package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import jakarta.persistence.*;

@Entity
@Table
public class PreporukaFilma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zanr_id", referencedColumnName = "ID")
    private Zanr zanr;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    public PreporukaFilma() {
        // Default-ni konstruktor
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
