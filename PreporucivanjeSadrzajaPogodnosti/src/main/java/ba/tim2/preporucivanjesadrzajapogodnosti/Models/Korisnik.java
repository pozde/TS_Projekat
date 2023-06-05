package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Korisnik {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column
    @NotEmpty(message = "Ime ne može biti prazno!")
    @Size(min = 3, max = 255, message = "Ime mora imati između 3 i 255 znakova!")
    private String ime;

    @Column
    @NotEmpty(message = "Prezime ne može biti prazno!")
    @Size(min = 3, max = 255, message = "Prezime mora imati između 3 i 255 znakova!")
    private String prezime;

    @Column(name = "datum_rodjenja")
    @Past(message = "Datum mora biti u prošlosti!")
    @JsonFormat(pattern = "dd.MM.yyyy.")
    private LocalDate datumRodjenja;

    @Column(name = "broj_telefona")
    private String brojTelefona;

    @Column
    private String spol;

    @Column
    @Email(message = "E-mail nije validan!", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "E-mail ne smije biti prazan!")
    private String email;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "clanarina_id")
    private Clanarina clanarina;

    @JsonIgnore
    @OneToMany(mappedBy = "korisnik")
    private List<Karta> karte = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "korisnik")
    private List<PreporukaFilma> preporukeFilmova = new ArrayList<>();

    public Korisnik() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Clanarina getClanarina() {
        return clanarina;
    }

    public void setClanarina(Clanarina clanarina) {
        this.clanarina = clanarina;
    }

    public List<Karta> getKarte() {
        return karte;
    }

    public void setKarte(List<Karta> karte) {
        this.karte = karte;
    }

    public List<PreporukaFilma> getPreporukeFilmova() {
        return preporukeFilmova;
    }

    public void setPreporukeFilmova(List<PreporukaFilma> preporukeFilmova) {
        this.preporukeFilmova = preporukeFilmova;
    }

    public void dodajKartu(Karta karta) {
        karte.add(karta);
    }

    public void dodajKarte(List<Karta> listaKarti) {
        karte.addAll(listaKarti);
    }
}
