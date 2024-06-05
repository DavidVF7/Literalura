
<p align="center">
  <img src="https://github.com/TU_USUARIO/TU_REPOSITORIO/assets/TU_IMAGEN"
</p>

# Gestor de Libros 📚

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

## Clases y Funcionalidades 🧩

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

## 👨‍💻 Desarrollado por
- [Tu Nombre]

## Instrucciones de Uso 🚀

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en IntelliJ IDEA u otro IDE de tu elección.
3. Configura la conexión a la base de datos en el archivo de propiedades correspondiente.
4. Ejecuta la clase `Principal.java` para iniciar el programa.
5. Sigue las instrucciones en pantalla para buscar libros, autores y obtener estadísticas.

¡Disfruta gestionando tu biblioteca de libros!

## ¿Cómo funciona? 🎥
Aquí puedes ver una demostración visual de cómo funciona el proyecto:

[Demostración del proyecto](URL_DE_TU_VIDEO)
