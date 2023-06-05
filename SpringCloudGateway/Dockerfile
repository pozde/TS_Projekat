FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/SpringCloudGateway-0.0.1-SNAPSHOT.jar api-gateway.jar
EXPOSE 9080
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]