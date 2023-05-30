package ba.tim2.preporucivanjesadrzajapogodnosti.Token;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Korisnik;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;
}
