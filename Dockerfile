# Use the Eclipse alpine official image
# https://hub.docker.com/_/eclipse-temurin
FROM eclipse-temurin:21-jdk-alpine

# Install necessary dependencies for Chrome and ChromeDriver
RUN apk update && apk add --no-cache \
    bash \
    curl \
    libx11 \
    libxcomposite \
    libxrandr \
    libxi \
    nss \
    alsa-lib \
    chromium \
    chromium-chromedriver

# Create and change to the app directory.
WORKDIR /app

# Copy files to the container image
COPY . ./

# Give execute permission to mvnw
RUN chmod +x ./mvnw

# Build the app.
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Set environment variables for running Chrome in headless mode
ENV CHROME_BIN=/usr/bin/chromium
ENV DISPLAY=:99

# Run the app by dynamically finding the JAR file in the target directory
CMD ["sh", "-c", "java -jar target/*.jar"]