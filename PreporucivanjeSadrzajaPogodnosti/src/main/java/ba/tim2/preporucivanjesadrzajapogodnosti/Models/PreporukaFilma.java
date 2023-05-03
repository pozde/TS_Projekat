package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import javax.persistence.*;

@Entity
@Table
public class PreporukaFilma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zanr_id", referencedColumnName = "id")
    private Zanr zanr;

    @ManyToOne
    @JoinColumn(name="korisnik_id", nullable = false)
    private Korisnik korisnik;

    public PreporukaFilma() {

    }

    public PreporukaFilma(int id, Zanr zanr, Korisnik korisnik) {
        this.id = id;
        this.zanr = zanr;
        this.korisnik = korisnik;
    }

    public PreporukaFilma(Zanr zanr, Korisnik korisnik) {
        this.zanr = zanr;
        this.korisnik = korisnik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
