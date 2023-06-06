FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/PreporucivanjeSadrzajaPogodnosti-0.0.1-SNAPSHOT.jar preporucivanje-sadrzaja-pogodnosti.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/preporucivanje-sadrzaja-pogodnosti.jar"]