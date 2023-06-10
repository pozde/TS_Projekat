package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Sala {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int brojSale;

    @OneToMany(mappedBy = "sala")
    @JsonIgnore
    private List<Sjediste> sjedista;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    private List<Film> filmovi = new ArrayList<>();

    public Sala() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrojSale() {
        return brojSale;
    }

    public void setBrojSale(int brojSale) {
        this.brojSale = brojSale;
    }

    public List<Sjediste> getSjedista() {
        return sjedista;
    }

    public void setSjedista(List<Sjediste> sjedista) {
        this.sjedista = sjedista;
    }

    public List<Film> getFilmovi() {
        return filmovi;
    }

    public void setFilmovi(List<Film> filmovi) {
        this.filmovi = filmovi;
    }
}
