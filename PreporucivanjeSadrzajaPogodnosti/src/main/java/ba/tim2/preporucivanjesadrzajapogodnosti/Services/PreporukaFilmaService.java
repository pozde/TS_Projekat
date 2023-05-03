package ba.tim2.preporucivanjesadrzajapogodnosti.Services;

import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.PreporukaFilmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreporukaFilmaService {
    @Autowired
    private PreporukaFilmaRepository repository;
}
