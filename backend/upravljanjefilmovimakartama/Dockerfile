FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/UpravljanjeFilmovimaKartama-0.0.1-SNAPSHOT.jar upravljanje-filmovima-karata.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/upravljanje-filmovima-karata.jar" ]