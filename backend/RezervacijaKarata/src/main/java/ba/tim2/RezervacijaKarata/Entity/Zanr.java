package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Zanr {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column
    private String nazivZanra;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "zanrovi")
    private List<Film> filmovi = new ArrayList<>();

    public Zanr() {
    }

    public Zanr(String nazivZanra) {
        this.nazivZanra = nazivZanra;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNazivZanra() {
        return nazivZanra;
    }

    public void setNazivZanra(String nazivZanra) {
        this.nazivZanra = nazivZanra;
    }

    public List<Film> getFilmovi() {
        return filmovi;
    }

    public void setFilmovi(List<Film> filmovi) {
        this.filmovi = filmovi;
    }

    public void obrisiFilm(Film film) {
        filmovi.remove(film);
    }

    public void dodajFilm(Film film) {
        filmovi.add(film);
    }
}
