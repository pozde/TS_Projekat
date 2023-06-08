package ba.tim2.RezervacijaKarata.Service;

import ba.tim2.RezervacijaKarata.Entity.Zanr;
import ba.tim2.RezervacijaKarata.Repository.ZanrRepository;
import ba.tim2.RezervacijaKarata.grpc.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZanrServiceImpl implements ZanrService{

    @Autowired
    private ZanrRepository zanrRepository;
    @Override
    public List<Zanr> getSviZanrovi() {
        GrpcClient.log("Zanr", "GET /zanrovi/", "SUCCESS");
        return zanrRepository.findAll();
    }
}
