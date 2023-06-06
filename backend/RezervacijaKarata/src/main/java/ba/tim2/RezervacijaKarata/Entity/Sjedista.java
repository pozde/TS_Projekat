package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Sjedista {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int brojSjedista;
    @OneToOne(mappedBy = "sjedista")
    @JsonIgnore
    private Karte karta;

    @ManyToOne
    @JsonIgnore
    @JsonIgnoreProperties("sjedista")
    @JoinColumn(name="sala_id", nullable=false)
    private Sala sala;

    public Sjedista() {
    }

    public Sjedista(int id, int brojSjedista) {
        this.id = id;
        this.brojSjedista = brojSjedista;
    }

    public Sjedista(int brojSjedista, Sala sala) {
        super();
        this.brojSjedista = brojSjedista;
        this.sala = sala;
    }

    public Sjedista(int id, int brojSjedista, Sala sala) {
        this.id = id;
        this.brojSjedista = brojSjedista;
        this.sala = sala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrojSjedista() {
        return brojSjedista;
    }

    public void setBrojSjedista(int brojSjedista) {
        this.brojSjedista = brojSjedista;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Karte getKarta() {
        return karta;
    }

    public void setKarta(Karte karta) {
        this.karta = karta;
    }
}
