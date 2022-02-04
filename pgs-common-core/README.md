# IZIPAY-COMMON-CORE Project

_Este proyecto es utilizado como un proyecto common que contiene estructuras de datos base y utilitarios de librerías reutilizables_

_Descripción del proyecto_

- Aplicación basada en **Java 11 Open JDK**
- Dependencias administradas con **Gradle 7.1.1**
- IDE para el desarrollo del proyecto **Spring Tool Suite 4.13.0. RELEASE y SpringBoot 2.6.3**
- Para generar código **Lombok 1.18.22**

### Compilación de proyecto, Crea artefacto en => ../artifacts/mef-common.jar

```
./gradlew buildArtifact
```

### Generar Certificados RSA de prueba

```
openssl genrsa -out private.pem 2048
openssl rsa -in private.pem -outform PEM -pubout -out public.pem
openssl pkcs8 -topk8 -inform PEM -outform DER -in private.pem -out private.der -nocrypt
```

Los certificados de prueba se encuentran en la ruta ./others/rsa


### Configuración docker para la base de datos MongoDB y Redis

```
docker-compose up -d
```

### SonarQube

#### Configuración docker-compose para desplegar una instancia sonarqube local

```
docker-compose -f docker-compose/sonarqube.yml up -d // (usuario:contraseña) por defecto => (admin:admin)
```
** http://localhost:9000 ** Url del servidor sonarqube

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