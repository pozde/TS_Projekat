package ba.tim2.authservice.Security.Authentication;

import ba.tim2.authservice.Models.User.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String ime;
    private String prezime;

    @JsonFormat(pattern = "dd.MM.yyyy.")
    private LocalDate datumRodjenja;
    private String brojTelefona;
    private String email;
    private String password;
    private Role role;
    private String spol;
}
