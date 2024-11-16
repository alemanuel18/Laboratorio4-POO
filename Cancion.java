public class Cancion {
    private String nombre;
    private int duracion; 
    private String autor;
    private String genero;
    private static int contador = 1; 
    private int id; 

    public Cancion(String nombre, int duracion, String autor, String genero) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.autor = autor;
        this.genero = genero;
        this.id = contador++; 
    }

    // Métodos getter
    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getId() {
        return id;
    }

    public String mostrarInformacion() {
        return"Nombre: " + nombre + ", Duración: " + duracion + "s, Autor: " + autor + ", Género: " + genero;
    }
}

