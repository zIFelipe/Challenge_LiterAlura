
![badge literalura](https://github.com/user-attachments/assets/82447766-1cd9-49dc-b888-c06d55b1750e)

<h1 align="center"> Challenge_LiterAlura 📚 </h1>

<h1 style="text-align: left;">Descripcion📝</h1>
Este proyecto es una aplicacion que permite la busqueda, gestion y almacenamiento de informacion de libros y autores, integrando estos datos mediante una API. Esto permite a los usuarios acceder a la informacion de los libros, autores e idiomas de origen. Ademas, la aplicacion permite almacenar estos datos dentro de una base de datos como lo es PostgreSQL17, brindando una herramienta eficiente para el manejo de información.🚀
<h1 style="text-align: left;">Estado del proyecto: Finalizado📎</h1>
<h1 style="text-align: left;">Tecnologías empleadas </h1>
- **Java 17**

- **Sptring Boot** para el REST API y JPA

- **Hibernate** gestion de base de datos

- **MySQL** , **H2 Database** configuracion base de datos
  
- **Jakarta Persistence API** JPA para mapeo objeto-relacional

- **Jackson** para serialización y deserialización de JSON
  
<h1 style="text-align: left;">✏️ Requisitos Previos 📖</h1>

1. Java Development Kit (JDK) 17 o superior.
2. Un entorno de desarrollo como preferiblemenete IntelliJ IDEA.
3. Base de datos configurada (MySQL, H2 o similar) hacer uso de Postgre17 (pgAdmin4).
4. Maven instalado para la gestión de dependencias.
5. Sring Data JPA y PostgreSQL Driver (se pueden adjuntar al proyecto en Spring initializr)
6. Jackson Databind y Jacks Core dependencias para el Json (se puede obtener los fragmentos de codigo en mvn Repository)

<h1 style="text-align: left;">Tutorial de Uso e instalación 🖥️</h1>
1. Clona este repositorio e instalalo en tu computadora:

    ```bash
    git clone [https://github.com/tu-usuario/literalura.git](https://github.com/zIFelipe/Challenge_LiterAlura.git)
    cd LiterAlura
    ```
    
2. Utiliza una IDE compatible con Java, preferiblemente IntelliJ IDEA.
   
3.  Configura tu base de datos en el archivo`application.properties`:

                  spring.application.name=LiterAlura_Callenge
                  spring.datasource.url=jdbc:postgresql://${DB_HOST}/LiterAlura_BD
                  spring.datasource.username=${DB_USER}
                  spring.datasource.password=${DB_PASSWORD}
                  spring.datasource.driver-class-name=org.postgresql.Driver
                  hibernate.dialect=org.hibernate.dialect.HSQLDialect
                  spring.jpa.hibernate.ddl-auto=update
                  spring.jpa.show-sql=true
                  spring.jpa.format-sql = true
4.  Compila el programa mediante "LiterAluraChallengeApplication", en caso de no saber que hacer, revisar las capturas de pantalla adjuntas en el readme.
   

    

<h1 style="text-align: left;">Funciones 💡</h1>

### Clases y estructura principal:

#### `clasePrincipal`:
- **ClasePrincipal**: La clase principal que ejecuta el inicio de la aplicación y coordina la ejecución del resto del sistema, aqui se encuentran las funciones principales del proyecto.

---

### Paquete: `model` 🏷️
Este paquete contiene las clases que definen los modelos del sistema, incluyendo la representación de datos como libros, autores y otros objetos necesarios para el procesamiento.

#### Clases en `model`:

- **Idioma (Enum)**: Enum que define los posibles idiomas de los libros (Español, inglés, francés y protugués.
  
- **Libro**: Clase y Entidad que representa un libro, con atributos como título, autores, idioma, descargas.

- **Autor**: Clase y Entidad que representa a un autor, con atributos como nombre, año de nacimiento, y los libros asociados.

- **DatosLibro (Record)**: Registro que mapea los datos obtenidos de la API externa, incluyendo el título del libro, autores, idioma y descargas.

- **DatosAutor (Record)**: Registro que mapea los datos de un autor obtenidos de la API externa, incluyendo fecha de nacimiento, de fallecimiento y nombre.

- **DatosListaLibro (Record)**: Registro que mapea una lista de libros desde la API, con sus respectivas propiedades como el total y los libros.

---

### Paquete: `repository` 📦
Este paquete contiene la interfaz necesaria para interactuar con la base de datos, permitiendo la persistencia de los objetos en el sistema.

#### Clases en `repository`:

- **LibroRepository**: Interfaz que extiende `JpaRepository`, proporcionando métodos para acceder y modificar los datos de los libros almacenados en la base de datos mediante el uso de @Query.

---

### Paquete: `service` 🔧
Este paquete contiene la lógica de negocio y la interacción con los servicios externos, como el consumo de la API y la conversión de datos.

#### Clases en `service`:

- **ConsumoAPI**: Clase que se encarga de consumir la API externa para obtener datos de libros y autores.

- **ConvierteDatos**: Clase que maneja la conversión de los datos obtenidos de la API en objetos que pueden ser almacenados en la base de datos.

- **IConvierteDatos (Interface)**: Interfaz que define los métodos necesarios para convertir los datos de la API en objetos del modelo.

---
## Mapeo de Entidades 📊

En este proyecto utilizamos **JPA (Java Persistence API)** y **Jackson** para mapear las clases modelo a tablas en la base de datos y para manejar la serialización/deserialización de objetos. A continuación se detallan las principales anotaciones utilizadas:

### 1. **`@Entity` 📦**  
La anotación `@Entity` marca una clase como una **entidad JPA**, lo que significa que esa clase será mapeada a una tabla en la base de datos. Cada entidad debe tener al menos un campo que se identifique como la **clave primaria**

---

### 2. **`@Table` 🏷️**  
La anotación `@Table` se utiliza para **especificar la tabla de la base de datos** con la que se va a mapear la entidad.

---

### 3. **`@Id` 🔑**  
Con `@Id` se indica el campo que es la **clave primaria** de la entidad. Esto permite que JPA sepa cómo identificar de manera única cada instancia de la entidad en la base de datos.

---

### 4. **`@GeneratedValue` ⚙️**  
La anotación `@GeneratedValue` define la **estrategia de generación de valores** para la clave primaria. 

---

### 5. **`@Column` 📝**  
`@Column` especifica el **mapeo de una columna en la tabla de la base de datos**.

---

### 6. **`@OneToMany` 🔗**  
La anotación `@OneToMany` define una relación **uno a muchos** entre dos entidades. Indica que una entidad está asociada a muchas instancias de otra entidad, como un autor que tiene muchos libros.

---

### 7. **`@ManyToOne` 🔄**  
La anotación `@ManyToOne` establece una relación **muchos a uno** entre dos entidades. Es el opuesto de `@OneToMany`. Se utiliza cuando varias instancias de una entidad están asociadas a una sola instancia de otra entidad. Por ejemplo, muchos libros pueden estar asociados a un solo autor.

---

### 8. **`@JsonAlias` 🔤**  
La anotación `@JsonAlias` es utilizada en el contexto de **Jackson**, y permite asignar uno o más alias a un campo.

---

### 9. **`@JsonIgnoreProperties` 🚫**  
La anotación `@JsonIgnoreProperties` se utiliza para **ignorar propiedades específicas** de un objeto durante la serialización o deserialización con **Jackson**. 

---

<h1 style="text-align: left;">Desarrollado por 🎓</h1>
**Luis Felipe Méndez González 📠**  

---

<h1 style="text-align: left;">Capturas del funcionamientos 🎓</h1>
Aqui se verá como funciona el programa y que hace cada opción

![primeraOpcion](https://github.com/user-attachments/assets/bc985867-f80e-4806-94bf-9fe5f4b75833)

![segundaOpcionMostrarLibros](https://github.com/user-attachments/assets/f529313d-d2cd-47e8-9399-4a07b3383143)

![segundaOpcionMostrarAutores](https://github.com/user-attachments/assets/699de94f-ec17-4b02-8172-739c5a8eea1f)

![opcion4 y excepcion numerica](https://github.com/user-attachments/assets/166814b7-3ec8-4429-83de-24ab76a73522)

![opcion5 Prueba con en](https://github.com/user-attachments/assets/9c3c9b72-701b-445c-b926-f2348b9051f9)

Imagenes del aspecto base de datos:

![Captura de pantalla 2025-01-14 201904](https://github.com/user-attachments/assets/b1d03737-03e1-41cf-bedb-88cbc0fca538)


![Captura de pantalla 2025-01-14 201923](https://github.com/user-attachments/assets/ab134ffb-6345-47e6-b79c-c922a8c99d26)




