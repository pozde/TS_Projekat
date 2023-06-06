package ba.tim2.RezervacijaKarata.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class Sala {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int brojSale;

    @OneToMany(mappedBy = "sala")
    @JsonIgnore
    private List<TerminSaProjekcijom> terminSaProjekcijom;
    @OneToMany(mappedBy = "sala")
    private List<Sjedista> sjedista;

    public Sala(int id, int brojSale) {
        this.id = id;
        this.brojSale = brojSale;
    }
    public Sala(int brojSale) {
        super();
        this.brojSale = brojSale;
    }

    public Sala() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrojSale() {
        return brojSale;
    }

    public void setBrojSale(int brojSale) {
        this.brojSale = brojSale;
    }

    public List<TerminSaProjekcijom> getTerminSaProjekcijom() {
        return terminSaProjekcijom;
    }

    public void setTerminSaProjekcijom(List<TerminSaProjekcijom> terminSaProjekcijom) {
        this.terminSaProjekcijom = terminSaProjekcijom;
    }

    public List<Sjedista> getSjedista() {
        return sjedista;
    }

    public void setSjedista(List<Sjedista> sjedista) {
        this.sjedista = sjedista;
    }
}
