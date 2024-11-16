import java.util.Date;

public interface ClaseA {
    String encender();
    String apagar();
    String subirVolumen();
    String bajarVolumen();
    String cambiarAMFM();
    String cambiarEstacion(double incremento);
    String guardarEmisora(double frecuencia);
    double cargarEmisora(int posicion);
    String seleccionarListaReproduccion(String lista);
    String cambiarCancion(String direccion);
    String escucharCancion();
    String conectarTelefono(String dispositivo);
    String desconectarTelefono();
    String mostrarContactos();
    String llamarContacto(String nombre);
    String finalizarLlamada();
    String cambiarSpeaker();
    String cambiar(boolean sonido);
    String planificarViajes(Date inicio, Date fin, String lugarInicio, String lugarFinal);
    String llamarUltimoContacto();
}
