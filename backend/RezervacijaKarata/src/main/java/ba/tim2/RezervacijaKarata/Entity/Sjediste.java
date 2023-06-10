package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Sjediste {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column(nullable = false)
    private int brojSjedista;
    @OneToOne(mappedBy = "sjediste")
    @JsonIgnore
    private Karta karta;

    @ManyToOne
    //@JsonIgnore
    //@JsonIgnoreProperties("sjedista")
    @JoinColumn(name="sala_id", nullable = false)
    private Sala sala;

    public Sjediste() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
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

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }
}
