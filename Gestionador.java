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
    private boolean telefonoConectado = false;

    Contacto contacto1= new Contacto("Manuel", "19283743");
    Contacto contacto2= new Contacto("Samuel", "92742812");
    Contacto contacto3= new Contacto("Raul", "29741014");

    ArrayList<Contacto> contacto=new ArrayList<>();

    public void agregarContacto(){
        contacto.add(contacto1);
        contacto.add(contacto2);
        contacto.add(contacto3);
    }


    public String encender() {
        if (!encendido) {
            encendido = true;
            return "Radio encendido.";
        } else {
            return "El radio ya está encendido.";
        }
    }

    public String apagar() {
        if (encendido) {
            encendido = false;
            telefonoConectado = false;
            return "Radio apagado.";
        } else {
            return "El radio ya está apagado.";
        }
    }

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

    public String cambiarAMFM() {
        if (encendido) {
            modo = modo.equals("FM") ? "AM" : "FM";
            return "Modo cambiado a " + modo;
        } else {
            return "No se puede cambiar de AM a FM. El radio está apagado.";
        }
    }

    public String cambiarEstacion(double incremento) {
        if (encendido) {
            estacionActual += incremento;
            return "Estación cambiada a " + estacionActual + " " + modo;
        } else {
            return "No se puede cambiar la estación. El radio está apagado.";
        }
    }

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


    public double cargarEmisora(int posicion) {
        if (encendido && posicion >= 1 && posicion <= emisorasGuardadas.size()) {
            double frecuencia = emisorasGuardadas.get(posicion - 1);
            return frecuencia;
        } else {
            return -1;
        }
    }

    public String seleccionarListaReproduccion(String lista) {
        if (encendido) {
            return "Lista de reproducción '" + lista + "' seleccionada.";
        } else {
            return "Radio está apagado.";
        }
    }

    public String cambiarCancion(String direccion) {
        if (encendido) {
            return "Canción " + (direccion.equals("adelante") ? "siguiente" : "anterior") + " seleccionada.";
        } else {
            return "Radio está apagado.";
        }
    }

    public String escucharCancion() {
        if (encendido) {
            return "Escuchando canción...";
        } else {
            return "Radio está apagado.";
        }
    }

    public String conectarTelefono(String dispositivo) {
        if (encendido) {
            telefonoConectado = true;
            return "Teléfono " + dispositivo + " conectado.";
        } else {
            return "Radio está apagado.";
        }
    }

    public String desconectarTelefono() {
        if (encendido && telefonoConectado) {
            telefonoConectado = false;
            return "Teléfono desconectado.";
        } else {
            return "No hay teléfono para desconectar o el radio está apagado.";
        }
    }

    public String mostrarContactos() {
        if (encendido && telefonoConectado) {
            return"Mostrando contactos...\n1."+contacto1+"\n2."+contacto2+"\n3."+contacto3;
        } else {
            return "No se pueden mostrar contactos. Asegúrese de que el radio esté encendido y un teléfono esté conectado.";
        }
    }

    public String llamarContacto(String nombre) {
        if (encendido && telefonoConectado) {
            // Recorrer la lista de contactos para verificar si existe
            for (Contacto contacto : contacto) {
                if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                    ultimoContactoLlamado = contacto;
                    return "Llamando a " + nombre + "...";
                }
            }
            return "El contacto '" + nombre + "' no existe en la lista de contactos.";
        } else {
            return "No se puede realizar la llamada. Asegúrese de que el radio esté encendido y el teléfono esté conectado.";
        }
    }
    

    public String finalizarLlamada() {
        if (encendido) {
            return "Llamada finalizada.";
        } else {
            return "No hay llamada activa o el radio está apagado.";
        }
    }


    @Override
    public String cambiar(boolean sonido) {
        if (encendido && sonido) {
            return "Se camnio a auriculares";
        }else if (encendido && sonido) {
            return "Se cambio a spekers";   
        }
        else {
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

    public boolean isEncendido() {
        return encendido;
    }


    public boolean isTelefonoConectado() {
        return telefonoConectado;
    }
}
