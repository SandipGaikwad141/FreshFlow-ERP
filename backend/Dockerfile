# Step 1: Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml first to enable Docker layer caching
COPY pom.xml .

# Pre-download all dependencies into the build layer
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B -T 1C

# Copy source code after downloading dependencies
COPY src ./src

# Build the JAR file skipping tests
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests -T 1C

# Step 2: Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]