FROM openjdk:21-oracle AS build

# Install curl, gnupg, and Maven dependencies
RUN apt-get update && apt-get install -y \
    curl \
    gnupg \
    ca-certificates \
    unzip \
    && curl -fsSL https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz -o /tmp/maven.tar.gz \
    && tar -xvf /tmp/maven.tar.gz -C /opt \
    && ln -s /opt/apache-maven-3.8.4/bin/mvn /usr/bin/mvn

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Run Maven to build the project
RUN mvn clean package -DskipTests

# Use a smaller base image to run the app
FROM openjdk:21-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage to the current directory
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (default Spring Boot port)
EXPOSE 8080

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
