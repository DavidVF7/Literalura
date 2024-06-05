<p align="center">
  <img src="https://github.com/DavidVF7/Literalura/assets/103916971/6966b0be-98cc-4571-a3b6-e48c3ded7d11"
</p>

# Literalura 📚

Desarrollado como parte del Challenge de gestión de libros, impuesto por Alura Latam en colaboración con Oracle en el programa ONE, como parte de la especialización Back-End.

## Descripción 📝

Este proyecto es un Gestor de Libros desarrollado en Java que te permite buscar libros por título, autores por nombre, listar libros y autores registrados, y obtener estadísticas generales sobre la base de datos de libros. La aplicación interactúa con una API externa para obtener información y utiliza una base de datos local para almacenar y gestionar los datos de libros y autores.

## Tecnologías Utilizadas 💻

- **Lenguaje de Programación:** Java
- **API de Libros:** Se utilizó una API externa (gutendex.com) para obtener información sobre libros y autores.
- **Spring Framework:** Para la gestión de la inyección de dependencias y acceso a la base de datos.
- **Base de Datos:** Utilización de una base de datos (posiblemente H2, MySQL, etc.) para el almacenamiento de datos.
- **Control de Versiones:** Git/GitHub se usaron para el control de versiones del proyecto y la colaboración en equipo.
- **Entorno de Desarrollo Integrado (IDE):** IntelliJ IDEA fue el entorno de desarrollo utilizado para escribir, depurar y ejecutar el código Java.

## Funcionalidades 🧩

### Principal.java

El punto de entrada principal del programa. Aquí se maneja la interacción con el usuario a través de la consola, mostrando un menú de opciones y gestionando las consultas sobre libros y autores.

#### Funcionalidades principales:

- Buscar libro por título.
- Buscar autor por nombre.
- Listar libros registrados.
- Listar autores registrados.
- Listar autores vivos en un determinado año.
- Listar libros por idioma.
- Obtener estadísticas generales.
- Listar los 10 libros más descargados.
- Listar autores nacidos o fallecidos en algún año específico.

### ConsumoApi.java

Clase responsable de realizar consultas a una API externa para obtener información sobre libros y autores.

### ConvierteDatos.java

Esta clase se encarga de convertir los datos obtenidos de la API para su uso en la aplicación.

### AutorRepository.java

Interfaz que maneja las operaciones de la base de datos relacionadas con los autores.

## Clases Modelo y Mapeo de Entidades 🗃️

En este proyecto, utilizamos varias clases modelo para representar las entidades del dominio, como autores y libros. Estas clases están mapeadas a tablas de la base de datos utilizando JPA (Java Persistence API).

### Autor.java

La clase `Autor` representa la entidad "Autor" en la base de datos. Esta clase incluye atributos como el nombre del autor, su fecha de nacimiento y fallecimiento, así como una lista de libros asociados. La relación entre autores y libros se gestiona mediante una asociación de uno a muchos.

#### Atributos principales:
- **id**: Identificador único del autor.
- **nombre**: Nombre del autor.
- **fechaDeNacimiento**: Año de nacimiento del autor.
- **fechaDeFallecimiento**: Año de fallecimiento del autor (puede ser nulo si el autor está vivo).
- **libros**: Lista de libros escritos por el autor.

### Datos.java

La clase `Datos` se utiliza para mapear la estructura JSON de la respuesta de la API. Esta clase contiene el total de resultados y una lista de libros obtenidos de la API.

#### Atributos principales:
- **total**: Número total de resultados.
- **libros**: Lista de objetos `DatosLibro` que representan los libros obtenidos de la API.

### DatosLibro.java

La clase `DatosLibro` representa la información de un libro obtenida de la API. Esta clase incluye atributos como el título del libro, una lista de autores, los idiomas en los que está disponible y el número de descargas.

#### Atributos principales:
- **titulo**: Título del libro.
- **autores**: Lista de objetos `DatosAutor` que representan los autores del libro.
- **idiomas**: Lista de idiomas en los que está disponible el libro.
- **numeroDeDescargas**: Número de descargas del libro.

### Mapeo de Entidades

Utilizamos las siguientes anotaciones de JPA para mapear las clases modelo a tablas de la base de datos:

- **@Entity**: Marca una clase como una entidad JPA.
- **@Table**: Especifica la tabla de la base de datos con la que se va a mapear la entidad.
- **@Id**: Indica el campo que es la clave primaria.
- **@GeneratedValue**: Define la estrategia de generación de valores para la clave primaria.
- **@Column**: Especifica el mapeo de una columna en la tabla de base de datos.
- **@OneToMany**: Define una relación uno a muchos entre dos entidades.

Estas anotaciones permiten que las clases de modelo se conviertan en entidades persistentes en una base de datos relacional, facilitando la interacción con la base de datos a través de repositorios y servicios.

## 👨‍💻 Desarrollado por
- David Velasco Fierros

## Instrucciones de Uso 🚀

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en IntelliJ IDEA u otro IDE de tu elección.
3. Configura la conexión a la base de datos en el archivo de propiedades correspondiente.
4. Ejecuta la clase `Principal.java` para iniciar el programa.
5. Sigue las instrucciones en pantalla para buscar libros, autores y obtener estadísticas.

¡Disfruta gestionando tu biblioteca de libros!

## 📷 Capturas de pantalla
Aquí puedes ver una demostración visual de cómo funciona el proyecto:

![literalura0](https://github.com/DavidVF7/Literalura/assets/103916971/15036550-36ce-454b-9df3-29c48e074e9a)
![literalura1](https://github.com/DavidVF7/Literalura/assets/103916971/3adddd81-8234-4a3b-991c-51262b293038)
![literalura2](https://github.com/DavidVF7/Literalura/assets/103916971/50bb1371-066b-4435-a3cd-ebd46a5027a8)
![literalura3](https://github.com/DavidVF7/Literalura/assets/103916971/63694054-500b-4c49-96c2-7427b11f8212)
![literalura4](https://github.com/DavidVF7/Literalura/assets/103916971/17939e0f-5636-485a-b135-a8d1a25385bd)
![literalura5](https://github.com/DavidVF7/Literalura/assets/103916971/3342f7fc-c265-45c9-970b-17134753cbb8)
![literalura6](https://github.com/DavidVF7/Literalura/assets/103916971/59453c4e-a805-4806-841e-e96c6fcad20a)
![literalura7](https://github.com/DavidVF7/Literalura/assets/103916971/e36a204f-b322-4469-bba7-a00f627d07af)
![literalura8](https://github.com/DavidVF7/Literalura/assets/103916971/3ba63d40-f491-4c2f-8b41-7a4c43435252)
![literalura9](https://github.com/DavidVF7/Literalura/assets/103916971/25e7ca8d-2195-416d-a6b3-7efc4d8a81f8)
![literalura10](https://github.com/DavidVF7/Literalura/assets/103916971/67dd0bd2-f838-4afe-8bbb-cc8d1fe6bbe9)
