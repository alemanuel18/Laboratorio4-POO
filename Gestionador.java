import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
        if (!encendido) {
            return "El radio está apagado. Encienda el radio para guardar una emisora.";
        }

        if (emisorasGuardadas.size() >= 50) {
            return "No se puede guardar más emisoras. El límite de 50 emisoras ha sido alcanzado.";
        }
 
        if (emisorasGuardadas.contains(frecuencia)) {
            return "La emisora " + frecuencia + " ya está guardada.";
        }

        emisorasGuardadas.add(frecuencia);
        return "Emisora " + frecuencia + " guardada exitosamente.";
    }
    
    @Override
    public double cargarEmisora(int posicion) {
        if (!encendido) {
            System.out.println("El radio está apagado. Encienda el radio para cargar una emisora.");
            return -1;
        }
        if (emisorasGuardadas.isEmpty()){
            System.out.println("No hay emisoras guardadas.");
            return -1;
        }
        if (posicion < 1 || posicion > emisorasGuardadas.size()){
            System.out.println("Posición inválida. Seleccione un número entre 1 y " + emisorasGuardadas.size() + ".");
            return -1;
        }
        estacionActual = emisorasGuardadas.get(posicion - 1);
        System.out.println("Emisora cargada: " + estacionActual + " " + modo);
        return estacionActual;
    }
    

    @Override
    public String seleccionarListaReproduccion(String lista) {
        if (!encendido) {
            return "El radio está apagado. Encienda el radio para seleccionar una lista de reproducción.";
        }
    
        // Listas preconfiguradas
        HashMap<String, List<Cancion>> listasPreconfiguradas = new HashMap<>();
        listasPreconfiguradas.put("Rock", List.of(
            new Cancion("Two Faced", 181, "Linkin Park", "Rock"),
            new Cancion("BURN IR DOWN", 209, "Linkin Park", "Rock")
        ));
        listasPreconfiguradas.put("Electronica", List.of(
            new Cancion("Vice", 198, "Maiki Vanics", "Electónica"),
            new Cancion("The Right Song", 207, "Tiesto, Oliver Heldens", "Electónica")
        ));
    
        if (!listasPreconfiguradas.containsKey(lista)) {
            return "Lista de reproducción no encontrada. Pruebe con Rock o Electrónica.";
        }
    
        // Cargar lista seleccionada
        playlistActual.clear();
        for (Cancion cancion : listasPreconfiguradas.get(lista)) {
            playlistActual.put(String.valueOf(cancion.getId()), cancion);
        }
        return "Lista de reproducción '" + lista + "' seleccionada exitosamente.";
    }
    
    private int indiceCancionActual = 0;
    @Override
    public String cambiarCancion(String direccion) {
        if (!encendido) {
            return "El radio está apagado. Encienda el radio para cambiar de canción.";
        }
    
        if (playlistActual.isEmpty()) {
            return "No hay canciones en la lista de reproducción que has escogido.";
        }
        List<String> nombresCanciones = new ArrayList<>(playlistActual.keySet());
    
        if (direccion.equalsIgnoreCase("siguiente")) {
            if (indiceCancionActual < nombresCanciones.size() - 1) {
                indiceCancionActual++;
            } else {
                return "¡Ya estás en la última canción!";
            }
        } else if (direccion.equalsIgnoreCase("atras")) {
            if (indiceCancionActual > 0) {
                indiceCancionActual--;
            } else {
                return "¡Estás en la primera canción!";
            }
        } else {
            return "¡UPS! Recuerda usar siguiente o atras.";
        }
   
        Cancion cancionActual = playlistActual.get(nombresCanciones.get(indiceCancionActual));
        return "Canción actual: " + cancionActual.getNombre() + "\n" +
               "Duración: " + cancionActual.getDuracion() + "s\n" +
               "Autor: " + cancionActual.getAutor() + "\n" +
               "Género: " + cancionActual.getGenero();
    }
    

    @Override
    public String escucharCancion() {
        if (!encendido) {
            return "El radio está apagado. ¡Enciendalo para escuchar tus canciones!";
        }
        if (playlistActual.isEmpty()) {
            return "¡Oh no! ¡No hay canciones en la lista de reproducción actual!";
        }
        List<String> nombresCanciones = new ArrayList<>(playlistActual.keySet());
        Cancion cancionActual = playlistActual.get(nombresCanciones.get(indiceCancionActual));
        return "Reproduciendo: " + cancionActual.getNombre() + " - " +
               cancionActual.getAutor() + " (" + cancionActual.getDuracion() + " segundos, " +
               cancionActual.getGenero() + ")";
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
