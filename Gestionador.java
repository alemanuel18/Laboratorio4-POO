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

    @Override
    public String encender() {
        if (!encendido) {
            encendido = true;
            return "Radio encendido.";
        } else {
            return "El radio ya está encendido.";
        }
    }

    @Override
    public String apagar() {
        if (encendido) {
            encendido = false;
            enLlamada = false;
            telefonoConectado = false;
            return "Radio apagado.";
        } else {
            return "El radio ya está apagado.";
        }
    }

    @Override
    public String subirVolumen() {
        if (encendido && volumen < 100) {
            volumen++;
            return "Volumen aumentado a: " + volumen;
        } else if (!encendido) {
            return "No se puede subir el volumen. El radio está apagado.";
        } else {
            return "Volumen máximo alcanzado.";
        }
    }

    @Override
    public String bajarVolumen() {
        if (encendido && volumen > 0) {
            volumen--;
            return "Volumen disminuido a: " + volumen;
        } else if (!encendido) {
            return "No se puede bajar el volumen. El radio está apagado.";
        } else {
            return "Volumen mínimo alcanzado.";
        }
    }

    @Override
    public String cambiarAMFM() {
        if (encendido) {
            modo = modo.equals("FM") ? "AM" : "FM";
            return "Modo cambiado a " + modo;
        } else {
            return "No se puede cambiar de AM a FM. El radio está apagado.";
        }
    }

    @Override
    public String cambiarEstacion(double incremento) {
        if (!encendido) {
            return "El radio está apagado. Encienda el radio para cambiar la emisora.";
        }
    
        if (modo.equals("FM")) {
            if (estacionActual + incremento >= 87.0 && estacionActual + incremento <= 102.5) {
                estacionActual += incremento;
            } else {
                return "Estación fuera de rango en FM, el rango es 87.0 - 102.5.";
            }
        } else if (modo.equals("AM")) {
            if (estacionActual + incremento >= 520 && estacionActual + incremento <= 1500) {
                estacionActual += incremento;
            } else {
                return "Estación fuera de rango en AM, el rango es de 520 - 1500.";
            }
        }
        return "Estación cambiada a " + estacionActual + " " + modo;
    }
    

    @Override
    public String guardarEmisora(double frecuencia) {
        if (encendido) {
            if (emisorasGuardadas.size() < 50) {
                emisorasGuardadas.add(frecuencia);
                return "Emisora " + frecuencia + " guardada en posición " + emisorasGuardadas.size();
            } else {
                return "Ya se ha alcanzado el límite de 50 emisoras guardadas.";
            }
        } else {
            return "No se puede guardar la emisora. El radio está apagado.";
        }
    }

    @Override
    public double cargarEmisora(int posicion) {
        if (encendido && posicion >= 1 && posicion <= emisorasGuardadas.size()) {
            double frecuencia = emisorasGuardadas.get(posicion - 1);
            return frecuencia;
        } else {
            return -1;
        }
    }

    @Override
    public String seleccionarListaReproduccion(String lista) {
        if (encendido) {
            return "Lista de reproducción '" + lista + "' seleccionada.";
        } else {
            return "Radio está apagado.";
        }
    }

    @Override
    public String cambiarCancion(String direccion) {
        if (encendido) {
            return "Canción " + (direccion.equals("adelante") ? "siguiente" : "anterior") + " seleccionada.";
        } else {
            return "Radio está apagado.";
        }
    }

    @Override
    public String escucharCancion() {
        if (encendido) {
            return "Escuchando canción...";
        } else {
            return "Radio está apagado.";
        }
    }

    @Override
    public String conectarTelefono(String dispositivo) {
        if (encendido) {
            telefonoConectado = true;
            return "Teléfono " + dispositivo + " conectado.";
        } else {
            return "Radio está apagado.";
        }
    }

    @Override
    public String desconectarTelefono() {
        if (encendido && telefonoConectado) {
            telefonoConectado = false;
            return "Teléfono desconectado.";
        } else {
            return "No hay teléfono para desconectar o el radio está apagado.";
        }
    }

    @Override
    public String mostrarContactos() {
        if (encendido && telefonoConectado) {
            return "Mostrando contactos...";
        } else {
            return "No se pueden mostrar contactos. Asegúrese de que el radio esté encendido y un teléfono esté conectado.";
        }
    }

    @Override
    public String llamarContacto(String nombre) {
        if (encendido && telefonoConectado) {
            ultimoContactoLlamado = new Contacto(nombre, "1234567890");
            enLlamada = true;
            return "Llamando a " + nombre + "...";
        } else {
            return "No se puede realizar la llamada. Asegúrese de que el radio esté encendido y el teléfono esté conectado.";
        }
    }

    @Override
    public String finalizarLlamada() {
        if (encendido && enLlamada) {
            enLlamada = false;
            return "Llamada finalizada.";
        } else {
            return "No hay llamada activa o el radio está apagado.";
        }
    }

    @Override
    public String cambiarSpeaker() {
        if (encendido && telefonoConectado) {
            return "Cambiado a speaker.";
        } else {
            return "No se puede cambiar a speaker. Asegúrese de que el radio esté encendido y un teléfono esté conectado.";
        }
    }

    @Override
    public String cambiar(boolean sonido) {
        if (encendido) {
            return "Sonido " + (sonido ? "encendido" : "apagado");
        } else {
            return "No se puede cambiar el estado del sonido. El radio está apagado.";
        }
    }

    @Override
    public String planificarViajes(Date inicio, Date fin, String lugarInicio, String lugarFinal) {
        if (encendido) {
            return "Viaje planificado desde " + lugarInicio + " hasta " + lugarFinal + " desde el " + inicio.toString() + " hasta el " + fin.toString();
        } else {
            return "No se puede planificar viajes. El radio está apagado.";
        }
    }

    @Override
    public String llamarUltimoContacto() {
        if (encendido && ultimoContactoLlamado != null) {
            enLlamada = true;
            return "Llamando al último contacto: " + ultimoContactoLlamado.getNombre();
        } else {
            return "No hay último contacto registrado o el radio está apagado.";
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
