FROM eclipse-temurin:17-jdk

# Crear directorio de trabajo
WORKDIR /app

# Copiar el jar generado por Maven
COPY target/demo-kafka-autocommint-1.0.1.jar app.jar

# Ejecutar aplicación
ENTRYPOINT ["java","-jar","app.jar"]