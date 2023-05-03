package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.PopustRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopustService {
    @Autowired
    private PopustRepository repository;
}
