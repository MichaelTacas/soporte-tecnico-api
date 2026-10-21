# Sistema de Gestión de Soporte Técnico

## Descripción del proyecto

Este proyecto consiste en el desarrollo de una API RESTful utilizando Java y Spring Boot para gestionar solicitudes de soporte técnico de una empresa de servicios tecnológicos.

La aplicación permitirá registrar, consultar, actualizar y eliminar solicitudes de soporte técnico, así como gestionar la información relacionada con clientes y técnicos.

## Situación problemática

Actualmente, las solicitudes de soporte técnico se registran en papel o mediante correos electrónicos desorganizados, lo que puede ocasionar pérdida de información y retrasos en la atención.

Por ello, se propone desarrollar una API RESTful que permita organizar y gestionar las solicitudes de soporte técnico de manera eficiente.

## Objetivo

Desarrollar una API RESTful con Java y Spring Boot que permita administrar solicitudes de soporte técnico mediante operaciones CRUD.

Las principales operaciones serán:

- Registrar solicitudes.
- Consultar solicitudes.
- Actualizar solicitudes.
- Eliminar solicitudes.
- Gestionar clientes.
- Gestionar técnicos.
- Controlar el estado y prioridad de las solicitudes.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring Web
- Jakarta Validation
- Git
- GitHub
- Postman

## Arquitectura del proyecto

El proyecto utiliza una arquitectura organizada por capas y responsabilidades.

### model

Contiene las clases que representan los principales elementos del sistema.

- Cliente
- Técnico
- Solicitud
- Prioridad
- EstadoSolicitud

### dto

Contiene los objetos utilizados para recibir y devolver información mediante la API.

- SolicitudRequest
- SolicitudResponse

### service

Contendrá las interfaces que definirán las operaciones y reglas de negocio del sistema.

### service.impl

Contendrá las implementaciones de los servicios y la lógica de negocio.

### controller

Contendrá los controladores encargados de recibir las peticiones HTTP y exponer los endpoints REST.

### exception

Contendrá las excepciones personalizadas y el manejo centralizado de errores.

## Estructura del proyecto

soporte-tecnico-api
│
├── src
│   └── main
│       ├── java
│       │   └── com.idat.soportetecnico
│       │       ├── controller
│       │       ├── dto
│       │       ├── exception
│       │       ├── model
│       │       └── service
│       │           └── impl
│       │
│       └── resources
│           └── application.properties
│
├── pom.xml
├── .gitignore
└── README.md

## Modelos principales

### Cliente

Representa al cliente que solicita soporte técnico.

### Técnico

Representa al técnico encargado de atender una solicitud.

### Solicitud

Representa una solicitud de soporte técnico e incluye información como:

- Identificador.
- Título.
- Descripción.
- Prioridad.
- Estado.
- Cliente.
- Técnico asignado.

## Prioridades

Las solicitudes pueden tener las siguientes prioridades:

- BAJA
- MEDIA
- ALTA

## Estados de una solicitud

Las solicitudes pueden tener los siguientes estados:

- PENDIENTE
- EN_PROCESO
- RESUELTA
- CERRADA

## Validaciones

La aplicación utiliza Jakarta Validation para controlar los datos recibidos.

Algunas validaciones utilizadas son:

- `@NotBlank`
- `@NotNull`
- `@Size`
- `@Positive`

Estas validaciones permiten evitar datos vacíos, nulos o incorrectos.

## Persistencia de datos

La aplicación no utiliza una base de datos.

Los datos serán almacenados temporalmente en memoria utilizando estructuras como:

- `List`
- `Map`

La información permanecerá disponible mientras la aplicación esté en ejecución.

## Ejecución del proyecto

1. Clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Configurar Java 21.
4. Esperar que Maven descargue las dependencias.
5. Ejecutar la clase:

`SoporteTecnicoApiApplication`

6. La aplicación estará disponible en:

`http://localhost:8080`

## Endpoints

Esta sección será completada cuando se implementen los controladores.

Se implementarán operaciones como:

- GET
- POST
- PUT
- DELETE

## Pruebas

Las pruebas funcionales de la API serán realizadas utilizando Postman.

Se probarán:

- Registro de solicitudes.
- Consulta de solicitudes.
- Actualización.
- Eliminación.
- Validaciones.
- Manejo de errores.

## Documentación de la API

Los endpoints serán documentados posteriormente mediante Swagger.

## Manejo de errores

La aplicación implementará un manejo centralizado de excepciones mediante:

`@ControllerAdvice`

Esto permitirá devolver mensajes claros y códigos HTTP adecuados cuando ocurra un error.

## Integrantes y responsabilidades

### Integrante 1
Modelos, DTO y validaciones.

### Integrante 2
Servicios, interfaces, lógica de negocio y almacenamiento temporal.

### Integrante 3
Controladores, endpoints REST, manejo de excepciones y pruebas.

## Control de versiones

El proyecto utiliza Git y GitHub.

Ramas utilizadas:

- `main`
- `feature/modelos`
- `feature/servicios`
- `feature/controladores`

Cada integrante desarrolla su parte en una rama independiente y posteriormente los cambios son integrados a `main` mediante Pull Requests.

## Estado actual del proyecto

Actualmente se encuentran implementados:

- Configuración inicial de Spring Boot.
- Java 21.
- Modelos del sistema.
- DTO.
- Validaciones de entrada.

Pendiente:

- Servicios.
- Lógica de negocio.
- Controladores.
- Manejo de excepciones.
- Swagger.
- Pruebas completas con Postman.