package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;
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
    @Column
    @Past(message = "Datum mora biti u prošlosti!")
    private Date datumRodjenja;

    @Column(unique = true)
    @Email(message = "E-mail nije validan!", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "E-mail ne smije biti prazan!")
    private String email;
    @Column
    private String brojTelefona;
    @Column
    private String spol;

    @ManyToOne
    //@JoinColumn(name="clanarina_id", nullable = false)
    @JoinColumn(name = "clanarina_id")
    private Clanarina clanarina;

    @OneToMany(mappedBy = "korisnik")
    private List<Karta> karte;

    @OneToMany(mappedBy = "korisnik")
    private List<PreporukaFilma> preporukeFilmova;

    public Korisnik() {
    }

    public Korisnik(int id, String ime, String prezime, Date datumRodjenja,
                    String email, String brojTelefona, String spol, Clanarina clanarina,
                    List<Karta> karte, List<PreporukaFilma> preporukeFilmova) {
        this.ID = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.spol = spol;
        this.clanarina = clanarina;
        this.karte = karte;
        this.preporukeFilmova = preporukeFilmova;
    }

    public Korisnik(String ime, String prezime, Date datumRodjenja,
                    String email, String brojTelefona, String spol, Clanarina clanarina,
                    List<Karta> karte, List<PreporukaFilma> preporukeFilmova) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.spol = spol;
        this.clanarina = clanarina;
        this.karte = karte;
        this.preporukeFilmova = preporukeFilmova;
    }

    public Korisnik(int id, String ime, String prezime, Date datumRodjenja, String email, String brojTelefona, String spol) {
        this.ID = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.spol = spol;
    }

    public Korisnik(String ime, String prezime, Date datumRodjenja, String email, String brojTelefona, String spol) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.spol = spol;
    }

    public Korisnik(String ime, String prezime, Date datumRodjenja, String email, String brojTelefona, String spol, Clanarina clanarina) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.spol = spol;
        this.clanarina = clanarina;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
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
}
