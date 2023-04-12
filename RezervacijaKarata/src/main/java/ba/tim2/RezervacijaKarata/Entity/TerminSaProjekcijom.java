package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class TerminSaProjekcijom {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private LocalDateTime pocetakProjekcije;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="sala_id", nullable=false)
    private Sala sala;

    //@JoinColumn(name="film_id", nullable=false)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="film_id", referencedColumnName="id"),
            @JoinColumn(name="duration_film", referencedColumnName="duration")
    })
    private Film film;

    @JsonIgnore
    @OneToMany(mappedBy = "terminSaProjekcijom")
    private List<Karte> karte;

    public TerminSaProjekcijom() {
    }

    public TerminSaProjekcijom(int id, LocalDateTime pocetakProjekcije) {
        this.id = id;
        this.pocetakProjekcije = pocetakProjekcije;
    }

    public TerminSaProjekcijom(LocalDateTime pocetakProjekcije, Sala sala, Film film) {
        this.pocetakProjekcije = pocetakProjekcije;
        this.sala = sala;
        this.film = film;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDatumProjekcije() {
        return pocetakProjekcije;
    }

    public void setDatumProjekcije(LocalDateTime pocetakProjekcije) {
        this.pocetakProjekcije = pocetakProjekcije;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Karte> getKarte() {
        return karte;
    }

    public void setKarte(List<Karte> karte) {
        this.karte = karte;
    }

}
