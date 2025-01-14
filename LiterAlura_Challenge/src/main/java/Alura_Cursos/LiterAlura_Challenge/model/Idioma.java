package Alura_Cursos.LiterAlura_Challenge.model;

public enum Idioma {

    Espanol("es"),
    Ingles("en"),
    Portugues("pt"),
    Frances("fr");

    private final String nombre;

    Idioma(String nombre) {
        this.nombre = nombre;
    }
    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.nombre.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("El idioma seleccionado no se encuentra disponible: " + text);
    }

}
