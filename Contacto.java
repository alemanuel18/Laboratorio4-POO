public class Contacto {
    private String nombre;
    private String numero;

    public Contacto(String nombre, String numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void mostrarInformacion() {
        System.out.println("Contacto: " + nombre + ", Número: " + numero);
    }
}