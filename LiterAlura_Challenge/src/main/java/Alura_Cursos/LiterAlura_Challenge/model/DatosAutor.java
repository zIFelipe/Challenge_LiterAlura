package Alura_Cursos.LiterAlura_Challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosAutor (
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer anoNacimiento,
        @JsonAlias("death_year") Integer anoFallecimiento
){
}
