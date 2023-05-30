package ba.tim2.authservice.service;

import ba.tim2.authservice.user.User;
import ba.tim2.authservice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //private final PollingStationRepository pollingStationRepository;

    /*
    public ResponseEntity<String> setPollingStation(Integer userId, Integer pollingStationId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<PollingStation> optionalPollingStation = pollingStationRepository.findById(pollingStationId);

        if (optionalUser.isEmpty() || optionalPollingStation.isEmpty()) {
            GrpcClient.log(userId,"AuthService","Set PollingStation to user","Fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or Polling station not found");
        }

        User user = optionalUser.get();
        PollingStation pollingStation = optionalPollingStation.get();

        user.setPollingStation(pollingStation);
        pollingStation.addUser(user);

        userRepository.save(user);
        pollingStationRepository.save(pollingStation);
        GrpcClient.log(userId,"AuthService","Set PollingStation to user","Success");
        return ResponseEntity.ok("Added polling station " + pollingStation.getId() + " to user " + user.getId());
    }

     */

    public String getUsers() {
        List<User> users = userRepository.findAll();
        String json = null;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (User user : users) {
                sb.append(user.toString()).append(",");
            }
            if (users.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
        }
        System.out.println("Serialized JSON: " + json);
        return json;
    }
}
