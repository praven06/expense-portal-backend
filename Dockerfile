# Use a lightweight JDK 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /expportal

# Copy the built JAR into the container
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "app.jar"]
