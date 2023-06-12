package ba.tim2.preporucivanjesadrzajapogodnosti;

import ba.tim2.preporucivanjesadrzajapogodnosti.Controllers.FilmController;
import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Film;
import ba.tim2.preporucivanjesadrzajapogodnosti.Services.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
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
@AutoConfigureMockMvc(addFilters = false)
public class PreporucivanjeSadrzajaPogodnostiTests {

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

    @Test
    @WithMockUser
    public void testDodajFilm() throws Exception {
        Film film = new Film();
        film.setNazivFilma("Oppenheimer");
        filmService.spasiFilm(film);

        ResponseEntity<Film> responseEntity = new ResponseEntity<>(film, HttpStatus.CREATED);
        given(this.filmService.spasiFilm(ArgumentMatchers.any(Film.class))).willReturn(responseEntity);// (new ResponseEntity(film, HttpStatus.CREATED));
        System.out.println("tess " + responseEntity.getBody().getNazivFilma());
        System.out.println("Response JSON: " + MockMvcResultMatchers.jsonPath("$.nazivFilma"));
        mvc.perform(MockMvcRequestBuilders
                        .post("/dodajFilm")
                        .content(asJsonString(film))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nazivFilma").value(film.getNazivFilma()));
    }

    @Test
    @WithMockUser
    public void testGetSizeFilm() throws Exception {
        Film film1 = new Film();
        film1.setNazivFilma("Oppenheimer");
        Film film2 = new Film();
        film2.setNazivFilma("The Dark Knight Rises");
        List<Film> filmovi = Arrays.asList(film1, film2);
        given(filmService.getSviFilmovi()).willReturn(filmovi);
        mvc.perform(MockMvcRequestBuilders.get("/filmovi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nazivFilma").value(film1.getNazivFilma()))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }
}
