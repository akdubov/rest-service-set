FROM openjdk:8
COPY ./target/*.jar /tmp
WORKDIR /tmp
EXPOSE 8081
ENTRYPOINT ["java","-jar", "/tmp/rest-client-privileged-0.0.1-SNAPSHOT.jar"]