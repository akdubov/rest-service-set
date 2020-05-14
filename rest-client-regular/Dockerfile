FROM openjdk:8
COPY ./target/*.jar /tmp
WORKDIR /tmp
EXPOSE 8082
ENTRYPOINT ["java","-jar", "/tmp/rest-client-regular-0.0.1-SNAPSHOT.jar"]