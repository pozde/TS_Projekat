/*
package ba.tim2.authservice.service;

import ba.tim2.authservice.grpc.GrpcClient;
import ba.tim2.authservice.pollingstation.PollingStation;
import ba.tim2.authservice.pollingstation.PollingStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollingStationService {

    private final PollingStationRepository pollingStationRepository;

    public String getPollingStations() {
        List<PollingStation> pollingStations = pollingStationRepository.findAll();
        String json = null;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (PollingStation pollingStation : pollingStations) {
                sb.append(pollingStation.toString()).append(",");
            }
            if (pollingStations.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
            return null;
        }
        System.out.println("Serialized JSON: " + json);
        return json;
    }


    public String addPollingStation(Integer userId, PollingStation pollingStation) {
        pollingStationRepository.save(pollingStation);
        GrpcClient.log(userId,"AuthService","createPS","Success");
        return "Successfully created polling station " + pollingStation.getId();
    }
}
*/
