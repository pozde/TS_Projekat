package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sala {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    private int brojSale;

    @Column
    @JsonFormat(pattern = "HH:mm")
    private LocalDateTime pocetakProjekcije;

    @OneToMany(mappedBy = "sala")
    private List<Sjediste> sjedista = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    private List<Film> filmovi = new ArrayList<>();

    public Sala() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
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

    public void dodajSjediste(Sjediste sjediste) {
        sjedista.add(sjediste);
    }

    public void dodajFilm(Film film) {
        filmovi.add(film);
    }

    public void obrisiFilm(Film film) {
        filmovi.remove(film);
    }

    public LocalDateTime getPocetakProjekcije() {
        return pocetakProjekcije;
    }

    public void setPocetakProjekcije(LocalDateTime pocetakProjekcije) {
        this.pocetakProjekcije = pocetakProjekcije;
    }
}
