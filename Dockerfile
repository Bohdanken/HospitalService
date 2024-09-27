FROM gradle:jdk17 AS build

WORKDIR /app

COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src
COPY config ./config

RUN chmod 777 gradlew

RUN gradle build -x test --no-daemon

FROM openjdk:17-jdk-alpine

WORKDIR /dist
COPY --from=build /app/build/libs/HospitalApp-0.0.1-SNAPSHOT.jar ./

EXPOSE 8092

CMD ["java", "-jar", "HospitalApp-0.0.1-SNAPSHOT.jar"]