# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio dentro del contenedor
WORKDIR /app

# Copiar el JAR generado por Maven
COPY target/Pedidos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del backend
EXPOSE 8080

# Comando para ejecutar el backend
ENTRYPOINT ["java", "-jar", "app.jar"]
