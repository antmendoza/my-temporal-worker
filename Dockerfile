FROM openjdk:17-jdk-slim-buster
MAINTAINER antmendoza.com
COPY target/my-temporal-worker-1.0-SNAPSHOT.jar my-temporal-worker-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=tc","/my-temporal-worker-1.0-SNAPSHOT.jar"]