# Dentik - Sistema de Gestión Clínica y Citas

API REST para la gestión de una clínica odontológica: administración de usuarios y roles,
perfiles profesionales, control de sesiones, recuperación de contraseña y autenticación.
Este proyecto expone exclusivamente la API REST; la interfaz web se desarrolla en un
proyecto independiente que consume estos servicios.

## Tabla de contenido

- [Tecnologías](#tecnologías)
- [Arquitectura](#arquitectura)
- [Requisitos previos](#requisitos-previos)
- [Configuración](#configuración)
- [Ejecución](#ejecución)
- [Modelo de datos](#modelo-de-datos)
- [Roles del sistema](#roles-del-sistema)
- [Documentación de endpoints](#documentación-de-endpoints)
  - [Autenticación](#autenticación)
  - [Usuarios](#usuarios)
  - [Roles](#roles)
  - [Perfiles profesionales](#perfiles-profesionales)
  - [Sesiones](#sesiones)
- [Códigos de estado](#códigos-de-estado)
- [Consideraciones de seguridad](#consideraciones-de-seguridad)

## Tecnologías

| Componente | Versión |
|------------|---------|
| Java | 21 |
| Spring Boot | 4.1.0 |
| Spring Web MVC | incluido en Spring Boot |
| Spring Data JPA | incluido en Spring Boot |
| PostgreSQL | driver runtime |
| MapStruct | 1.6.3 |
| Lombok | gestionado por Spring Boot |
| Jakarta Bean Validation | incluido |

## Arquitectura

El proyecto sigue una arquitectura limpia (hexagonal) organizada en cuatro capas.
Cada entidad se implementa como un módulo vertical con la siguiente estructura:

```
dominio/
  entidades/            Modelos de negocio (POJO sin dependencias de framework)
  repositorios/         Puertos de persistencia (interfaces I<Entidad>Repositorio)
aplicacion/
  casosuso/entrada/     Interfaces de casos de uso (I<Entidad>UseCase)
  casosuso/impl/        Implementación de la lógica de negocio
infraestructura/
  persistencia/jpa/         Entidades JPA (<Entidad>Entity)
  persistencia/adaptadores/ Adaptadores que implementan los puertos de dominio
  persistencia/mapeadores/  Conversores JPA <-> dominio (MapStruct)
  repositorios/             Repositorios Spring Data (I<Entidad>JpaRepositorio)
  configuracion/            Registro manual de beans (DentikConfig)
presentacion/
  dto/request/          Objetos de entrada de la API
  dto/response/         Objetos de salida de la API
  mapeadores/           Conversores dominio <-> DTO (MapStruct)
  controladores/        Controladores REST
```

El flujo de una petición REST es:

```
Controlador REST -> DtoMapper -> Caso de uso -> Puerto de repositorio
                 -> Adaptador -> JpaMapper -> Repositorio JPA -> Base de datos
```

Los beans de repositorios y casos de uso se registran manualmente en
`infraestructura/configuracion/DentikConfig.java`. Los controladores y los mapeadores
de MapStruct se detectan por escaneo de componentes.

## Requisitos previos

- JDK 21 instalado.
- PostgreSQL en ejecución con una base de datos llamada `dentik-db`.
- Maven (se incluye el wrapper `mvnw`, no es necesario instalarlo aparte).

## Configuración

La configuración de la conexión se encuentra en `src/main/resources/application.properties`:

```properties
spring.application.name=dentik

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/dentik-db
spring.datasource.username=usuario
spring.datasource.password=contraseña
```

Ajuste `url`, `username` y `password` según su entorno. La opción `ddl-auto=update`
genera y actualiza el esquema automáticamente a partir de las entidades JPA.

Antes de usar la API conviene tener creados los roles base en la tabla `roles`:
`Paciente`, `Odontologo`, `Recepcionista`, `Administrador`.

## Ejecución

```bash
# Compilar
./mvnw clean compile

# Ejecutar la aplicación
./mvnw spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`.
Todos los endpoints REST se publican bajo el prefijo `/api`.

## Modelo de datos

Entidades principales y sus relaciones:

- **Usuarios**: datos personales y credenciales. Un paciente es un usuario con rol Paciente.
- **Roles**: catálogo de roles del sistema.
- **UsuarioRoles**: relación muchos a muchos entre usuarios y roles.
- **PerfilesProfesionales**: datos específicos de los odontólogos (relación 1 a 1 con usuario).
- **SesionesUsuario**: registro de inicios de sesión.
- **RecuperacionContrasena**: tokens temporales para restablecer la contraseña.

## Roles del sistema

| Rol | Descripción |
|-----|-------------|
| Paciente | Acceso a citas e historial propio. |
| Odontologo | Profesional de la salud. |
| Recepcionista | Gestión de citas y agenda. |
| Administrador | Administración del sistema. |

## Documentación de endpoints

Todas las peticiones con cuerpo utilizan `Content-Type: application/json`.
Las fechas se envían en formato ISO 8601: `yyyy-MM-dd` para fechas y
`yyyy-MM-ddTHH:mm:ss` para marcas de tiempo.

---

### Autenticación

Ruta base: `/api/auth`

#### Iniciar sesión

```
POST /api/auth/login
```

Valida las credenciales del usuario y registra una sesión. La cuenta debe estar en
estado `Activo`.

Cuerpo de la petición:

```json
{
  "correo": "juan.perez@correo.com",
  "contrasena": "MiClave123"
}
```

Respuesta `200 OK`:

```json
{
  "idUsuario": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "documento": "0102030405",
  "correo": "juan.perez@correo.com",
  "estado": "Activo",
  "fechaRegistro": "2026-07-22T10:15:00",
  "ultimaSesion": "2026-07-22T17:40:00",
  "fechaNacimiento": "1995-08-12",
  "telefono": "0987654321",
  "direccion": "Av. Amazonas 123",
  "ciudad": "Quito",
  "estadoProvincia": "Pichincha",
  "codigoPostal": "170501",
  "roles": ["Paciente"]
}
```

Si las credenciales son incorrectas o la cuenta no está activa, responde con error.

#### Solicitar recuperación de contraseña

```
POST /api/auth/recuperar
```

Genera un token temporal (vigencia de 24 horas) asociado al correo indicado.
En un entorno productivo este token se enviaría por correo electrónico; en esta
versión se devuelve en la respuesta para facilitar las pruebas.

Cuerpo de la petición:

```json
{
  "correo": "juan.perez@correo.com"
}
```

Respuesta `200 OK`:

```json
{
  "token": "550e8400-e29b-41d4-a716-446655440000",
  "mensaje": "Se genero un enlace temporal para restablecer la contrasena"
}
```

#### Restablecer contraseña

```
POST /api/auth/restablecer
```

Valida el token (existente, no utilizado y no expirado) y actualiza la contraseña.

Cuerpo de la petición:

```json
{
  "token": "550e8400-e29b-41d4-a716-446655440000",
  "nuevaContrasena": "NuevaClave456"
}
```

Respuesta `204 No Content` (sin cuerpo).

---

### Usuarios

Ruta base: `/api/usuarios`

Estructura del objeto de entrada (`UsuarioRequestDTO`):

| Campo | Tipo | Obligatorio | Notas |
|-------|------|-------------|-------|
| nombre | string | Sí | |
| apellido | string | Sí | |
| documento | string | Sí | Único en el sistema |
| correo | string | Sí | Formato de email válido y único |
| contrasena | string | Sí | |
| rol | string | No | Rol a asignar (ver tabla de roles) |
| estado | string | No | `Activo`, `Inactivo` o `Suspendido`. Por defecto `Activo` |
| fechaNacimiento | date | No | `yyyy-MM-dd` |
| telefono | string | No | |
| direccion | string | No | |
| ciudad | string | No | |
| estadoProvincia | string | No | |
| codigoPostal | string | No | |

#### Registro público de paciente

```
POST /api/usuarios/registro
```

Registra un nuevo usuario y le asigna automáticamente el rol `Paciente`, sin importar
el valor del campo `rol`. Valida que el documento y el correo no estén registrados.

Cuerpo de la petición:

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "documento": "0102030405",
  "correo": "juan.perez@correo.com",
  "contrasena": "MiClave123",
  "fechaNacimiento": "1995-08-12",
  "telefono": "0987654321",
  "direccion": "Av. Amazonas 123",
  "ciudad": "Quito",
  "estadoProvincia": "Pichincha",
  "codigoPostal": "170501"
}
```

Respuesta `201 Created`: objeto `UsuarioResponseDTO` (mismo formato que la respuesta de login).

#### Crear usuario (administrador)

```
POST /api/usuarios
```

Crea un usuario con el rol indicado en el campo `rol`. Se utiliza para dar de alta
odontólogos, recepcionistas o administradores. Valida documento y correo duplicados.

Cuerpo de la petición:

```json
{
  "nombre": "Ana",
  "apellido": "Torres",
  "documento": "0203040506",
  "correo": "ana.torres@correo.com",
  "contrasena": "Clave789",
  "rol": "Odontologo"
}
```

Respuesta `201 Created`: objeto `UsuarioResponseDTO`.

#### Listar usuarios

```
GET /api/usuarios
```

Respuesta `200 OK`: arreglo de `UsuarioResponseDTO`.

#### Obtener usuario por identificador

```
GET /api/usuarios/{id}
```

Respuesta `200 OK`: objeto `UsuarioResponseDTO`.

#### Listar usuarios por rol

```
GET /api/usuarios/rol/{nombre}
```

Devuelve los usuarios que tienen asignado el rol indicado. Ejemplo:
`GET /api/usuarios/rol/Odontologo`.

Respuesta `200 OK`: arreglo de `UsuarioResponseDTO`.

#### Actualizar usuario

```
PUT /api/usuarios/{id}
```

Actualiza los datos del usuario. Los roles no se modifican en esta operación; se
gestionan con el endpoint de asignación de roles.

Cuerpo de la petición: `UsuarioRequestDTO` (mismos campos que en la creación).

Respuesta `200 OK`: objeto `UsuarioResponseDTO`.

#### Asignar rol a un usuario

```
POST /api/usuarios/{id}/roles/{idRol}
```

Asigna un rol adicional a un usuario existente. No requiere cuerpo. Ejemplo:
`POST /api/usuarios/5/roles/2` asigna el rol con identificador 2 al usuario 5.

Respuesta `200 OK`: objeto `UsuarioResponseDTO` con la lista de roles actualizada.

#### Eliminar usuario

```
DELETE /api/usuarios/{id}
```

Respuesta `204 No Content`.

---

### Roles

Ruta base: `/api/roles`

Estructura del objeto de entrada (`RolRequestDTO`):

| Campo | Tipo | Obligatorio |
|-------|------|-------------|
| nombre | string | Sí |
| descripcion | string | No |

#### Crear rol

```
POST /api/roles
```

Cuerpo de la petición:

```json
{
  "nombre": "Odontologo",
  "descripcion": "Rol de profesional de la salud"
}
```

Respuesta `201 Created`:

```json
{
  "idRol": 2,
  "nombre": "Odontologo",
  "descripcion": "Rol de profesional de la salud"
}
```

#### Listar roles

```
GET /api/roles
```

Respuesta `200 OK`: arreglo de `RolResponseDTO`.

#### Obtener rol por identificador

```
GET /api/roles/{id}
```

Respuesta `200 OK`: objeto `RolResponseDTO`.

#### Eliminar rol

```
DELETE /api/roles/{id}
```

Respuesta `204 No Content`.

---

### Perfiles profesionales

Ruta base: `/api/perfiles-profesionales`

Estructura del objeto de entrada (`PerfilProfesionalRequestDTO`):

| Campo | Tipo | Obligatorio | Notas |
|-------|------|-------------|-------|
| idUsuario | number | Sí | Usuario odontólogo asociado |
| especialidad | string | No | |
| colegiatura | string | No | Único en el sistema |
| fechaIngreso | date | No | `yyyy-MM-dd`. Por defecto la fecha actual |

#### Crear perfil profesional

```
POST /api/perfiles-profesionales
```

Cuerpo de la petición:

```json
{
  "idUsuario": 5,
  "especialidad": "Ortodoncia",
  "colegiatura": "COL-12345",
  "fechaIngreso": "2024-01-15"
}
```

Respuesta `201 Created`:

```json
{
  "idPerfil": 1,
  "idUsuario": 5,
  "especialidad": "Ortodoncia",
  "colegiatura": "COL-12345",
  "fechaIngreso": "2024-01-15"
}
```

#### Listar perfiles profesionales

```
GET /api/perfiles-profesionales
```

Respuesta `200 OK`: arreglo de `PerfilProfesionalResponseDTO`.

#### Obtener perfil por identificador

```
GET /api/perfiles-profesionales/{id}
```

Respuesta `200 OK`: objeto `PerfilProfesionalResponseDTO`.

#### Obtener perfil por usuario

```
GET /api/perfiles-profesionales/usuario/{idUsuario}
```

Respuesta `200 OK`: objeto `PerfilProfesionalResponseDTO`.

#### Eliminar perfil profesional

```
DELETE /api/perfiles-profesionales/{id}
```

Respuesta `204 No Content`.

---

### Sesiones

Ruta base: `/api/sesiones`

Endpoints de solo lectura para consultar el registro de sesiones. Las sesiones se
crean automáticamente al iniciar sesión mediante `/api/auth/login`.

#### Listar todas las sesiones

```
GET /api/sesiones
```

Respuesta `200 OK`:

```json
[
  {
    "idSesion": 1,
    "idUsuario": 1,
    "fechaInicio": "2026-07-22T17:40:00",
    "fechaFin": null,
    "direccionIp": "127.0.0.1",
    "navegador": "Mozilla/5.0",
    "estado": "Activa"
  }
]
```

#### Listar sesiones por usuario

```
GET /api/sesiones/usuario/{idUsuario}
```

Respuesta `200 OK`: arreglo de `SesionUsuarioResponseDTO`.

---

## Códigos de estado

| Código | Significado |
|--------|-------------|
| 200 OK | Operación consultada o actualizada correctamente. |
| 201 Created | Recurso creado correctamente. |
| 204 No Content | Operación exitosa sin cuerpo de respuesta (eliminaciones y restablecimiento). |
| 400 Bad Request | Error de validación en los datos enviados. |
| 500 Internal Server Error | Error de negocio o de servidor (por ejemplo, dato duplicado o recurso no encontrado). |

## Consideraciones de seguridad

- Las contraseñas se almacenan en texto plano en esta versión. Para un entorno
  productivo debe incorporarse un algoritmo de hash (por ejemplo BCrypt) antes de
  persistir las credenciales.
- Los endpoints no cuentan todavía con control de acceso por rol ni emisión de tokens
  de sesión (JWT). La autenticación valida credenciales y registra la sesión, pero no
  protege el resto de rutas.
- El token de recuperación se devuelve en la respuesta únicamente con fines de prueba;
  en producción debe entregarse por un canal seguro como el correo electrónico.
