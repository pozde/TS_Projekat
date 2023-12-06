package ba.tim2.RezervacijaKarata.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(OPTIONS, "/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                // ClanarinaController
                .requestMatchers(GET, "/clanarine/").permitAll()
                .requestMatchers(GET, "/clanarine/{id}").permitAll()
                .requestMatchers(POST, "/clanarine/dodaj").permitAll()
                .requestMatchers(PUT, "/azuriraj/{id}").permitAll()
                .requestMatchers(DELETE, "/obrisi/{id}").permitAll()
                // FilmController
                .requestMatchers(POST, "/dodajFilm").permitAll()
                .requestMatchers(GET, "/filmovi").permitAll()
                .requestMatchers(GET, "/films").permitAll()
                .requestMatchers(GET, "/film/{id}").permitAll()
                .requestMatchers(PUT, "/azurirajFilm").permitAll()
                .requestMatchers(DELETE, "/deleteFilm/{id}").permitAll()
                // KartaController
                .requestMatchers(POST, "/dodajKartu/{korisnik_id}/{film_id}/{sala_id}/{brojSjedista}").permitAll()
                .requestMatchers(GET, "/karte").permitAll()
                .requestMatchers(GET, "/karte/sjediste/{broj_sale}/{broj_sjedista}").permitAll()
                .requestMatchers(GET, "/karta/{id}").permitAll()
                .requestMatchers(GET, "/karte/{id}").permitAll()
                .requestMatchers(DELETE, "/obrisiKartu/{id}").permitAll()
                // KorisnikController
                .requestMatchers(POST, "/dodajKorisnika").permitAll()
                .requestMatchers(GET, "/korisnici").permitAll()
                .requestMatchers(GET, "/korisnik/{id}").permitAll()
                .requestMatchers(GET, "/korisnik/email/{email}").permitAll()
                .requestMatchers(PUT, "/azurirajKorisnika/{id}").permitAll()
                .requestMatchers(DELETE, "/obrisiKorisnika/{id}").permitAll()
                // PopustController
                .requestMatchers(GET, "/popusti/").permitAll()
                .requestMatchers(GET, "/popusti/{id}").permitAll()
                .requestMatchers(POST, "/popusti/dodaj").permitAll()
                .requestMatchers(PUT, "/popusti/azuriraj/{id}").permitAll()
                .requestMatchers(DELETE, "/popusti/obrisi/{id}").permitAll()
                // PreporukaFilmaController
                .requestMatchers(GET, "/preporukeFilmova/").permitAll()
                .requestMatchers(GET, "/preporukeFilmova/{id}").permitAll()
                .requestMatchers(POST, "/preporukeFilmova/dodaj").permitAll()
                .requestMatchers(PUT, "/preporukeFilmova/azuriraj/{id}").permitAll()
                .requestMatchers(DELETE, "/preporukeFilmova/obrisi/{id}").permitAll()
                // SalaController
                .requestMatchers(POST, "/dodajSalu").permitAll()
                .requestMatchers(GET, "/sala/{brojSale}").permitAll()
                .requestMatchers(GET, "/sale").permitAll()
                .requestMatchers(DELETE, "/deleteSale").permitAll()
                .requestMatchers(DELETE, "/deleteSalu/{id}").permitAll()
                .requestMatchers(PUT, "/sale/film/{id}").permitAll()
                .requestMatchers(POST, "/dodajSjediste/{sala_id}").permitAll()
                // SjedisteController
                .requestMatchers(POST, "/dodajSjediste").permitAll()
                .requestMatchers(GET, "/sjedista").permitAll()
                .requestMatchers(GET, "/sjedista/{id}").permitAll()
                .requestMatchers(GET, "/sjedista/sala/{brojSale}").permitAll()
                .requestMatchers(GET, "/brojSjedista/{brojSjedista}").permitAll()
                .requestMatchers(DELETE, "/deleteSjediste/{id}").permitAll()
                .requestMatchers(DELETE, "/deleteSjedista").permitAll()
                // ZanrController
                .requestMatchers(GET, "/zanrovi/").permitAll()
                .requestMatchers(PUT, "/zanrovi/film/{id}").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
