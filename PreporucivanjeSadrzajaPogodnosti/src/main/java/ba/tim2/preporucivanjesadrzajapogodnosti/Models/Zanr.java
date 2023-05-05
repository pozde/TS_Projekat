package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

    @ManyToOne
    //@JoinColumn(name="film_id", nullable = false)
    @JoinColumn(name = "film_id")
    private Film film;

    @OneToOne(mappedBy = "zanr")
    private PreporukaFilma preporukaFilma;

    public Zanr() {
    }

    public Zanr(String naziv) {
        this.naziv = naziv;
    }

    public Zanr(int ID, String naziv, Film film, PreporukaFilma preporukaFilma) {
        this.ID = ID;
        this.naziv = naziv;
        this.film = film;
        this.preporukaFilma = preporukaFilma;
    }

    public Zanr(String naziv, Film film, PreporukaFilma preporukaFilma) {
        this.naziv = naziv;
        this.film = film;
        this.preporukaFilma = preporukaFilma;
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public PreporukaFilma getPreporukaFilma() {
        return preporukaFilma;
    }

    public void setPreporukaFilma(PreporukaFilma preporukaFilma) {
        this.preporukaFilma = preporukaFilma;
    }
}
