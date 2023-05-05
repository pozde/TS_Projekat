package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column
    @NotEmpty(message = "Naziv filma ne može biti prazan!")
    @Size(min = 3, max = 255, message = "Naziv filma mora imati između 3 i 255 znakova!")
    private String nazivFilma;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "karta_id", referencedColumnName = "ID")
    private Karta karta;

    @OneToMany(mappedBy = "film")
    private List<Zanr> zanrovi;

    public Film() {
    }

    public Film(String naziv) {
        this.nazivFilma = naziv;
    }

    public Film(int ID, String nazivFilma, Karta karta, List<Zanr> zanrovi) {
        this.ID = ID;
        this.nazivFilma = nazivFilma;
        this.karta = karta;
        this.zanrovi = zanrovi;
    }

    public Film(String nazivFilma, Karta karta, List<Zanr> zanrovi) {
        this.nazivFilma = nazivFilma;
        this.karta = karta;
        this.zanrovi = zanrovi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }

    public List<Zanr> getZanrovi() {
        return zanrovi;
    }

    public void setZanrovi(List<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }
}
