package ba.tim2.preporucivanjesadrzajapogodnosti.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = """
      select t from Token t inner join Korisnik k\s
      on t.korisnik.ID = k.ID\s
      where k.ID = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> nadjiSveTokeneZaKorisnika(int id);

    Optional<Token> findByToken(String token);
}
