# Establece la imagen base
FROM openjdk:17-alpine3.14

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR de la aplicación al contenedor
COPY target/short-url-1.0.0.jar /app.jar

# Expone el puerto en el que la aplicación está escuchando
EXPOSE 8086

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "/app.jar"]
