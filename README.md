##### Elektrotehnički fakultet u Sarajevu
##### Računarstvo i informatika
#### Praktikum – Napredne web tehnologije

------------

 Članovi tima:
 - Muhamed Borovac
 - Benjamin Pašić
 - Admir Pozderac

------------


CineFlex
==

## Opis

CineFlex je softverska aplikacija za kino omogućava rezervaciju karata, pregled aktuelnih filmova i razne korisničke pogodnosti na jednom mjestu.

Neke od najvažnijih funkcionalnosti u sklopu CineFlex-a su: 
* kreiranje korisničkog računa
* pregled korisničkog računa
* pregled aktuelnih filmova uz mogućnost pretrage filmova po nazivu i/ili žanrovima
* rezervacija karata za projekciju filma (odabir filmova, željenih sjedišta i sl.)
* preporučivanje sadržaja na osnovu pregledanih filmova
* privilegovani način rada (dodavanje filmova, brisanje filmova, pregled korisnika, pregled logova i sl.)


## Preduvjeti

Za build i pokretanje aplikacije potrebno vam je:
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ili noviji
* [Maven 3](https://maven.apache.org/)

## Konfiguracija baze
U svojoj zadanoj konfiguraciji sva tri mikroservisa koriste MySQL bazu podataka, koja se popunjava podacima prilikom pokretanja. Baze podataka imaju sljedeće nazive:
* upravljanje_filmovima_kartama(za mikroservis za upravljanje filmovima i kartama)
* rezervacijaKarata (za mikroservis za rezervaciju karata)
* preporucivanje_sadrzaja_pogodnosti (za mikroservis za preporučivanje sadržaja i pogodnosti)

## Pokretanje aplikacije lokalno

### Koraci

**U okviru komandnog prozora otkucati:**
```bash
git clone https://github.com/mborovac1/PNWT_Projekat.git
```
**Otvoriti IntelliJ IDEA:**

U glavnom meniju izabrati ```
                          File->Open```, te odabrati željeni projekat.

**Pokrenuti eureka-server:**

Jedan od načina jeste pokretanje main metode u:
* com.example.eurekaserver.EurekaServerApplication

Alternativno, moguće je koristiti [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins-maven-plugin):
```bash
mvn spring-boot:run
```

Eureka serveru je moguće pristupiti na lokaciji [http://localhost:8761/](http://localhost:8761/).

**Pokrenuti projekat:**

Jedan od načina jeste pokretanje main metode u:
* ba.tim2.upravljanjefilmovima.UpravljanjeFilmovimaApplication kod mikroservisa za upravljanje filmovima i kartama
* ba.tim2.RezervacijaKarata.RezervacijaKarataApplication kod mikroservisa za rezervaciju karata
* ba.tim2.preporucivanjesadrzajapogodnosti.PreporucivanjeSadrzajaPogodnostiApplication kod mikroservisa za preporučivanje sadržaja i pogodnosti

Alternativno, moguće je koristiti [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins-maven-plugin)
kako je opisano u prethodnom koraku.


## Pokretanje aplikacije kao Docker kontejner

### Koraci

**Instalirati Docker Desktop:**

[Docker Desktop](https://www.docker.com/products/docker-desktop)

Osnovni koraci koji su potrebni za kreiranje naših Docker kontejnera su sljedeći:

1. Moramo dodati JAVA_HOME sistemsku environment varijablu, za pokretanje Java komandi kroz terminal. U našem slučaju je JAVA_HOME sistemska varijabla pozicionirana na sljedeći način C:\Program Files\Java\jdk-17
2. Prije pokretanja kontejnera također je vrlo bitno kroz terminal navigirati do direktorija svakog od mikroservisa, te za svaki mikroservis je potrebno unijeti komandu ```.\mvnw.cmd clean package -D skipTests``` kako bi se uspješno kreirale .jar datoteke koje su neophodne za izgradnju Docker slika.
3. Nakon toga, potrebno se pozicionirati u direktorij unutar kojeg se nalaze svi mikroservisi (direktoriji svih mikroservisa).
4. Kao konačni korak potrebno je unijeti komandu ```docker-compose up --build``` komandu, kako bi se izgradile sve potrebne slike kroz docker-compose.yml fajl, te nakon toga pokrenuli kontejneri.
