package com.aluracursos.desafioapilibros.principal;

import com.aluracursos.desafioapilibros.model.*;
import com.aluracursos.desafioapilibros.repository.AutorRepository;
import com.aluracursos.desafioapilibros.service.ConsumoApi;
import com.aluracursos.desafioapilibros.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;

import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private AutorRepository repository;


    public Principal(AutorRepository repository) {
        this.repository = repository;
    }

    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n ¡¡BIENVENIDO AL LITERALURA!! \n
                    1 - Buscar libro por título
                    2 - Buscar autor por nombre
                    3 - Listar libros registrados
                    4 - Listar autores registrados
                    5 - Listar autores vivos en un determinado año
                    6 - Listar libros por idioma
                    7 - Estadísticas generales
                    8 - Top 10 libros más descargados
                    9 - Listar autores nacidos en algún año
                    10 - Listar autores fallecidos en algún año
                    
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    buscarAutorPorNombre();
                    break;
                case 3:
                    listarLibrosRegistrados();
                    break;
                case 4:
                    listarAutoresRegistrados();
                    break;
                case 5:
                    listarAutoresVivos();
                    break;
                case 6:
                    listarLibrosPorIdioma();
                    break;
                case 7:
                    estadisticas();
                    break;
                case 8:
                    top10();
                    break;
                case 9:
                    listarAutorPorFechaNacimiento();
                    break;
                case 10:
                    listarAutorPorFechaFallecimiento();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación ... \n");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }


    private void buscarLibroPorTitulo() {
        System.out.println("Ingresa el título del libro que desea buscar");
        var titulo = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+").toLowerCase());

        if (json.isEmpty() || !json.contains("\"count\":0,\"next\":null,\"previous\":null,\"results\":[]")) {
            var datos = conversor.obtenerDatos(json, Datos.class);

            Optional<DatosLibro> libroBuscado = datos.libros().stream()
                    .findFirst();

            if (libroBuscado.isPresent()) {
                System.out.println(
                        "\n------------- LIBRO --------------" +
                        "\nTítulo: " + libroBuscado.get().titulo() +
                        "\nAutor: " + libroBuscado.get().autores().stream()
                                .map(a -> a.nombre()).limit(1).collect(Collectors.joining()) +
                        "\nIdioma: " + libroBuscado.get().idiomas().stream()
                                .collect(Collectors.joining()) +
                        "\nNúmero de descargas: " + libroBuscado.get().numeroDeDescargas() +
                        "\n--------------------------------------\n"
                );

                try {
                    List<Libro> libroEncontrado = libroBuscado.stream()
                            .map(a -> new Libro(a))
                            .collect(Collectors.toList());

                    Autor autorAPI = libroBuscado.stream()
                            .flatMap(l -> l.autores().stream()
                                    .map(a -> new Autor(a)))
                            .collect(Collectors.toList()).stream().findFirst().get();

                    Optional<Autor> autorBD = repository.buscarAutorPorNombre(libroBuscado.get().autores().stream()
                            .map(a -> a.nombre())
                            .collect(Collectors.joining()));

                    Optional<Libro> libroOptional = repository.buscarLibroPorNombre(titulo);

                    if (libroOptional.isPresent()) {
                        System.out.println("El libro ya está guardado en la BD.");
                    } else {
                        Autor autor;
                        if (autorBD.isPresent()) {
                            autor = autorBD.get();
                            System.out.println("EL autor ya esta guardado en la BD");
                        } else {
                            autor = autorAPI;
                            repository.save(autor);
                        }
                        autor.setLibros(libroEncontrado);
                        repository.save(autor);
                    }
                } catch (Exception e) {
                    System.out.println("Warning! " + e.getMessage());
                }
            } else {
                System.out.println("Libro no encontrado");
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void buscarAutorPorNombre(){
        System.out.println("Ingrese el nombre del autor que desea buscar");

        try {
            var autorBuscado = teclado.nextLine();
            Optional<Autor> autor = repository.buscarAutorPorNombre(autorBuscado);
            if (autor.isPresent()){
                autor.stream()
                        .forEach(System.out::println);
            } else {
                System.out.println("Autor no encontrado");
            }
        } catch (Exception e){
            System.out.println("Ingrese un nombre correcto. - Warning: " + e.getMessage());
        }

    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = repository.librosRegistrados();
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autor;
        autor = repository.findAll();

        autor.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese un año para verificar el autor(es) que desea buscar");

        try {
            var fecha = Integer.parseInt(teclado.nextLine());
            List<Autor> autores = repository.listarAutoresVivos(fecha);

            if (!autores.isEmpty()){
                autores.stream()
                        .sorted(Comparator.comparing(Autor::getNombre))
                        .forEach(System.out::println);
            } else {
                System.out.println("Ningún autor vivo encontrado en este año");
            }

        } catch (NumberFormatException e){
            System.out.println("Ingrese un año válido " + e.getMessage());
        }

    }

    private void listarLibrosPorIdioma() {
        var menuIdiomas = """
                Elija una opción
                
                1 - Inglés
                2 - Español
                3 - Francés
                4 - Portugués
                5 - Alemán
                
                0 - Regresar
                """;
        System.out.println(menuIdiomas);

        try {
            var opcionIdioma = Integer.parseInt(teclado.nextLine());

            switch (opcionIdioma) {
                case 1:
                    buscarLibrosPorIdioma("en");
                    break;
                case 2:
                    buscarLibrosPorIdioma("es");
                    break;
                case 3:
                    buscarLibrosPorIdioma("fr");
                    break;
                case 4:
                    buscarLibrosPorIdioma("pt");
                    break;
                case 5:
                    buscarLibrosPorIdioma("de");
                    break;
                case 0:
                    System.out.println("Regresando ...");
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
            }
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida: " + e.getMessage());
        }
    }

    private void buscarLibrosPorIdioma(String idioma) {
        try {
            Idioma idiomaEnum = Idioma.valueOf(idioma.toUpperCase());
            List<Libro> libros = repository.librosPorIdioma(idiomaEnum);

            if (!libros.isEmpty()){
                libros.stream()
                        .forEach(System.out::println);
            } else {
                System.out.println("No hay libros registrados en ese idioma");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Introduce un idioma válido en el formato especificado");
        }
    }

    private void estadisticas() {
        var json = consumoApi.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        DoubleSummaryStatistics est = datos.libros().stream()
                .filter(d -> d.numeroDeDescargas() > 0)
                .collect(Collectors.summarizingDouble(DatosLibro::numeroDeDescargas));

        System.out.println("Cantidad media de descargas: " + est.getAverage());
        System.out.println("Cantidad máxima de descargas: " + est.getMax());
        System.out.println("Cantidad mínima de descargas: " + est.getMin());
        System.out.println("Cantidad de registros evaluados para calcular estadísticas: " + est.getCount());
    }

    private void top10() {
        System.out.println("\nTop 10 libros más descargados:\n");
        var json = consumoApi.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        datos.libros().stream()
                .sorted(Comparator.comparing(DatosLibro::numeroDeDescargas).reversed())
                .limit(10)
                .forEach(l ->
                    System.out.println("[" + l.numeroDeDescargas() + " descargas] - " + l.titulo()));
    }

    private void listarAutorPorFechaNacimiento(){
        System.out.println("Ingrese el año de nacimiento del autor");
        try {
            var nacimiento = Integer.valueOf(teclado.nextLine());
            List<Autor> autores = repository.listarAutorPorFechaNacimiento(nacimiento);
            if (!autores.isEmpty()) {
                autores.stream()
                        .forEach(System.out::println);
            } else {
                System.out.println("No se encontró ningún actor nacido en este año");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ingrese un año válido. - Warning: " + e.getMessage());
        }
    }

    private void listarAutorPorFechaFallecimiento(){
        System.out.println("Ingrese el año de fallecimiento del autor");
        try {
            var fallecimiento = Integer.valueOf(teclado.nextLine());
            List<Autor> autor = repository.listarAutorPorFechaFallecimiento(fallecimiento);
            if (!autor.isEmpty()) {
                autor.stream()
                        .forEach(System.out::println);
            } else {
                System.out.println("No se encontró ningún actor fallecido en este año");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un año válido. - Warning: " + e.getMessage());
        }
    }
}
