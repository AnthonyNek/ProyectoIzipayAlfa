# PGS-BACK-MS-TEMPLATE Project

_Este proyecto es utilizado para la creación de los objetos del dominio y definición de interfaces de repositorio 
relacionados con el esquema de base de datos DB-PGS_

_Descripción del proyecto_

- Aplicación basada en **Java 11 Open JDK**
- Dependencias administradas con **Gradle 7.1.1**
- IDE para el desarrollo del proyecto **Spring Tool Suite 4.13.0. RELEASE y SpringBoot 2.6.3**
- Para generar código **Lombok 1.18.22**

### Compilación de proyecto, Crea artefacto en => ../artifacts/pgs-back-ms-template.jar

```
./gradlew buildArtifact
```

#### Comandos para publicar el código a una instancia sonarqube 

```
./gradlew --refresh-dependencies deploySonarQube  // Opcion A

./gradlew --refresh-dependencies buildSonarQube  // Opcion B
sonar-scanner //El sonar scanner hace uso del archivo sonar-project.properties y genera un log de detalle mas extenso
```

#### Los siguientes 2 parametros sirven para cualquiera de las 2 opciones A y B
```
-Dsonar.host.url=http://localhost:9000 
-Dsonar.login=token
```