# ----------------------
# Stage 1: Build the JAR
# ----------------------
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml first to leverage Docker cache
COPY pom.xml .
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the project (skip tests for faster builds)
RUN mvn clean package -DskipTests

# ----------------------
# Stage 2: Run the app
# ----------------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/aithronix-identity-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
