# Sistema de Gestión de Soporte Técnico

API REST desarrollada con Java y Spring Boot para gestionar solicitudes de soporte técnico, clientes y técnicos.

## Descripción

El proyecto permite registrar, consultar, actualizar y eliminar solicitudes de soporte técnico mediante una API REST.

La información se almacena temporalmente en memoria utilizando estructuras `Map`, por lo que los datos se eliminan cuando se reinicia la aplicación.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.1.1
- Spring Web
- Jakarta Validation
- Maven
- Swagger / OpenAPI
- Postman
- Git
- GitHub

## Arquitectura del proyecto

El proyecto está organizado por capas:

```text
src/main/java/com/idat/soportetecnico
├── controller
│   ├── ClienteController
│   ├── SolicitudController
│   └── TecnicoController
├── dto
│   ├── SolicitudRequest
│   └── SolicitudResponse
├── exception
│   ├── GlobalExceptionHandler
│   ├── RecursoNoEncontradoException
│   └── ReglaNegocioException
├── model
│   ├── Cliente
│   ├── Solicitud
│   ├── Tecnico
│   ├── EstadoSolicitud
│   └── Prioridad
├── service
│   ├── ClienteService
│   ├── SolicitudService
│   ├── TecnicoService
│   └── impl
│       ├── ClienteServiceImpl
│       ├── SolicitudServiceImpl
│       └── TecnicoServiceImpl
└── SoporteTecnicoApiApplication
```

## Funcionalidades

### Clientes

- Registrar cliente.
- Listar clientes.
- Buscar cliente por ID.
- Actualizar cliente.
- Eliminar cliente.
- Validar nombre, correo y empresa.

### Técnicos

- Registrar técnico.
- Listar técnicos.
- Buscar técnico por ID.
- Actualizar técnico.
- Eliminar técnico.
- Consultar técnicos disponibles.
- Validar nombre y especialidad.

### Solicitudes

- Registrar solicitud.
- Listar solicitudes.
- Buscar solicitud por ID.
- Actualizar solicitud.
- Eliminar solicitud.
- Buscar solicitudes por estado.
- Buscar solicitudes por prioridad.
- Asignar cliente y técnico.
- Modificar el estado de una solicitud.

## Estados de una solicitud

Las solicitudes pueden tener los siguientes estados:

```text
PENDIENTE
EN_PROCESO
RESUELTA
CERRADA
```

Toda solicitud nueva se registra automáticamente con estado `PENDIENTE`.

## Prioridades

Las prioridades disponibles son:

```text
BAJA
MEDIA
ALTA
```

## Validaciones

La API utiliza Jakarta Validation mediante anotaciones como:

- `@Valid`
- `@NotBlank`
- `@NotNull`
- `@Size`
- `@Email`
- `@Positive`

Cuando los datos enviados no cumplen las reglas establecidas, la API devuelve `400 Bad Request`.

## Manejo de errores

El proyecto utiliza `GlobalExceptionHandler` mediante `@RestControllerAdvice`.

Ejemplos:

```text
400 Bad Request → datos inválidos o regla de negocio
404 Not Found   → recurso no encontrado
```

Por ejemplo, consultar un cliente inexistente devuelve:

```json
{
  "estado": 404,
  "mensaje": "Cliente no encontrado",
  "error": "Not Found"
}
```

## Endpoints principales

### Clientes

```text
POST   /api/clientes
GET    /api/clientes
GET    /api/clientes/{id}
PUT    /api/clientes/{id}
DELETE /api/clientes/{id}
```

### Técnicos

```text
POST   /api/tecnicos
GET    /api/tecnicos
GET    /api/tecnicos/{id}
PUT    /api/tecnicos/{id}
DELETE /api/tecnicos/{id}
GET    /api/tecnicos/disponibles
```

### Solicitudes

```text
POST   /api/solicitudes
GET    /api/solicitudes
GET    /api/solicitudes/{id}
PUT    /api/solicitudes/{id}
DELETE /api/solicitudes/{id}

GET /api/solicitudes/estado/{estado}
GET /api/solicitudes/prioridad/{prioridad}
```

## Swagger / OpenAPI

La API cuenta con documentación interactiva mediante Swagger.

Con la aplicación ejecutándose:

```text
http://localhost:8080/swagger-ui.html
```

También se puede consultar la especificación OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

Swagger permite visualizar y probar directamente los endpoints REST.

## Ejecución

1. Clonar el repositorio.
2. Tener instalado Java 21.
3. Abrir el proyecto como proyecto Maven.
4. Ejecutar:

```text
SoporteTecnicoApiApplication
```

La aplicación se inicia en:

```text
http://localhost:8080
```

## Persistencia

El proyecto no utiliza una base de datos.

La información se almacena temporalmente utilizando:

```text
Map<Long, Cliente>
Map<Long, Tecnico>
Map<Long, Solicitud>
```

Por este motivo, los registros se eliminan cuando se reinicia la aplicación.

## Pruebas

Los endpoints fueron probados utilizando:

- Postman.
- Swagger UI.

Se verificaron:

- Operaciones CRUD.
- Validaciones.
- Respuestas `200 OK`.
- Respuestas `201 Created`.
- Respuestas `204 No Content`.
- Errores `400 Bad Request`.
- Errores `404 Not Found`.
- Cambio de estado de solicitudes.

## Principios de diseño

El proyecto utiliza separación de responsabilidades mediante capas e interfaces.

Ejemplo:

```text
Controller
    ↓
Service
    ↓
ServiceImpl
    ↓
Map
```

Los controladores dependen de las interfaces de servicio y no directamente de sus implementaciones, reduciendo el acoplamiento entre componentes.

## Control de versiones

El proyecto utiliza Git y GitHub mediante ramas de trabajo:

```text
main
feature/modelos
feature/servicios
feature/controladores
```

Las funcionalidades desarrolladas en cada rama son integradas posteriormente a `main` mediante Pull Requests.