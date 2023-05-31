package ba.tim2.authservice.Models.User;

import ba.tim2.authservice.Models.Token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String ime;
    private String prezime;

    private Date datumRodjenja;

    private String brojTelefona;

    private String email;
    private String password;

    private String spol;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "{" +
                //"\"id\":\"" + ID + "\"," +
                "\"ime\":\"" + ime + "\"," +
                "\"prezime\":\"" + prezime + "\"," +
                "\"datumRodjenja\":" + null + "," +
                "\"brojTelefona\":\"" + brojTelefona + "\"," +
                "\"spol\":\"" + spol + "\"," +
                "\"email\":\"" + email + "\"" +
                "}";
    }
}
