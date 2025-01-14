package Alura_Cursos.LiterAlura_Challenge.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);

}
