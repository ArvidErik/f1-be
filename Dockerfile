# Use a base image with Java 17 or Java 21 (depending on what you're using)
FROM openjdk:17-oracle AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Ensure the Maven wrapper is executable
RUN chmod +x ./mvnw

# Run Maven to build the project (use `mvn clean install` for a full build)
RUN ./mvnw clean package -DskipTests

# Use a smaller base image to run the app
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage to the current directory
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (default Spring Boot port)
EXPOSE 8080

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "DemoApplication.jar"]
