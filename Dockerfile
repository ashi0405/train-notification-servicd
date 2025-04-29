# Use the official OpenJDK 17 image as base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project to the working directory
COPY . /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the app using Maven
RUN ./mvnw clean package

# Copy the JAR file from the target directory
COPY target/NotificationService-0.0.1-SNAPSHOT.jar /app/NotificationService.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "/app/NotificationService.jar"]
