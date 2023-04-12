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
    private int id;
    @Column
    @NotEmpty(message = "Naziv filma mora postojati!")
    private String nazivFilma;

    @Column
    @NotEmpty(message = "Trajanje filma mora postojati!")
    private int duration;

    @OneToMany(mappedBy = "film")
    @JsonIgnore
    private List<TerminSaProjekcijom> terminSaProjekcijama;
    public Film() {

    }

    public Film(String naziv, int duration) {
        super();
        this.nazivFilma = naziv;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<TerminSaProjekcijom> getTerminSaProjekcijama() {
        return terminSaProjekcijama;
    }

    public void setTerminSaProjekcijama(List<TerminSaProjekcijom> terminSaProjekcijama) {
        this.terminSaProjekcijama = terminSaProjekcijama;
    }
}