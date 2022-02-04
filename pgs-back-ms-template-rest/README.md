# PGS-BACK-MS-TEMPLATE-REST Project

_Este es un proyecto de micro-servicios plantilla_

_Descripción del proyecto_

- Se tiene un ejemplo de un controller y sus respectivos test, **que deben ser eliminados una vez se comprenda la estructura del proyecto**
- Aplicación basada en **Java 11 Open JDK**
- Dependencias administradas con **Gradle 7.1.1**
- Aplicación con arquitectura **hexagonal**
- IDE para el desarrollo del proyecto **Spring Tool Suite 4.11.0. RELEASE y SpringBoot 2.6.3**
- Para generar de código **Lombok 1.18.22**
- Contenedores para la integración con los componentes externos **Docker 20.10.2+ y Docker Compose 1.27.4+**

### 1. Swagger UI

Utilizando Swagger UI para exponer la documentación de nuestra API, podemos ahorrarnos mucho tiempo, ya que podremos organizar nuestros métodos e incluso poner los ejemplos que necesitemos.

Cabe recalcar que esta funcionalidad estará únicamente habilitada en desarrollo, validando esto con el perfil activo en los archivos de configuración

**http://localhost:9500/api/pgs/v1/template/swagger-ui**

### 2.- Ejecución por línea de comandos

**--stacktrace:** flag opcional para ver logs generados por gradle en la compilación

**--args='--spring.profiles.active=dev'** flag opcional para activar un perfil en especificó

```
./gradlew bootRun --stacktrace
```

### 3.- Empaquetado .jar de la aplicación

```
gradle wrapper build --stacktrace -x test
```