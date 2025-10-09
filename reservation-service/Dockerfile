# ---- Stage 1: Build the app ----
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /build

# Install maven
RUN apt-get update && apt-get install -y maven

# Copy only pom files first
COPY pom.xml ./pom.xml
COPY common-library/pom.xml ./common-library/pom.xml
COPY cart-service/pom.xml ./cart-service/pom.xml
COPY inventory-service/pom.xml ./inventory-service/pom.xml
COPY reservation-service/pom.xml ./reservation-service/pom.xml
COPY search-service/pom.xml ./search-service/pom.xml
COPY product-service/pom.xml ./product-service/pom.xml

#Copy required sources
COPY common-library/src ./common-library/src
COPY reservation-service/src ./reservation-service/src

# Build service only
RUN mvn clean install -DskipTests -pl common-library
RUN mvn clean package -DskipTests -pl reservation-service -am

# ---- Stage 2: Run the app ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy only the built jar (Spring Boot 3 creates a fat jar by default)
COPY --from=builder /build/reservation-service/target/*.jar app.jar
EXPOSE 8080

# Default startup command
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
