package ba.tim2.authservice.Security.Authentication;

import ba.tim2.authservice.Models.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String brojTelefona;
    private String email;
    private String password;
    private Role role;
    private String spol;
}
