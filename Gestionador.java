import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Gestionador implements ClaseA {
    private boolean encendido = false;
    private String modo = "FM"; // AM o FM
    private double estacionActual = 88.5;
    private int volumen = 0;
    private ArrayList<Double> emisorasGuardadas = new ArrayList<>();
    private HashMap<String, Cancion> playlistActual = new HashMap<>();
    private Contacto ultimoContactoLlamado = null;
    private boolean enLlamada = false;
    private boolean telefonoConectado = false;

    public void encender() {
        if (!encendido) {
            encendido = true;
            System.out.println("Radio encendido.");
        } else {
            System.out.println("El radio ya está encendido.");
        }
    }

    public void apagar() {
        if (encendido) {
            encendido = false;
            enLlamada = false;
            telefonoConectado = false;
            System.out.println("Radio apagado.");
        } else {
            System.out.println("El radio ya está apagado.");
        }
    }

    public void subirVolumen() {
        if (encendido && volumen < 100) {
            volumen++;
            System.out.println("Volumen aumentado a: " + volumen);
        } else if (!encendido) {
            System.out.println("No se puede subir el volumen. El radio está apagado.");
        } else {
            System.out.println("Volumen máximo alcanzado.");
        }
    }

    public void bajarVolumen() {
        if (encendido && volumen > 0) {
            volumen--;
            System.out.println("Volumen disminuido a: " + volumen);
        } else if (!encendido) {
            System.out.println("No se puede bajar el volumen. El radio está apagado.");
        } else {
            System.out.println("Volumen mínimo alcanzado.");
        }
    }

    public void cambiarAMFM() {
        if (encendido) {
            modo = modo.equals("FM") ? "AM" : "FM";
            System.out.println("Modo cambiado a " + modo);
        } else {
            System.out.println("No se puede cambiar de AM a FM. El radio está apagado.");
        }
    }

    public void cambiarEstacion(double incremento) {
        if (encendido) {
            estacionActual += incremento;
            System.out.println("Estación cambiada a " + estacionActual + " " + modo);
        } else {
            System.out.println("No se puede cambiar la estación. El radio está apagado.");
        }
    }

    public void guardarEmisora(double frecuencia) {
        if (encendido) {
            if (emisorasGuardadas.size() < 50) {
                emisorasGuardadas.add(frecuencia);
                System.out.println("Emisora " + frecuencia + " guardada en posición " + emisorasGuardadas.size());
            } else {
                System.out.println("Ya se ha alcanzado el límite de 50 emisoras guardadas.");
            }
        } else {
            System.out.println("No se puede guardar la emisora. El radio está apagado.");
        }
    }


    public double cargarEmisora(int posicion) {
        if (encendido && posicion >= 1 && posicion <= emisorasGuardadas.size()) {
            double frecuencia = emisorasGuardadas.get(posicion - 1);
            System.out.println("Cargando emisora en la posición " + posicion + ": " + frecuencia);
            return frecuencia;
        } else {
            System.out.println("No se puede cargar la emisora. El radio está apagado o la posición es inválida.");
            return -1;
        }
    }


    public void seleccionarListaReproduccion(String lista) {
        if (encendido) {
            System.out.println("Lista de reproducción '" + lista + "' seleccionada.");
        } else {
            System.out.println("Radio está apagado.");
        }
    }


    public void cambiarCancion(String direccion) {
        if (encendido) {
            System.out.println("Canción " + (direccion.equals("adelante") ? "siguiente" : "anterior") + " seleccionada.");
        } else {
            System.out.println("Radio está apagado.");
        }
    }


    public void escucharCancion() {
        if (encendido) {
            System.out.println("Escuchando canción...");
        } else {
            System.out.println("Radio está apagado.");
        }
    }


    public void conectarTelefono(String dispositivo) {
        if (encendido) {
            telefonoConectado = true;
            System.out.println("Teléfono " + dispositivo + " conectado.");
        } else {
            System.out.println("Radio está apagado.");
        }
    }

    public void desconectarTelefono() {
        if (encendido && telefonoConectado) {
            telefonoConectado = false;
            System.out.println("Teléfono desconectado.");
        } else {
            System.out.println("No hay teléfono para desconectar o el radio está apagado.");
        }
    }


    public void mostrarContactos() {
        if (encendido && telefonoConectado) {
            System.out.println("Mostrando contactos...");
        } else {
            System.out.println("No se pueden mostrar contactos. Asegúrese de que el radio esté encendido y un teléfono esté conectado.");
        }
    }


    public void llamarContacto(String nombre) {
        if (encendido && telefonoConectado) {
            ultimoContactoLlamado = new Contacto(nombre, "1234567890");
            enLlamada = true;
            System.out.println("Llamando a " + nombre + "...");
        } else {
            System.out.println("No se puede realizar la llamada. Asegúrese de que el radio esté encendido y el teléfono esté conectado.");
        }
    }


    public void finalizarLlamada() {
        if (encendido && enLlamada) {
            enLlamada = false;
            System.out.println("Llamada finalizada.");
        } else {
            System.out.println("No hay llamada activa o el radio está apagado.");
        }
    }


    public void cambiarSpeaker() {
        if (encendido && telefonoConectado) {
            System.out.println("Cambiado a speaker.");
        } else {
            System.out.println("No se puede cambiar a speaker. Asegúrese de que el radio esté encendido y un teléfono esté conectado.");
        }
    }

    public String cambiar(boolean sonido) {
        if (encendido) {
            return "Sonido " + (sonido ? "encendido" : "apagado");
        } else {
            return "No se puede cambiar el estado del sonido. El radio está apagado.";
        }
    }

    public String planificarViajes(Date inicio, Date fin, String lugarInicio, String lugarFinal) {
        if (encendido) {
            return "Viaje planificado desde " + lugarInicio + " hasta " + lugarFinal + " desde el " + inicio.toString() + " hasta el " + fin.toString();
        } else {
            return "No se puede planificar viajes. El radio está apagado.";
        }
    }

    public void llamarUltimoContacto() {
        if (encendido && ultimoContactoLlamado != null) {
            enLlamada = true;
            System.out.println("Llamando al último contacto: " + ultimoContactoLlamado.getNombre());
        } else {
            System.out.println("No hay último contacto registrado o el radio está apagado.");
        }
    }

    public boolean isEncendido() {
        return encendido;
    }

    public boolean isEnLlamada() {
        return enLlamada;
    }

    public boolean isTelefonoConectado() {
        return telefonoConectado;
    }
}
