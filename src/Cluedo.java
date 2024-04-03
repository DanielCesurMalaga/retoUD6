import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cluedo {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        String[] personajesArray = { "Amapola", "Celeste", "Prado", "Mora", "Rubio", "Blanco" }; // 6 elementos
        String[] armasArray = { "bate", "pistola", "candelabro", "cuchillo", "cuerda", "hacha", "pesa", "veneno",
                "trofeo" }; // 9 elementos
        String[] habitacionesArray = { "casa de invitados", "teatro", "spa", "observatorio", "comedor", "terraza",
                "salon", "cocina", "vestibulo" }; // 9 elementos

        Jugador jugador;
        List<Jugador> estadosJugador = new ArrayList<Jugador>();

        // inicio de la aplicación.
        System.out.println("Bienvenido a CLUEDO");
        System.out.println("este programa se encarga de barajar las cartas de forma aleatoria para jugar al Cluedo.");
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
        int resp = -1;
        int num = -1;
        String introDato = null;
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
                    for (int i = personajesArray.length-num; i < personajesArray.length; i++) {
                        System.out.println("Introduce nombre del personaje: ");
                        introDato = teclado.nextLine();
                        personajesArray[i]= introDato;                   
                    }
                    // visualizo todos los datos del nuevo array
                    System.out.println("Array de personajes actualizado: "+Arrays.toString(personajesArray));
                    break;

                case 2:
                System.out.println("\n¿Cuántas armas quieres añadir: ?");
                num = teclado.nextInt();
                armasArray = actualizarArray(armasArray, num);
                teclado.nextLine();
                for (int i = armasArray.length-num; i < armasArray.length; i++) {
                    System.out.println("Introduce nombre del arma: ");
                    introDato = teclado.nextLine();
                    armasArray[i]= introDato;                   
                }
                // visualizo todos los datos del nuevo array
                System.out.println("Array de Armas actualizado: "+Arrays.toString(armasArray));
                    break;
                case 3:
                System.out.println("\n¿Cuántas habitaciones quieres añadir: ?");
                num = teclado.nextInt();
                habitacionesArray = actualizarArray(habitacionesArray, num);
                teclado.nextLine();
                for (int i = habitacionesArray.length-num; i < habitacionesArray.length; i++) {
                    System.out.println("Introduce nombre de la habitación: ");
                    introDato = teclado.nextLine();
                    habitacionesArray[i]= introDato;                   
                }
                // visualizo todos los datos del nuevo array
                System.out.println("Array de Habitaciones actualizado: "+Arrays.toString(habitacionesArray));
                    break;
                case 4:
                    System.out.println("¡Genial, sigamos...!");
                    break;

                default:
                System.out.println("Introduce opción correcta. Pringao!");
                    break;
            }

        } while (resp != 4);
        System.out.println("..Barajando Cartas...");

        /*4. Se informa al usuario de que se va a producir la baraja de las cartas, 
        para ello se llama a un método que se encarga de barajar (ver aclaración sobre barajar cartas) 
        y almacenar el personaje, arma, habitación y hora como objeto de la lista dinámica que hemos 
        creado anteriormente para este fin. Este método devolverá true o false en caso de que se haya 
        podido o no hacer dicha operación (controla excepciones). */
        
        
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
         
        System.out.println(Arrays.toString(personajesArray));
        System.out.println(Arrays.toString(armasArray));
        System.out.println(Arrays.toString(habitacionesArray));
        */


        teclado.close();
    } // fin main

    public static String[] actualizarArray(String[] arrayAntiguo, int num) {
        String[] arrayNuevo = new String[arrayAntiguo.length + num];
        System.arraycopy(arrayAntiguo, 0, arrayNuevo, 0, arrayAntiguo.length);
        return arrayNuevo;
    }

    public static boolean barajar(String[] armasArray, String[] personajeArray){
        /*un método que se encarga de barajar (ver aclaración sobre barajar cartas) 
        y almacenar el personaje, arma, habitación y hora como objeto de la lista dinámica que hemos 
        creado anteriormente para este fin. Este método devolverá true o false en caso de que se haya 
        podido o no hacer dicha operación (controla excepciones).   */
        // generar tres números aleatorios, uno por cada array y seleccionar el elem de la posición dada.
        Random aleatorio = new Random();
        int posArma, posPersonaje, posHabitacion;
        posArma = aleatorio.nextInt(armasArray.length);
        return true;
    }

} // cluedo
