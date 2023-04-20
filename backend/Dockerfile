# Docker Image for BIMDB

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY bimdb/target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]