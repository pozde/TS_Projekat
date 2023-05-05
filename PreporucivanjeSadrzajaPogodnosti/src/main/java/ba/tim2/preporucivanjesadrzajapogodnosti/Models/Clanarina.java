package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Clanarina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column
    @FutureOrPresent(message = "Datum isteka članarine mora ne smije biti u prošlosti!")
    private Date datumIsteka;

    @Column
    @NotEmpty(message = "Vrsta članarine ne smije biti prazna!")
    private String vrsta;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "clanarina")
    private List<Korisnik> korisnici;

    public Clanarina() {
    }

    public Clanarina(Date datumIsteka, String vrsta) {
        //korisnici = new ArrayList<>();
        this.datumIsteka = datumIsteka;
        this.vrsta = vrsta;
    }

    public Clanarina(int ID, Date datumIsteka, String vrsta, List<Korisnik> korisnici) {
        this.ID = ID;
        this.datumIsteka = datumIsteka;
        this.vrsta = vrsta;
        this.korisnici = korisnici;
    }

    public Clanarina(Date datumIsteka, String vrsta, List<Korisnik> korisnici) {
        this.datumIsteka = datumIsteka;
        this.vrsta = vrsta;
        this.korisnici = korisnici;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
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
}
