package Alura_Cursos.LiterAlura_Challenge.repository;

import Alura_Cursos.LiterAlura_Challenge.model.Autor;
import Alura_Cursos.LiterAlura_Challenge.model.Idioma;
import Alura_Cursos.LiterAlura_Challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface libroRepository extends JpaRepository<Autor, Long> {

    //Tabla libro
    @Query("SELECT l FROM Libro l JOIN l.autor a WHERE l.titulo LIKE %:titulo%")
    Optional<Libro> buscarLibroPorTitulo(@Param("titulo") String titulo);
    @Query("SELECT l FROM Autor a JOIN a.libros l WHERE l.idioma = :idioma")
    List<Libro> buscarLibrosPorElIdioma(@Param("idioma") Idioma idioma);
    @Query("SELECT l FROM Autor a JOIN a.libros l ORDER BY l.titulo")
    List<Libro> librosRegistradosEnBD();

    //tabla autor
    @Query("SELECT a FROM Libro l JOIN l.autor a WHERE a.nombre LIKE %:nombre%")
    Optional<Autor> buscarAutorPorSuNombre(@Param("nombre") String nombre);
    @Query("SELECT a FROM Autor a WHERE (a.anoFallecimiento IS NULL OR a.anoFallecimiento >= :ano) AND a.anoNacimiento <= :ano ORDER BY a.nombre")
    List<Autor> autoresVivosEnFechaDeterminada(@Param("ano") Integer anio);

}
