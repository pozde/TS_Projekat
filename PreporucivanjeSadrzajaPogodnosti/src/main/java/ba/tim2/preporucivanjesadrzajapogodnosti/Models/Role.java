package ba.tim2.preporucivanjesadrzajapogodnosti.Models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public enum Role {
    ADMIN("ADMIN"), KORISNIK("KORISNIK");

    @Getter
    private final String nazivRole;

    public List<SimpleGrantedAuthority> getAuthorities(){
        return Collections.singletonList(new SimpleGrantedAuthority(this.nazivRole))
;    }
}
