# Etapa 1: construir el proyecto
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q clean package -DskipTests

COPY src/main/resources/wallet /app/wallet
ENV TNS_ADMIN=/app/wallet


# Etapa 2: ejecutar el JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Pedidos-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
