FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/eurekaserver-0.0.1-SNAPSHOT.jar eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]