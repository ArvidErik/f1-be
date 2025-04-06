FROM openjdk:21-jdk-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Run Maven to build the project
RUN mvn clean package -DskipTests

# Use a smaller base image to run the app
FROM openjdk:21-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage to the current directory
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (default Spring Boot port)
EXPOSE 8080

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
