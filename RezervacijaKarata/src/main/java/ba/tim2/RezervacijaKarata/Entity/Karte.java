package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Karte {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int brojKarte;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="korisnik_id", nullable = true)
    private Korisnik korisnik;

    @OneToOne
    @JoinColumn(name="sjediste_id", nullable=false)
    private Sjedista sjedista;

    @ManyToOne
//    @JsonIgnoreProperties("terminSaProjekcijama")
    @JoinColumn(name="termin_projekcija_id", nullable=false)
    private TerminSaProjekcijom terminSaProjekcijom;

    public Karte() {
    }

    public Karte(int id, int brojKarte) {
        this.id = id;
        this.brojKarte = brojKarte;
    }

    public Karte(int brojKarte, Korisnik korisnik, Sjedista sjedista, TerminSaProjekcijom terminSaProjekcijom) {
        this.brojKarte = brojKarte;
        this.korisnik = korisnik;
        this.sjedista = sjedista;
        this.terminSaProjekcijom = terminSaProjekcijom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Sjedista getSjedista() {
        return sjedista;
    }

    public void setSjedista(Sjedista sjedista) {
        this.sjedista = sjedista;
    }

    public TerminSaProjekcijom getTerminSaProjekcijom() {
        return terminSaProjekcijom;
    }

    public void setTerminSaProjekcijom(TerminSaProjekcijom terminSaProjekcijom) {
        this.terminSaProjekcijom = terminSaProjekcijom;
    }
}
