package ba.tim2.RezervacijaKarata;

import ba.tim2.RezervacijaKarata.Entity.Korisnik;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDate;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RestTemplateTestovi {

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Test
    public void testPostRestSpasiKorisnika() {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme("test");
        korisnik.setPrezime("test");
        korisnik.setSpol("M");
        korisnik.setDatumRodjenja(LocalDate.of(2000,2,24));
        korisnik.setEmail("test@exampleofatest.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Korisnik> request = new HttpEntity<>(korisnik, headers);
        Korisnik k1 = testRestTemplate.postForObject("http://localhost:8082/korisnici/dodaj", request, Korisnik.class);

        assertEquals(korisnik.getEmail(), k1.getEmail());
        testRestTemplate.delete("http://localhost:8082/korisnici/obrisi/" + k1.getID());
    }


    @Test
    public void testPostRestStatusOK() {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme("test");
        korisnik.setPrezime("test");
        korisnik.setSpol("M");
        korisnik.setDatumRodjenja(LocalDate.of(2000,2,24));
        korisnik.setEmail("test@exampleofatest.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Korisnik> request = new HttpEntity<>(korisnik, headers);
        ResponseEntity<Korisnik> responseEntity = testRestTemplate.postForEntity("http://localhost:8082/korisnici/dodaj", request, Korisnik.class);;

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Assert the response
        testRestTemplate.delete("http://localhost:8082/korisnici/obrisi/" + responseEntity.getBody().getID());
    }

    @Test
    public void testGETPostojiPrviKorisnikRest() {
        ResponseEntity<Korisnik> responseEntity = testRestTemplate.getForEntity(
                "http://localhost:8082/korisnici/{id}",
                Korisnik.class,
                1 );

        System.out.println(responseEntity);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void contextLoads() {

    }

}
