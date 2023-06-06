FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/SpringCloudGateway-0.0.1-SNAPSHOT.jar api-gateway.jar
#COPY wait-for-it.sh wait-for-it.sh
#RUN chmod +x /wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]