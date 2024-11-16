import java.util.Scanner;
import java.util.Date;

public class Interfaz {
    private static Gestionador gestionador = new Gestionador();
    private static Scanner scanner = new Scanner(System.in);
    

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            if (!gestionador.isEncendido() && opcion != 1 && opcion != 8) {
                System.out.println("El radio está apagado. Por favor, encienda el radio primero.");
                continue;
            }
            switch (opcion) {
                case 1:
                    gestionador.encender();
                    break;
                case 2:
                    gestionador.apagar();
                    break;
                case 3:
                    gestionador.subirVolumen();
                    break;
                case 4:
                    gestionador.bajarVolumen();
                    break;
                case 5:
                    cambiarModoRadio();
                    break;
                case 6:
                    seleccionarReproduccion();
                    break;
                case 7:
                    gestionTelefono();
                    break;
                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
                    break;
            }
        } while (opcion != 8);
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú Principal - Radio del Vehículo");
        if (gestionador.isEncendido()) {
            System.out.println("2. Apagar Radio");
            System.out.println("3. Subir Volumen");
            System.out.println("4. Bajar Volumen");
            System.out.println("5. Modo Radio");
            System.out.println("6. Modo Reproducción");
            System.out.println("7. Modo Teléfono");
            System.out.println("8. Planificar Viajes");
        } else {
            System.out.println("1. Encender Radio");
        }
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void cambiarModoRadio() {
        System.out.println("Cambiando al modo radio...");
        gestionador.cambiarAMFM();
    }

    private static void seleccionarReproduccion() {
        System.out.println("Entrando al modo de reproducción...");
        System.out.print("Ingrese el nombre de la lista de reproducción: ");
        String lista = scanner.nextLine();
        gestionador.seleccionarListaReproduccion(lista);
    }

    private static void gestionTelefono() {
        boolean cambio=true;
        System.out.println("Entrando al modo teléfono...");
        if (!gestionador.isTelefonoConectado()) {
            System.out.print("¿Desea conectar un teléfono? (s/n): ");
            String respuesta = scanner.nextLine();
            if ("s".equalsIgnoreCase(respuesta)) {
                System.out.print("Ingrese el dispositivo a conectar: ");
                String dispositivo = scanner.nextLine();
                gestionador.conectarTelefono(dispositivo);
            }
        } else {
            System.out.print("Ingrese la acción (desconectar, mostrar contactos, llamar, speaker): ");
            String accion = scanner.nextLine();
            switch (accion) {
                case "desconectar":
                    gestionador.desconectarTelefono();
                    break;
                case "mostrar contactos":
                    gestionador.mostrarContactos();
                    break;
                case "llamar":
                    System.out.print("Ingrese el nombre del contacto: ");
                    String nombre = scanner.nextLine();
                    gestionador.llamarContacto(nombre);
                    int conlgar=menu8();
                    while (conlgar==2) {
                        gestionador.finalizarLlamada();
                        conlgar=menu8();
                    }
                    break;
                case "speaker":
                if (cambio) {
                    System.out.println("Se camnbio a auriculares");
                    gestionador.cambiar(cambio);
                }else{
                    System.out.println("Se cambio a Speakers");
                    gestionador.cambiar(cambio);
                }
                    break;
                default:
                    System.out.println("Acción no reconocida.");
                    break;
            }
        }
    }

    public static int menu8(){
        //Se inicializan las variables
        //Se crean los objetos

        Scanner teclado=new Scanner(System.in);
        String eleccionUsuarioS="";
        int eleccionUsuarioi=0;
        boolean verificador=false;
        

        while (verificador==false) {
            System.out.println("\nMenu\n¿Desea colgar la llamada?\n1. Si\n2. No");
            eleccionUsuarioS=teclado.nextLine();

            try { 
                eleccionUsuarioi=Integer.parseInt(eleccionUsuarioS);
                if(eleccionUsuarioi<1 ||eleccionUsuarioi>2){
                    System.out.println("Ingrese una de las opciones del menu");
                }else
                verificador=true;
                
            } catch (Exception e) {
                System.out.println("Ingrese un numero entero");
            } 
        }
        return eleccionUsuarioi;

    }

    private static void planificarViajes() {
        System.out.println("Planificando un viaje...");
        System.out.print("Ingrese lugar de inicio del viaje: ");
        String lugarInicio = scanner.nextLine();
        System.out.print("Ingrese lugar de destino del viaje: ");
        String lugarFinal = scanner.nextLine();
        gestionador.planificarViajes(new Date(), new Date(), lugarInicio, lugarFinal);
    }
}
