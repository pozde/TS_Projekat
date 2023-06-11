package ba.tim2.SpringCloudGateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/rezervacija-karata/films",
            "/rezervacija-karata/zanrovi/",
            "/rezervacija-karata/dodajSjediste/{sala_id}",
            "/rezervacija-karata/dodajSjediste/",
            "/rezervacija-karata/dodajSjediste",
            "/system-events/actions"

    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
