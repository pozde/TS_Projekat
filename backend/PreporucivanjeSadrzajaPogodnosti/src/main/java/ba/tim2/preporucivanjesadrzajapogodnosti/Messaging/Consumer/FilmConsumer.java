package ba.tim2.preporucivanjesadrzajapogodnosti.Messaging.Consumer;

import ba.tim2.preporucivanjesadrzajapogodnosti.Models.Film;
import ba.tim2.preporucivanjesadrzajapogodnosti.Repositories.FilmRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmConsumer {
    @Autowired
    private FilmRepository filmRepository;

    @RabbitListener(queues = "film-queue")
    public void consumeMessageFromQueue(FilmMessage filmMessage) {
        Film film = new Film();
        film.setID(filmMessage.getFilm_id());
        film.setNazivFilma(filmMessage.getNazivFilma());
        filmRepository.save(film);
    }
}
