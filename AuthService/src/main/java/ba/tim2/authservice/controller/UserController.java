package ba.tim2.authservice.controller;

import ba.tim2.authservice.service.UserService;
import ba.tim2.authservice.user.User;
import ba.tim2.authservice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/id")
    private ResponseEntity<Integer> getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }

    /*
    @PostMapping("/{userId}/pollingStation/{pollingStationId}")
    public ResponseEntity<String> setPollingStation(@PathVariable Integer userId, @PathVariable Integer pollingStationId) {
        return userService.setPollingStation(userId,pollingStationId);
    }

    @PostMapping("/{userId}/pollingStation?name={pollingStationName}")
    public ResponseEntity setPStoUser(@PathVariable Integer userId, @PathVariable String pollingStationName) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<PollingStation> optionalPollingStation = pollingStationRepository.findByName(pollingStationName);
        if (optionalUser.isEmpty() || optionalPollingStation.isEmpty())
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or Polling station not found");
        User user = optionalUser.get();
        PollingStation pollingStation = optionalPollingStation.get();

        user.setPollingStation(pollingStation);
        pollingStation.addUser(user);



        userRepository.save(user);
        pollingStationRepository.save(pollingStation);
        return ResponseEntity.ok("Added polling station " + pollingStation.getId() + " to user " + user.getId());


    }

    @PostMapping("/pollingStation")
    public ResponseEntity<String> setPS(@RequestParam String name) {
        return setPStoUser(getId().getBody(), name);
    }
    */
}
