package ba.tim2.RezervacijaKarata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RezervacijaKarataApplication implements CommandLineRunner {

    @Autowired
    private RezervacijaKarataService rezervacijaKarataService;


    public static void main(String[] args) {
        SpringApplication.run(RezervacijaKarataApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        rezervacijaKarataService.insertStaticData();
    }

}
