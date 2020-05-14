FROM openjdk:8
COPY ./target/*.jar /tmp
WORKDIR /tmp
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/tmp/rest-server-service-0.0.1-SNAPSHOT.jar"]