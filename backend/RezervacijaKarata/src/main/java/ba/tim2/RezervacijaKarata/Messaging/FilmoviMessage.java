package ba.tim2.RezervacijaKarata.Messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmoviMessage {
    @JsonProperty("id")
    private int film_id;
    @JsonProperty("nazivFilma")
    private String nazivFilma;


    public FilmoviMessage() {
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }
}
