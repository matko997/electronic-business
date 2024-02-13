FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/bid-master-0.0.1-SNAPSHOT.jar /app/app.jar

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
