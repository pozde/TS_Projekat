FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/system-events-0.0.1-SNAPSHOT.jar system-events.jar
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "/system-events.jar"]