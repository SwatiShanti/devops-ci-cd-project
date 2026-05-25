# =========================================================================
# STAGE 1: Build Stage
# =========================================================================
# We use an official Maven image with JDK 21 to build the project. 
# This means Maven doesn't need to be installed on the machine running Docker.
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Copy the actual source code (Java files, resources)
COPY src ./src

# Build the application and package it into a JAR file.
# This compiles the code, runs the tests, and packages it into target/devops-ci-cd-1.0.0.jar
RUN mvn clean package

# =========================================================================
# STAGE 2: Run Stage
# =========================================================================
# We use a lightweight JRE (Java Runtime Environment) image to run the app.
# Using JRE instead of JDK, and Alpine Linux instead of full OS, keeps the final image size small.
FROM eclipse-temurin:21-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the Build Stage (Stage 1) to this stage
COPY --from=build /app/target/devops-ci-cd-1.0.0.jar app.jar

# Expose port 9090 (the port our Spring Boot application runs on)
EXPOSE 9090

# Command to execute the Spring Boot JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
