package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table
public class Popust {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "karta_id")
    private Karta karta;

    @Column
    //@NotEmpty(message = "Vrijednost popusta ne može biti prazna!")
    @Positive(message = "Vrijednost popusta ne može biti negativna!")
    double vrijednostPopusta;

    public Popust() {
    }

    public Popust(int ID, Karta karta, double vrijednostPopusta) {
        this.ID = ID;
        this.karta = karta;
        this.vrijednostPopusta = vrijednostPopusta;
    }

    public Popust(Karta karta, double vrijednostPopusta) {
        this.karta = karta;
        this.vrijednostPopusta = vrijednostPopusta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }

    public double getVrijednostPopusta() {
        return vrijednostPopusta;
    }

    public void setVrijednostPopusta(double vrijednostPopusta) {
        this.vrijednostPopusta = vrijednostPopusta;
    }
}
