# Step 1: Build the Spring Boot application using Maven with Java 21
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the backend folder contents specifically into /app
COPY backend/ .

RUN mvn clean package -DskipTests

# Step 2: Run the built executable JAR file with Java 21
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]