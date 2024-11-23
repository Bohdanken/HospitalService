# Step 1: Build stage using Gradle
FROM gradle:jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy Gradle files first to cache dependencies
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Download Gradle dependencies (cache them)
RUN ./gradlew build -x test --no-daemon || return 0

# Copy the source code after dependency installation
COPY src ./src

# Build the application
RUN ./gradlew build -x test --no-daemon

# Step 2: Runtime stage
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /dist

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar ./medicine-source-service.jar

# Expose the port for the entities service
EXPOSE 8082

# Command to run the entities microservice
CMD ["java", "-jar", "medicine-source-service.jar"]