# API REST - Registro de Usuarios

Esta es una API REST desarrollada con Spring Boot que implementa un sistema de registro de usuarios, generación de migraciones automáticas con Flyway y validación de datos.

## Características

- Registro de usuarios con validación de correo electrónico y contraseña.
- Almacena información de usuario, incluyendo nombre, correo, contraseña, y teléfonos asociados.
- Generación de token de autenticación (JWT) para usuarios registrados.
- Persistencia de información con soporte para UUID como identificador único.
- Manejo de migraciones de base de datos con Flyway.

## Tecnologías Utilizadas

- **Spring Boot** (2.x o superior)
- **Flyway** para migraciones automáticas.
- **H2 Database** o cualquier base de datos compatible con JDBC.
- **JSON Web Token (JWT)** para autenticación.

---

## Requisitos Previos

- **Java 17** o superior.
- **Maven** 
- **Postman** o cualquier cliente HTTP para probar la API.

---

## Configuración Inicial

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/usuario/repo.git
   cd repo
2. **Correr migraciones:**
    ```bash
    mvn flyway:migrate
   
3. **Inicar proyecto**
    ```bash
   mvn spring-boot:run

## Resgistro
1. **Usar http://localhost:8080/api/users/create en postman con metodo POST**
   2. **Usar body->raw con este formato
       ```bash
      {
         "name": "Juan Rodriguez",
         "email": "juari1ssh_1s@mail.com",
         "password": "hunter2",
         "phones": [
            {
               "number": "1234567",
               "citycode": "1",
               "contrycode": "57"
            },
            {
               "number": "2234567",
               "citycode": "1",
               "contrycode": "57"
            }
         ]
      }
   