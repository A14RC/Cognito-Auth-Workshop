# Spring Boot Security con AWS Cognito

Este proyecto es una API REST desarrollada en Kotlin y Spring Boot que implementa seguridad mediante OAuth2 y OpenID Connect utilizando AWS Cognito como proveedor de identidad.

## Requisitos Previos
* JDK 17 o superior.
* Cuenta de AWS con User Pool y Identity Pool configurados.
* Grupos definidos en Cognito: `admin`.

## Configuración del Entorno
Para que la aplicación valide los tokens de AWS, se debe configurar el archivo `src/main/resources/application.yaml` con los datos del User Pool:

- **Issuer URI**: `https://cognito-idp.us-east-2.amazonaws.com/us-east-2_RkzS4Hm9Q`
- **JWK Set URI**: `https://cognito-idp.us-east-2.amazonaws.com/us-east-2_RkzS4Hm9Q/.well-known/jwks.json`

## Endpoints Disponibles
1. `GET /api/public`: Acceso libre para todos los usuarios.
2. `GET /api/restricted`: Acceso solo para usuarios autenticados con un token válido.
3. `GET /api/restricted/admin`: Acceso restringido únicamente a usuarios pertenecientes al grupo `admin` en Cognito.

## Ejecución
Para ejecutar el proyecto localmente, utiliza el siguiente comando:
./gradlew bootRun