FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/bid-master-0.0.1-SNAPSHOT.jar /app/app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8080
CMD ["java", "-Duser.timezone=UTC", "-jar", "app.jar", "--pusher.app-id=${PUSHER_APP_ID}", "--pusher.key=${PUSHER_KEY}", "--pusher.secret=${PUSHER_SECRET}", "--pusher.cluster=${PUSHER_CLUSTER}", "--pusher.encrypted=${PUSHER_ENCRYPTED}"]
