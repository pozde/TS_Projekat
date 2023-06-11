package ba.tim2.upravljanjefilmovimakartama.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Karta {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column
    private int brojKarte;



    @OneToOne(mappedBy = "karta")
    private Film Film;

    @OneToOne
    @JoinColumn(name = "sjediste_id", nullable = false)
    private Sjediste sjediste;

    public Karta() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBrojKarte() {
        return brojKarte;
    }

    public void setBrojKarte(int brojKarte) {
        this.brojKarte = brojKarte;
    }





    public Film getFilm() {
        return Film;
    }

    public void setFilm(Film film) {
        Film = film;
    }

    public Sjediste getSjediste() {
        return sjediste;
    }

    public void setSjediste(Sjediste sjediste) {
        this.sjediste = sjediste;
    }
}
