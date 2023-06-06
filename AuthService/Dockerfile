FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/AuthService-0.0.1-SNAPSHOT.jar auth-service.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/auth-service.jar"]