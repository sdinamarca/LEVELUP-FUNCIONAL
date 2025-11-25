# Etapa 1: construir el proyecto
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q clean package -DskipTests


# Etapa 2: ejecutar el JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar el JAR compilado
COPY --from=build /app/target/Pedidos-0.0.1-SNAPSHOT.jar app.jar

# Copiar el wallet a la imagen FINAL (etapa que Render ejecuta)
COPY src/main/resources/wallet /app/wallet

# Configurar TNS_ADMIN para Oracle
ENV TNS_ADMIN=/app/wallet

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
