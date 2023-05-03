package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Entity
@Table
public class Popust {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "popust")
    private Karta karta;

    @Column
    @NotEmpty(message = "Vrijednost popusta ne može biti prazna!")
    @Positive(message = "Vrijednost popusta ne može biti negativna!")
    double vrijednostPopusta;

    public Popust() {
    }

    public Popust(int id, Karta karta, double vrijednostPopusta) {
        this.id = id;
        this.karta = karta;
        this.vrijednostPopusta = vrijednostPopusta;
    }

    public Popust(Karta karta, double vrijednostPopusta) {
        this.karta = karta;
        this.vrijednostPopusta = vrijednostPopusta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
