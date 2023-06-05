FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/ConfigService-0.0.1-SNAPSHOT.jar config-service.jar
EXPOSE 9888
ENTRYPOINT ["java", "-jar", "/config-service.jar"]