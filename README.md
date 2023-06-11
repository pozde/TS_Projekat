# Aplikacija za kino

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
* pregled korisničkog računa i uvid u kupljene karte
* pregled aktuelnih filmova uz mogućnost pretrage filmova po nazivu i/ili žanrovima
* rezervacija karata za projekciju filma (odabir filmova, željenih sjedišta i sl.)
* preporučivanje sadržaja na osnovu pregledanih filmova
* ostavljanje recenzija za aplikaciju i film


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

**Ući u svaki Spring projekat i odraditi Maven clean install:**

```bash
mvn clean install
```

**Unutar terminala kreirati docker image za MySQL:**

```bash
docker pull mysql
```

```bash
docker run --name system-events -e MYSQL_ROOT_PASSWORD=12345678 -e MYSQL_DATABASE=system_events -e MYSQL_USER=root -e MYSQL_PASSWORD=12345678 -d mysql:latest
```

**Unutar terminala pozicionirajući se u odgovarajući projekat kreirati image za sve mikroservise:**

- Eureka server | *eureka-server*

```bash
docker build -f Dockerfile -t eureka .
```

- System events | *system-events*

```bash
docker build -f Dockerfile -t system-events .
```

- Zuul gateway | *routing-and-filtering-gateway*

```bash
docker build -f Dockerfile -t zuul .
```

- Mikroservis za upravljanje korisnicima | *User*

```bash
docker build -f Dockerfile -t user .
```

- Mikroservis za upravljanje datotekama | *Project*

```bash
docker build -f Dockerfile -t project .
```

- Mikroservis za online testiranje | *online_testing*

```bash
docker build -f Dockerfile -t online-testing .
```

**Kreirati image za frontend *mockup-tool-react*:**

Instalirati *yarn*:

[Yarn Installation](https://classic.yarnpkg.com/en/docs/install/#windows-stable)

```bash
docker build -t mockup-tool-react .
```

**Pokrenuti docker-compose.yml datoteku:**

Pozicionirati se u root folder *Mockup-Tool-Drive* i otkucati komandu:

```bash
docker-compose up -d
```

**Unutar terminala kreirati *phpMyAdmin* docker image za pristup bazi podataka:**

```bash
docker run --network=mockup-tool-drive_default --name myadmin -d --link mockup-tool-drive_mysql-system-events_1:db -p 8070:80 phpmyadmin/phpmyadmin
```
