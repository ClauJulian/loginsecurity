# Gestor de Usuarios con Spring Boot Security

## Descripción
Este proyecto es un **Gestor de Usuarios** desarrollado con **Spring Boot**, que permite administrar usuarios con diferentes roles:

- **ADMIN**: Puede gestionar todos los usuarios, modificar y cambiar roles.
- **USER**: Puede modificar únicamente su propio perfil.

El sistema implementa seguridad con **Spring Security**, utilizando **FilterChain** para la gestión de autenticación y autorización, y **Thymeleaf Security** para proteger vistas en el frontend.

---

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf & Thymeleaf Security**
- **MySQL**
- **Lombok**
- **Maven**

---

## Instalación y Configuración

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/ClauJulian/loginsecurity.git
   ```
2. **Configurar la base de datos:**
   - Crear una base de datos llamada `security` en MySQL.
   - Ajustar las credenciales en `application.properties`.
3. **Compilar y ejecutar el proyecto:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. **Acceder a la aplicación:**
   Registra usuarios y desde la Base de Datos asigna el Rol de ADMIN a quién esté encargado de la Gestión de usuarios.
   - General: `http://localhost:8080/`
   - Usuario: `http://localhost:8080/`
   - Administrador: `http://localhost:8080/admin`

---

## Conceptos Clave

### Spring Security
Spring Security es un framework de seguridad para Java EE que ofrece autenticación y autorización para aplicaciones basadas en Spring Boot. Permite definir roles y restringir accesos a ciertas rutas y vistas.

### FilterChain
FilterChain se encarga de interceptar todas las peticiones HTTP antes de que lleguen a los controladores. Se utiliza para aplicar reglas de autenticación y autorización.

### Thymeleaf Security
Thymeleaf Security permite integrar Spring Security en las vistas, permitiendo mostrar u ocultar elementos según los permisos del usuario.

Ejemplo en HTML:
```html
<th:block sec:authorize="hasRole('ADMIN')">
    <a href="/admin">Panel de Administración</a>
</th:block>
```

---

## Dependencias Necesarias

Las principales dependencias utilizadas en este proyecto son:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.thymeleaf.extras</groupId>
        <artifactId>thymeleaf-extras-springsecurity6</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

---

## Contacto
Para consultas o mejoras en el proyecto, no dudes en contactarme.

