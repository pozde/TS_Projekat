package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Zanr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column
    @NotEmpty(message = "Naziv žanra ne smije biti prazan!")
    @Size(min = 3, max = 255, message = "Naziv žanra mora imati između 3 i 255 znakova!")
    private String naziv;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "zanrovi")
    private List<Film> filmovi = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "preporukaFilma_id")
    private PreporukaFilma preporukaFilma;

    public Zanr() {
    }

    public Zanr(String naziv) {
        this.naziv = naziv;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Film> getFilmovi() {
        return filmovi;
    }

    public void setFilmovi(List<Film> filmovi) {
        this.filmovi = filmovi;
    }

    public PreporukaFilma getPreporukaFilma() {
        return preporukaFilma;
    }

    public void setPreporukaFilma(PreporukaFilma preporukaFilma) {
        this.preporukaFilma = preporukaFilma;
    }

    public void dodajFilm(Film film) {
        filmovi.add(film);
    }

    public void dodajFilmove(List<Film> listaFilmova) {
        filmovi.addAll(listaFilmova);
    }
}
