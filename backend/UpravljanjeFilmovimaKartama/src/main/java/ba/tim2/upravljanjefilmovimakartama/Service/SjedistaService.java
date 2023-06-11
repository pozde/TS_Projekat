package ba.tim2.upravljanjefilmovimakartama.Service;

import ba.tim2.upravljanjefilmovimakartama.Entity.Sjediste;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SjedistaService {
    List<Sjediste> getSvaSjedista();
    ResponseEntity spasiSjediste(Sjediste sjediste);

    ResponseEntity obrisiSjediste(int id);

    ResponseEntity obrisiSvaSjedista();

    int getSalaIdPrekoSjedista(int id);

    int getBrojSalePrekoSjedista(int brojSale);

    ResponseEntity getSjedistePrekoBrojaSjedista(int broj);

}
