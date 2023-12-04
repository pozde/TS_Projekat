package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Clanarina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "datum_isteka")
    @FutureOrPresent(message = "Datum isteka članarine mora ne smije biti u prošlosti!")
    @JsonFormat(pattern = "dd.MM.yyyy.")
    private LocalDate datumIsteka;

    @Column
    @NotEmpty(message = "Vrsta članarine ne smije biti prazna!")
    private String vrsta;

    @OneToMany(mappedBy = "clanarina")
    private List<Korisnik> korisnici = new ArrayList<>();

    public Clanarina() {
        // Default-ni konstruktor
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(LocalDate datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public void dodajKorisnika(Korisnik korisnik) {
        korisnici.add(korisnik);
    }

    public void dodajKorisnike(List<Korisnik> listaKorisnika) {
        korisnici.addAll(listaKorisnika);
    }
}
