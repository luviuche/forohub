# Foro Hub - Challenge AluraLATAM 🚀

¡Bienvenido al repositorio de Foro Hub! Este proyecto es una API RESTful construida con Spring Boot, desarrollada como parte del Challenge Back End de AluraLATAM. 

El objetivo principal de esta API es replicar el funcionamiento a nivel de backend de un foro de discusiones, permitiendo a los usuarios crear, visualizar, actualizar y eliminar tópicos (CRUD), todo bajo una capa de seguridad y autenticación.

---

## 🛠️ Tecnologías Utilizadas

El proyecto fue desarrollado utilizando el siguiente stack tecnológico:

* **Java 17** (o superior)
* **Spring Boot 3+**
* **Spring Web** (Construcción de la API REST)
* **Spring Data JPA** (Persistencia y abstracción de la base de datos)
* **Spring Security** (Autenticación y Autorización)
* **Auth0 java-jwt** (Generación y validación de tokens JWT)
* **MySQL 8+** (Motor de base de datos relacional)
* **Flyway** (Control de versiones y migraciones de la base de datos)
* **Hibernate Validator** (Validación de reglas de negocio)
* **Lombok** (Reducción de código repetitivo/boilerplate)

---

## ⚙️ Funcionalidades Implementadas

La API cumple con las siguientes reglas de negocio y funcionalidades:

1. **Gestión de Tópicos (CRUD):**
   * Crear un nuevo tópico (evitando duplicados en título y mensaje).
   * Mostrar todos los tópicos creados (con soporte para paginación).
   * Mostrar los detalles de un tópico específico.
   * Actualizar el título y mensaje de un tópico.
   * Eliminar un tópico.
2. **Validaciones:** Uso de DTOs (Data Transfer Objects) para asegurar que las peticiones entrantes contengan todos los campos obligatorios.
3. **Persistencia Segura:** Uso de migraciones SQL con Flyway para crear la estructura de tablas de forma automatizada y segura.
4. **Seguridad y Autenticación:** * Cifrado de contraseñas utilizando el algoritmo BCrypt.
   * Restricción de acceso a los endpoints del foro.
   * Emisión de Tokens JWT para mantener sesiones *stateless* seguras.

---

## 🚀 Cómo ejecutar el proyecto localmente

### Prerrequisitos

* Tener instalado el JDK de Java (versión 17 o superior).
* Tener instalado Maven.
* Tener instalado un servidor de MySQL local.
* Una herramienta para probar peticiones HTTP como **Insomnia** o **Postman**.

### Pasos de instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/luviuche/forohub.git
   cd forohub
   ```

2. **Preparar la base de datos:**
   Abre tu cliente de MySQL y ejecuta el siguiente comando para crear la base de datos vacía:
   ```sql
   CREATE DATABASE forohub;
   ```

3. **Configurar las variables de entorno:**
   Ve al archivo `src/main/resources/application.properties` y actualiza las credenciales de tu base de datos y la clave secreta del JWT:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub
   spring.datasource.username=TU_USUARIO_AQUI
   spring.datasource.password=TU_CONTRASEÑA_AQUI
   api.security.secret=TU_SECRETO_AQUI
   ```

4. **Ejecutar la aplicación:**
   Al iniciar la aplicación desde tu IDE (como IntelliJ IDEA) o mediante Maven, Flyway ejecutará automáticamente los scripts de migración para crear las tablas necesarias en MySQL.

5. **Poblar datos de prueba (Opcional pero recomendado):**
   Para poder hacer pruebas, inserta al menos un curso y un usuario directamente en tu base de datos:
   ```sql
   INSERT INTO cursos (nombre, categoria) VALUES ('Spring Boot 3', 'Programación');
   -- La contraseña "123456" encriptada con BCrypt:
   INSERT INTO usuarios (nombre, correo_electronico, contrasena) VALUES ('Usuario Test', 'test@test.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');
   ```

---

## 📡 Endpoints de la API

| Método | Ruta | Descripción | Requiere Autenticación |
|--------|------|-------------|------------------------|
| `POST` | `/login` | Autentica al usuario y devuelve un token JWT | ❌ No |
| `GET` | `/topicos` | Devuelve el listado de tópicos (paginado) | ✅ Sí |
| `GET` | `/topicos/{id}` | Devuelve los detalles de un tópico específico | ✅ Sí |
| `POST` | `/topicos` | Registra un nuevo tópico en la base de datos | ✅ Sí |
| `PUT` | `/topicos/{id}` | Actualiza un tópico existente | ✅ Sí |
| `DELETE` | `/topicos/{id}` | Elimina un tópico de la base de datos | ✅ Sí |

---

*Desarrollado por Luis Viuche con dedicación para el programa AluraLATAM.*
