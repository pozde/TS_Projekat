package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.util.List;

@Entity
@Table
public class Film {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column
    @NotEmpty(message = "Naziv filma mora postojati!")
    private String nazivFilma;

    @Column
    private String opis;

    @Column(name = "poster_path")
    private String posterPath;


    @ManyToMany(mappedBy = "film")
    private List<Zanr> zanr;

    @Column
//    @NotEmpty(message = "Trajanje filma mora postojati!")
    private int duration;

    @OneToMany(mappedBy = "film")
    @JsonIgnore
    private List<TerminSaProjekcijom> terminSaProjekcijama;
    public Film() {

    }

    public Film(String naziv) {
        super();
        this.nazivFilma = naziv;
    }

    public Film(String naziv, int duration) {
        super();
        this.nazivFilma = naziv;
        this.duration = duration;
    }

    public Film(String naziv, int duration, String opis) {
        super();
        this.nazivFilma = naziv;
        this.duration = duration;
        this.opis = opis;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }

    public List<Zanr> getZanr() {
        return zanr;
    }

    public void setZanr(List<Zanr> zanr) {
        this.zanr = zanr;
    }

    public List<TerminSaProjekcijom> getTerminSaProjekcijama() {
        return terminSaProjekcijama;
    }

    public void setTerminSaProjekcijama(List<TerminSaProjekcijom> terminSaProjekcijama) {
        this.terminSaProjekcijama = terminSaProjekcijama;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}