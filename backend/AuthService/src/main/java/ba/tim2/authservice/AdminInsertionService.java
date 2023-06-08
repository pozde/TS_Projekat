package ba.tim2.authservice;

import ba.tim2.authservice.Models.User.Role;
import ba.tim2.authservice.Models.User.User;
import ba.tim2.authservice.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdminInsertionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void insertAdminData() {
        if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
            User admin = new User();
            admin.setIme("Admin");
            admin.setPrezime("Admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setDatumRodjenja(LocalDate.of(2000, 1, 1));
            admin.setSpol("M");
            admin.setBrojTelefona("0612345678");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
