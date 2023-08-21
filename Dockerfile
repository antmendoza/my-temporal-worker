FROM openjdk:17.0.2-oraclelinux8
MAINTAINER antmendoza.com
COPY target/my-temporal-worker-1.0-SNAPSHOT.jar my-temporal-worker-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=tc","/my-temporal-worker-1.0-SNAPSHOT.jar"]