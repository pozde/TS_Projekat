package ba.tim2.RezervacijaKarata.Entity;

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

    @Column(name = "vrijednost_popusta")
    @Positive(message = "Vrijednost popusta ne može biti negativna!")
    double vrijednostPopusta;

    public Popust() {
        // Default-ni konstruktor
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
