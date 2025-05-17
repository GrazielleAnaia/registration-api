FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/registration-api-0.0.1-SNAPSHOT.jar /app/registration-api.jar

EXPOSE 8180

CMD ["java", "-jar", "/app/registration-api.jar"]


