package ba.tim2.RezervacijaKarata.Messaging.Consumer;

import ba.tim2.RezervacijaKarata.Entity.Film;
import ba.tim2.RezervacijaKarata.Repository.FilmRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmConsumer {
    @Autowired
    private FilmRepository filmRepository;

    @RabbitListener(queues = "response-film-queue")
    public void consumeMessageFromQueue(FilmMessage filmMessage) {
        Film deleteFilm = filmRepository.findTopByNazivFilmaOrderByIDDesc(filmMessage.getNazivFilma());
        if(deleteFilm != null){
            filmRepository.deleteById(filmRepository.findByID(deleteFilm.getID()).getID());
        }
    }
}
