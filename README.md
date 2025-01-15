
![images](https://github.com/user-attachments/assets/45dac5df-21b3-436e-b148-b7cbf94ec7bf)

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

<h1 style="text-align: left;">Instalación 🖥️</h1>
1. Clona este repositorio:

    ```bash
    git clone [https://github.com/tu-usuario/literalura.git](https://github.com/zIFelipe/Challenge_LiterAlura.git)
    cd LiterAlura
    ```
2. Configura tu base de datos en el archivo`application.properties`:
   
               spring.application.name=LiterAlura_Callenge
                  spring.datasource.url=jdbc:postgresql://${DB_HOST}/LiterAlura_BD
                  spring.datasource.username=${DB_USER}
                  spring.datasource.password=${DB_PASSWORD}
                  spring.datasource.driver-class-name=org.postgresql.Driver
                  hibernate.dialect=org.hibernate.dialect.HSQLDialect
                  spring.jpa.hibernate.ddl-auto=update
                  spring.jpa.show-sql=true
                  spring.jpa.format-sql = true

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
