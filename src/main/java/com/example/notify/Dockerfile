# Use the official OpenJDK 17 image as base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml to the working directory
COPY . /app

# Install Maven (if not already available in the image)
RUN apt-get update && apt-get install -y maven

# Build the app using Maven
RUN ./mvnw clean package

# Copy the target jar file to the final location
COPY target/NotificationService-0.0.1-SNAPSHOT.jar /app/NotificationService.jar

# Expose the port your application will run on
EXPOSE 8080

# Command to run the jar file
CMD ["java", "-jar", "/app/NotificationService.jar"]
