# Deploying Spring Boot Application to Docker and Render

This guide explains how to containerize a Spring Boot application using Docker and then deploy it to Render.

## Prerequisites

- Java Development Kit (JDK) installed
- Maven installed
- Docker installed and running
- Docker Hub account (for pushing the Docker image)

## Steps

### 1. Create Dockerfile

Create a `Dockerfile` in the root directory of your Spring Boot project where `pom.xml` is located.

```dockerfile
# Dockerfile

# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/my-spring-boot-app-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
