package ba.tim2.systemevents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActionController {
    @Autowired
    private ActionService actionService;

    @GetMapping(value = "/actions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Action> actions() {
        return actionService.getAllActions();
    }
}
