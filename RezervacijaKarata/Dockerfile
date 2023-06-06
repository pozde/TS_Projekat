FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/RezervacijaKarata-0.0.1-SNAPSHOT.jar rezervacija-karata.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/rezervacija-karata.jar"]