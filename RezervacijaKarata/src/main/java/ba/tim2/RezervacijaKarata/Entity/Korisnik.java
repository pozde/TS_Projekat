package ba.tim2.RezervacijaKarata.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Korisnik {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column
    @NotBlank(message = "Ime ne smije biti prazno!")
    @Size(min = 3, max = 255, message = "Ime mora imati barem 3 karaktera")
    private String ime;
    @Column
    @NotBlank(message = "Prezime ne smije biti prazno!")
    @Size(min = 2, max = 255, message = "Prezime mora imati barem 2 karaktera")
    private String prezime;
    @Column(name = "datum_rodjenja")
    @Past(message = "Datum mora biti u prošlosti!")
    @JsonFormat(pattern = "dd.MM.yyyy.")
    private LocalDate datumRodjenja;
    @Column
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Mail ne smije biti prazan")
    private String email;
    @Column(name = "broj_telefona")
    private String brojTelefona;
    @Column
    private String spol;
    @OneToMany(mappedBy = "korisnik")
    private List<Karte> karte;

    public Korisnik() {
    }

    public Korisnik(int id, String ime, String prezime, LocalDate datumRodjenja, String email, String brojTelefona, String spol) {
        this.ID = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.spol = spol;
    }

    public Korisnik(String ime, String prezime, LocalDate datumRodjenja, String email, String brojTelefona, String spol) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.spol = spol;
    }

    public Korisnik(String ime, String prezime, LocalDate datumRodjenja, String brojTelefona, String spol) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.brojTelefona = brojTelefona;
        this.spol = spol;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Karte> getKarte() {
        return karte;
    }

    public void setKarte(List<Karte> karte) {
        this.karte = karte;
    }
}
