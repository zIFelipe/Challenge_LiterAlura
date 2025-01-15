//Desarrollador Luis Felipe Méndez González

package Alura_Cursos.LiterAlura_Challenge.clasePrincipal;


import Alura_Cursos.LiterAlura_Challenge.model.*;
import Alura_Cursos.LiterAlura_Challenge.repository.libroRepository;
import Alura_Cursos.LiterAlura_Challenge.service.ConsumoAPI;
import Alura_Cursos.LiterAlura_Challenge.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private libroRepository libroRepo;
    private List<Libro> libros;
    List<Autor> autores;

    public Principal(libroRepository repositoryL){
        this.libroRepo = repositoryL;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Libro por titulo
                    2 - Listado de libros registrados
                    3 - Listado de autores registrados
                    4 - Listado de Autores vivos en determinado año
                    5 - Listado de libros por idioma
                             
                    0 - Salir
                    """;

            try{
                System.out.println(menu);
                System.out.print("Ingrese una Opcion numerica: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listadoLibrosRegistrados();
                        break;
                    case 3:
                        listadoAutoresRegistrados();
                        break;
                    case 4:
                        listadoAutoresVivosEnFecha();
                        break;
                    case 5:
                        listadoLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }catch (NumberFormatException e){
                System.out.println("Error debe de ingresar un numero valido, intentelo de nuevo ");
                scanner.nextLine();
            }catch (Exception e){
                System.out.println("Error inesperado:  ");
                scanner.nextLine();
            }
        }


    }



    ///////////Inicio metodo opcion 1//////////////////////////
    private void buscarLibroPorTitulo() {
        System.out.println("Ingresa el título del libro que desea encontrar");
        String titulo = scanner.nextLine().toLowerCase();

        //realizamos la búsqueda solo por el título del libro en la API
        String url = URL_BASE + "?search=" + titulo.replace(" ", "+");
        String json = consumoAPI.obtenerDatos(url);

        if (json.isEmpty() || !json.contains("\"count\":0,\"next\":null,\"previous\":null,\"results\":[]")) {
            var datos = conversor.obtenerDatos(json, datosListaLibros.class);

            Optional<DatosLibro> libroEnBusqueda = datos.libros().stream()
                    .filter(libro -> libro.titulo().toLowerCase().contains(titulo))
                    .findFirst();

            if (libroEnBusqueda.isPresent()) {
                DatosLibro libro = libroEnBusqueda.get();
                mostrarInformacionLibro(libro);

                try {
                    //NO buscamos autores en la API, solo agregamos a los autores en la base de datos
                    String nombreAutor = obtenerNombreAutor(libro);
                    Optional<Autor> autorGuardado = libroRepo.buscarAutorPorSuNombre(nombreAutor);

                    Optional<Libro> libroBusqueda = libroRepo.buscarLibroPorTitulo(titulo);

                    if (libroBusqueda.isPresent()) {
                        System.out.println("El libro ya se encuentra almacenado.");
                    } else {
                        //si el Autor no existe en la base de datos, lo creamos
                        Autor nuevoAutor = autorGuardado.orElseGet(() -> obtenerNuevoAutor(libro));
                        nuevoAutor.setLibros(List.of(new Libro(libro)));
                        libroRepo.save(nuevoAutor);
                    }
                } catch (Exception e) {
                    System.out.println("Ocurrió un error inesperado: " + e.getMessage());
                }
            } else {
                System.out.println("Libro no encontrado");
            }
        } else {
            System.out.println("Error la busqueda, libro no encontrado");
        }
    }

    private void mostrarInformacionLibro(DatosLibro libro) {
        System.out.println("\n*********** LIBRO ************" +
                "\nTítulo: " + libro.titulo() +
                "\nAutor: " + libro.autores().stream().map(a -> a.nombre()).limit(1).collect(Collectors.joining()) +
                "\nIdioma: " + libro.idioma().stream().collect(Collectors.joining()) +
                "\nDescargas: " + libro.numeroDescargas() +
                "\n***********************************************\n"
        );
    }

    private String obtenerNombreAutor(DatosLibro libro) {
        //Se toma el nombre del primer autor
        return libro.autores().stream().map(a -> a.nombre()).collect(Collectors.joining());
    }

    private Autor obtenerNuevoAutor(DatosLibro libro) {
        //rcear un nuevo autor a partir del primer autor en la lista del libro
        Autor nuevoAutor = new Autor(libro.autores().stream().findFirst().orElseThrow());
        libroRepo.save(nuevoAutor);
        return nuevoAutor;
    }

/////////////////Fin metodo opcion 1////////////////////



    private void listadoLibrosRegistrados() {
        try{
            libros = libroRepo.librosRegistradosEnBD();
            if(libros.isEmpty()){
                System.out.println("No hay libros registrados");
            }else{
                System.out.println("-----Listado de libros registrados-----");
                libros.stream().forEach(System.out::println);
            }

        }catch (Exception e){
            System.out.println("Error  al intentar enlistar los libros:  " + e.getMessage());
        }


    }

    private void listadoAutoresRegistrados() {
        try{
            autores = libroRepo.findAll();
            if(autores.isEmpty()){
                System.out.println("No hay autores registrados");
            }else{
                System.out.println("-----Listado de autores registrados-----");
                autores.stream().forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Error  al intentar enlistar a los autores:  " + e.getMessage());
        }
    }

    private void listadoAutoresVivosEnFecha() {
        System.out.println("Ingresa el año para consultar autores vivos en dicho año");
        Integer AnoConsulta = scanner.nextInt();
        try{
            if(AnoConsulta == null || AnoConsulta <= 0){
                throw new IllegalArgumentException("El año proporcionado no es valido, por favor intentelo de nuevo");
            }
            List<Autor> autoresVivos = libroRepo.autoresVivosEnFechaDeterminada(AnoConsulta);

            if(autoresVivos.isEmpty()){
                System.out.println("No se encontraron autores vivos en el año: "+ AnoConsulta);

            }else{
                System.out.println("\n ------Autores vivos en el año " + AnoConsulta + ":" + " ---------");
                for(Autor autor : autoresVivos){
                    System.out.println("-Nombre del autor: " + autor.getNombre() +
                            "\n-Año de fallecimiento: "+ autor.getAnoFallecimiento() +
                            " \n-----------------------------------------");
                }
            }
        }catch (IllegalArgumentException e){
            System.out.println("Error de entrada: " + e.getMessage()); //No deberia mostrar el e.getMessage() talo cual en la colsola, efectos de practica

        }catch (Exception e){
            System.out.println("Error un error inesperado: " + e.getMessage()); //No deberia mostrar el e.getMessage() talo cual en la colsola, efectos de practica
        }
    }



    private void listadoLibrosPorIdioma() {
        try{
            System.out.println("\n*********** Idiomas/Lenguajes ************" +
                    "\n es = Español " +
                    "\n en = Inglés " +
                    "\n pt = Portugués " +
                    "\n fr = Francés: " +
                    "\n***********************************************\n" +
                    "\n"
            );
            System.out.println("Ingrese el idioma para la busqueda de libros en el inventario");
            var idioma = scanner.nextLine();
            var lenguaje = Idioma.fromString(idioma);
            List<Libro> librosPorIdioma = libroRepo.buscarLibrosPorElIdioma(lenguaje);
            if(!librosPorIdioma.isEmpty()){
                System.out.println("\nLos libros con el idioma seleccionado " + "|" + lenguaje + "|" + " son: " + "\n");
                librosPorIdioma.stream().forEach(System.out::println);
            }else{
                System.out.println("No existen libros en los registros con el idioma que escogió: "+ lenguaje);
            }

        }catch (IllegalArgumentException e){
            System.out.println("Error el idioma que ingreso es invalido, favor intentralo de nuevo " + e.getMessage());//No deberia mostrar el e.getMessage() talo cual en la colsola, efectos de practica
        }catch (Exception e){
            System.out.println("Error un error inesperado al realizar la busqueda: " + e.getMessage());//No deberia mostrar el e.getMessage() talo cual en la colsola, efectos de practica
        }

    }


}
