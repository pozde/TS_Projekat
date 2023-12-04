package ba.tim2.RezervacijaKarata.Repository.Auth;

import ba.tim2.RezervacijaKarata.Entity.Auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByID(int id);
    Optional<User> findByEmail(String email);
}
