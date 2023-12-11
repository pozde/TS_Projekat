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

import static ba.tim2.RezervacijaKarata.Entity.Auth.Role.ADMIN;
import static ba.tim2.RezervacijaKarata.Entity.Auth.Role.USER;
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
        http.cors()
                .and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(OPTIONS, "/**").permitAll()
                // Auth
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/register").permitAll()
                .requestMatchers("/auth/logout").hasAnyRole(ADMIN.name(), USER.name())
                // FilmController
                .requestMatchers(POST, "/dodajFilm").hasRole(ADMIN.name())
                .requestMatchers(GET, "/films").hasAnyRole(ADMIN.name())
                .requestMatchers(GET, "/filmovi").permitAll()
                .requestMatchers(GET, "/film/{id}").hasRole(USER.name())
                .requestMatchers(PUT, "/azurirajFilm").hasRole(ADMIN.name())
                .requestMatchers(DELETE, "/deleteFilm/{id}").hasRole(ADMIN.name())
                // KartaController
                .requestMatchers(POST, "/dodajKartu/{korisnik_id}/{film_id}/{sala_id}/{brojSjedista}").hasRole(USER.name())
                .requestMatchers(GET, "/karte").hasAnyRole(USER.name(), ADMIN.name())
                .requestMatchers(GET, "/karte/sjediste/{broj_sale}/{broj_sjedista}").hasAnyRole(ADMIN.name())
                .requestMatchers(GET, "/karta/{id}").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers(GET, "/karte/{id}").hasAnyRole(ADMIN.name())
                .requestMatchers(DELETE, "/obrisiKartu/{id}").hasRole(ADMIN.name())
                // KorisnikController
                .requestMatchers(POST, "/dodajKorisnika").hasRole(ADMIN.name())
                .requestMatchers(GET, "/korisnici").hasRole(ADMIN.name())
                .requestMatchers(GET, "/korisnik/{id}").hasRole(ADMIN.name())
                .requestMatchers(GET, "/korisnik/email/{email}").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers(GET, "/user/email/{email}").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers(PUT, "/azurirajKorisnika/{id}").hasRole(ADMIN.name())
                .requestMatchers(DELETE, "/obrisiKorisnika/{id}").hasRole(ADMIN.name())
                // SalaController
                .requestMatchers(POST, "/dodajSalu").hasRole(ADMIN.name())
                .requestMatchers(GET, "/sala/{brojSale}").hasRole(USER.name())
                .requestMatchers(GET, "/sale").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers(DELETE, "/deleteSale").hasRole(ADMIN.name())
                .requestMatchers(DELETE, "/deleteSalu/{id}").hasRole(ADMIN.name())
                .requestMatchers(PUT, "/sale/film/{id}").hasRole(ADMIN.name())
                .requestMatchers(POST, "/dodajSjediste/{sala_id}").hasRole(USER.name())
                // SjedisteController
                .requestMatchers(POST, "/dodajSjediste").hasRole(USER.name())
                .requestMatchers(GET, "/sjedista").hasRole(ADMIN.name())
                .requestMatchers(GET, "/sjedista/{id}").hasRole(ADMIN.name())
                .requestMatchers(GET, "/sjedista/sala/{brojSale}").hasRole(ADMIN.name())
                .requestMatchers(GET, "/brojSjedista/{brojSjedista}").hasRole(ADMIN.name())
                .requestMatchers(DELETE, "/deleteSjediste/{id}").hasRole(ADMIN.name())
                .requestMatchers(DELETE, "/deleteSjedista").hasRole(ADMIN.name())
                // ZanrController
                .requestMatchers(GET, "/zanrovi/").permitAll()
                .requestMatchers(PUT, "/zanrovi/film/{id}").hasRole(ADMIN.name())
                // Logout
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
