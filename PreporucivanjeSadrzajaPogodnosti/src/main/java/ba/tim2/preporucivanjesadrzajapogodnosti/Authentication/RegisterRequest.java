package ba.tim2.preporucivanjesadrzajapogodnosti.Authentication;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String ime;
    private String prezime;
    private String email;
    private String password;
    private Date datumRodjenja;
    private String brojTelefona;
    private String spol;
    private Role role;
}
