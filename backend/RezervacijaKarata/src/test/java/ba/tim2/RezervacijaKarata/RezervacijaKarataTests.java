package ba.tim2.RezervacijaKarata;

import ba.tim2.RezervacijaKarata.Controller.FilmController;
import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Security.JwtService;
import ba.tim2.RezervacijaKarata.Service.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmController.class)
public class RezervacijaKarataTests {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Autowired
    private MockMvc mvc;

    @MockBean
    private FilmService filmService;
    @MockBean
    private JwtService jwtService;

    @Test
    public void testDodajFilm() throws Exception
    {

        Film film = new Film("Oppenheimer");
        filmService.spasiFilm(film);

        ResponseEntity<Film> responseEntity = new ResponseEntity<>(film, HttpStatus.CREATED);
        given(this.filmService.spasiFilm(ArgumentMatchers.any(Film.class))).willReturn(responseEntity);// (new ResponseEntity(film, HttpStatus.CREATED));

        mvc.perform(MockMvcRequestBuilders
                        .post("/dodajFilm")
                        .content(asJsonString(film))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nazivFilma").value(film.getNazivFilma()));
    }

    @Test
    public void testGetSizeFilm() throws Exception {
        Film film1 = new Film("Oppenheimer");
        Film film2 = new Film("The Dark Knight Rises");
        List<Film> filmovi = Arrays.asList(film1,film2);
        given(filmService.getSviFilmovi()).willReturn(filmovi);
        mvc.perform(MockMvcRequestBuilders.get("/filmovi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].nazivFilma").value(film1.getNazivFilma()))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }
}
