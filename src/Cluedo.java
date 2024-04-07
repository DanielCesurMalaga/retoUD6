import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cluedo {
    // atributos de clase
    static String[] personajesArray = { "Amapola", "Celeste", "Prado", "Mora", "Rubio", "Blanco" }; // 6 elementos
    static String[] armasArray = { "bate", "pistola", "candelabro", "cuchillo", "cuerda", "hacha", "pesa", "veneno",
            "trofeo" }; // 9 elementos
    static String[] habitacionesArray = { "casa de invitados", "teatro", "spa", "observatorio", "comedor", "terraza",
            "salon", "cocina", "vestibulo" }; // 9 elementos
    static List<Jugador> estadosJugador = new ArrayList<Jugador>();
    static Jugador jugador;
    static final String CLAVE = "123";

    public static void main(String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);
        String clave = null;
        int resp = -1;
        int num = -1;
        String introDato = null;
        char continuar;
        do {
            // inicio de la aplicación.
            System.out.println("Bienvenido a CLUEDO");
            System.out
                    .println("este programa se encarga de barajar las cartas de forma aleatoria para jugar al Cluedo.");
            // mostrar personajes, armas y habitaciones disponibles.
            System.out.println("Personajes Disponibles:");
            for (int i = 0; i < personajesArray.length; i++) {
                System.out.print(personajesArray[i] + " // ");
            }
            System.out.println("\nArmas Disponibles:");
            for (int i = 0; i < armasArray.length; i++) {
                System.out.print(armasArray[i] + " // ");
            }
            System.out.println("\nHabitaciones Disponibles:");
            for (int i = 0; i < habitacionesArray.length; i++) {
                System.out.print(habitacionesArray[i] + " // ");
            }
            // preguntar si se quiere añadir algo más (a los arrays)

            do {
                System.out.println("\n¿Quieres añadir algo más?");
                System.out.println("Menú para añadir más información");
                System.out.println("1. Personaje");
                System.out.println("2. Arma");
                System.out.println("3. Habitación");
                System.out.println("4. Salir");

                resp = teclado.nextInt();
                switch (resp) {
                    case 1:
                        System.out.println("\n¿Cuántos personajes quieres añadir: ?");
                        num = teclado.nextInt();
                        personajesArray = actualizarArray(personajesArray, num);
                        teclado.nextLine(); // limpio buffer de teclado.
                        for (int i = personajesArray.length - num; i < personajesArray.length; i++) {
                            System.out.println("Introduce nombre del personaje: ");
                            introDato = teclado.nextLine();
                            personajesArray[i] = introDato;
                        }
                        // visualizo todos los datos del nuevo array
                        System.out.println("Array de personajes actualizado: " + Arrays.toString(personajesArray));
                        break;

                    case 2:
                        System.out.println("\n¿Cuántas armas quieres añadir: ?");
                        num = teclado.nextInt();
                        armasArray = actualizarArray(armasArray, num);
                        teclado.nextLine();
                        for (int i = armasArray.length - num; i < armasArray.length; i++) {
                            System.out.println("Introduce nombre del arma: ");
                            introDato = teclado.nextLine();
                            armasArray[i] = introDato;
                        }
                        // visualizo todos los datos del nuevo array
                        System.out.println("Array de Armas actualizado: " + Arrays.toString(armasArray));
                        break;
                    case 3:
                        System.out.println("\n¿Cuántas habitaciones quieres añadir: ?");
                        num = teclado.nextInt();
                        habitacionesArray = actualizarArray(habitacionesArray, num);
                        teclado.nextLine();
                        for (int i = habitacionesArray.length - num; i < habitacionesArray.length; i++) {
                            System.out.println("Introduce nombre de la habitación: ");
                            introDato = teclado.nextLine();
                            habitacionesArray[i] = introDato;
                        }
                        // visualizo todos los datos del nuevo array
                        System.out.println("Array de Habitaciones actualizado: " + Arrays.toString(habitacionesArray));
                        break;
                    case 4:
                        System.out.println("¡Genial, sigamos...!");
                        teclado.nextLine();
                        break;

                    default:
                        System.out.println("Introduce opción correcta. Pringao!");
                        break;
                }

            } while (resp != 4);
            System.out.println("..Barajando Cartas...");

            if (barajar()) {

                System.out.println(
                        "Si introduces la clave correcta te puedo mostrar quien ha sido el culpable, como y donde...");
                clave = teclado.nextLine();
                if (clave.equals(CLAVE)) {
                    System.out.println(estadosJugador.get(estadosJugador.size() - 1));
                } else {
                    System.out.println("No tienes derecho a ver el culpable, como ni donde");
                }

            } else {
                System.out.println("Ha habido un error al barajar.");
            }

            System.out.println("¿Quieres crear una nueva combinación para el juego?");
            continuar = teclado.nextLine().toLowerCase().charAt(0);
        } while (continuar == 's');

        // al terminar guardo todas los estados del jugador en un fichero OJO
        // precedido por la fecha del momento del cierre.
        // OJO el fichero no se sobreescribe.

        File fichero = new File("partida.dat");
        ObjectOutputStream escritor= null;

        try {
            escritor = new ObjectOutputStream(new FileOutputStream(fichero,true));
            escritor.writeObject("Cabecera - Fecha: "+ LocalDate.now());
            for (int i = 0; i<estadosJugador.size();i++) {
                escritor.writeObject(estadosJugador.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (escritor!=null) escritor.close();
        }

        /*
         * 4. Se informa al usuario de que se va a producir la baraja de las cartas,
         * para ello se llama a un método que se encarga de barajar (ver aclaración
         * sobre barajar cartas)
         * y almacenar el personaje, arma, habitación y hora como objeto de la lista
         * dinámica que hemos
         * creado anteriormente para este fin. Este método devolverá true o false en
         * caso de que se haya
         * podido o no hacer dicha operación (controla excepciones).
         */

        /*
         * for (int i = 0; i < 5; i++) {
         * jugador = new Jugador();
         * jugador.setArma(armasArray[i]);
         * jugador.setNombre(personajesArray[i]);
         * jugador.setHabitacion(habitacionesArray[i]);
         * jugador.setHoraPartida();
         * 
         * estadosJugador.add(jugador);
         * }
         * for (int i = 0; i < estadosJugador.size(); i++) {
         * System.out.println(estadosJugador.get(i));
         * }
         * System.out.println(armasArray.length);
         * armasArray = actualizarArray(armasArray, 3);
         * System.out.println(armasArray.length);
         * for (int i = 0; i < armasArray.length; i++) {
         * System.out.println(armasArray[i]);
         * }
         * 
         * System.out.println(Arrays.toString(personajesArray));
         * System.out.println(Arrays.toString(armasArray));
         * System.out.println(Arrays.toString(habitacionesArray));
         * 
         * for (int i = 0; i < 5; i++) {
         * barajar();
         * }
         * for (int i = 0; i < 5; i++) {
         * System.out.println(estadosJugador.get(i));
         * }
         */
        teclado.close();
    } // fin main

    public static String[] actualizarArray(String[] arrayAntiguo, int num) {
        String[] arrayNuevo = new String[arrayAntiguo.length + num];
        System.arraycopy(arrayAntiguo, 0, arrayNuevo, 0, arrayAntiguo.length);
        return arrayNuevo;
    }

    public static boolean barajar() {
        /*
         * un método que se encarga de barajar (ver aclaración sobre barajar cartas)
         * y almacenar el personaje, arma, habitación y hora como objeto de la lista
         * dinámica que hemos
         * creado anteriormente para este fin. Este método devolverá true o false en
         * caso de que se haya
         * podido o no hacer dicha operación (controla excepciones).
         */

        // generar tres números aleatorios, uno por cada array y seleccionar el elem de
        // la posición dada.

        // Generamos las posiciones aleatorias.
        Random aleatorio = new Random();
        int posArma, posPersonaje, posHabitacion;
        posArma = aleatorio.nextInt(armasArray.length);
        posPersonaje = aleatorio.nextInt(personajesArray.length);
        posHabitacion = aleatorio.nextInt(habitacionesArray.length);

        // instanciamos o inicializamos de nuevo al jugador e introducimos los valores
        // de las posiciones dadas
        try {
            jugador = new Jugador();
            jugador.setArma(armasArray[posArma]);
            jugador.setHabitacion(habitacionesArray[posHabitacion]);
            jugador.setNombre(personajesArray[posPersonaje]);
            jugador.setHoraPartida();

            // introducimos en la lista de estados el jugador generado y ya relleno.
            estadosJugador.add(jugador);

            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

} // cluedo
