import java.util.Date;

public interface ClaseA {
    void encender();
    void apagar();
    void subirVolumen();
    void bajarVolumen();
    void cambiarAMFM();
    void cambiarEstacion(double frecuencia);
    void guardarEmisora(double frecuencia);
    double cargarEmisora(int posicion);
    void seleccionarListaReproduccion(String lista);
    void cambiarCancion(String direccion);
    void escucharCancion();
    void conectarTelefono(String dispositivo);
    void desconectarTelefono();
    void mostrarContactos();
    void llamarContacto(String nombre);
    void finalizarLlamada();
    void cambiarSpeaker();
    String cambiar(boolean sonido);
    String planificarViajes(Date inicio, Date fin, String lugarInicio, String lugarFinal);
    void llamarUltimoContacto();
}
