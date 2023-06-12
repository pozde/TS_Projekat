package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column(name = "naziv_filma")
    @NotEmpty(message = "Naziv filma ne može biti prazan!")
    @Size(min = 2, max = 255, message = "Naziv filma mora imati između 3 i 255 znakova!")
    private String nazivFilma;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "karta_id")
    private Karta karta;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_zanrovi",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "zanr_id")
    )
    private List<Zanr> zanrovi = new ArrayList<>();

    public Film() {
        // Default-ni konstruktor
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

    public void dodajZanr(Zanr zanr) {
        zanrovi.add(zanr);
    }

    public void dodajZanrove(List<Zanr> listaZanrova) {
        zanrovi.addAll(listaZanrova);
    }
}
