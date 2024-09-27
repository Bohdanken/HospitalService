# Step 1: Build stage using Gradle
FROM gradle:jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy Gradle wrapper and project files first to cache dependencies
COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Ensure that the Gradle wrapper is executable
RUN chmod +x ./gradlew

# Download Gradle dependencies (cache them)
RUN ./gradlew build -x test --no-daemon || return 0

# Copy the source code after dependency installation
COPY src ./src

# Build the application, skipping tests and Checkstyle
RUN ./gradlew build -x test -x checkstyleMain  -x checkstyleTest --no-daemon


# Step 2: Runtime stage
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /dist

# Copy the built JAR file from the build stage to the runtime stage
COPY --from=build /app/build/libs/HospitalApp-0.0.1-SNAPSHOT.jar ./

# Expose the application port
EXPOSE 8092

# Run the application
CMD ["java", "-jar", "HospitalApp-0.0.1-SNAPSHOT.jar"]
