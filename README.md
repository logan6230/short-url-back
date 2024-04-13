# Short URL Backend

Este es un proyecto de demostración de Spring Boot para crear un acortador de URL.

## Descripción

El proyecto utiliza Spring Boot para implementar un servicio de acortamiento de URL. Utiliza MongoDB como base de datos para almacenar las URL originales y sus correspondientes versiones acortadas. Además, se integra con Docker Compose para facilitar el despliegue del servicio.

## Requisitos

- Java 17
- Maven
- Docker
- Docker Compose
- MongoDb

## Configuración

1. Clona el repositorio: `git clone https://github.com/segundo-pantoja/short-url.git`
2. Navega al directorio del proyecto: `cd short-url`
3. Construye el proyecto: `mvn clean package`
4. Ejecuta Docker Compose: `docker-compose up`

## Uso

Una vez que el servicio esté en funcionamiento, puedes acceder a él a través de la URL base proporcionada por Docker Compose. Puedes utilizar las siguientes rutas para interactuar con el servicio:

- `GET /{domain}`: Redirige a la URL original correspondiente al dominio proporcionado.
- `GET /all`: Obtiene todas las URL almacenadas en la base de datos.
- `POST /create`: Crea una nueva URL acortada. Envía un JSON con la URL original en el cuerpo de la solicitud.

## Contribución

¡Las contribuciones son bienvenidas! Si deseas contribuir a este proyecto, sigue estos pasos:

1. Realiza un fork del proyecto.
2. Crea una nueva rama: `git checkout -b feature/nueva-caracteristica`.
3. Realiza tus cambios y haz commit: `git commit -am 'Agrega una nueva característica'`.
4. Sube tus cambios: `git push origin feature/nueva-caracteristica`.
5. Envía un pull request.

## Licencia

Este proyecto está bajo la Licencia MIT.
