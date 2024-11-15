public class Cancion {
    private String nombre;
    private int duracion; // en segundos
    private String autor;
    private String genero;

    public Cancion(String nombre, int duracion, String autor, String genero) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.autor = autor;
        this.genero = genero;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + ", Duración: " + duracion + "s, Autor: " + autor + ", Género: " + genero);
    }
}
