import java.util.ArrayList;
import java.util.List;

public class Cluedo {
    public static void main(String[] args) {
        
    
    String[] personajesArray = { "Amapola", "Celeste", "Prado", "Mora", "Rubio", "Blanco" }; // 6 elementos
    String[] armasArray = { "bate", "pistola", "candelabro", "cuchillo", "cuerda", "hacha", "pesa", "veneno", "trofeo" }; // 9 elementos
    String[] habitacionesArray = { "casa de invitados", "teatro", "spa", "observatorio", "comedor", "terraza", "salon","cocina", "vestibulo" }; // 9 elementos

    Jugador jugador;
    List<Jugador> estadosJugador = new ArrayList<Jugador>();

    // inicio de la aplicación.
    System.out.println("Bienvenido a CLUEDO");
    System.out.println("este programa se encarga de barajar las cartas de forma aleatoria para jugar al Cluedo.");
    // mostrar personajes, armas y habitaciones disponibles.
    System.out.println("Personajes Disponibles:");
    for (int i = 0; i < personajesArray.length; i++) {
        System.out.print(personajesArray[i]+" // ");
    }
    System.out.println("\nArmas Disponibles:");
    for (int i = 0; i < armasArray.length; i++) {
        System.out.print(armasArray[i]+" // ");
    }
    System.out.println("\nHabitaciones Disponibles:");
    for (int i = 0; i < habitacionesArray.length; i++) {
        System.out.print(habitacionesArray[i]+" // ");
    }
    
        /* 
    for (int i = 0; i < 5; i++) {
        jugador = new Jugador();
        jugador.setArma(armasArray[i]);
        jugador.setNombre(personajesArray[i]);
        jugador.setHabitacion(habitacionesArray[i]);
        jugador.setHoraPartida();

        estadosJugador.add(jugador);    
    }
    for (int i = 0; i < estadosJugador.size(); i++) {
        System.out.println(estadosJugador.get(i));
    }
    System.out.println(armasArray.length);
    armasArray = actualizarArray(armasArray, 3);
    System.out.println(armasArray.length);
    for (int i = 0; i < armasArray.length; i++) {
        System.out.println(armasArray[i]);
    }
    */



    } // fin main

    public static String[] actualizarArray(String[] arrayAntiguo, int num){
        String[] arrayNuevo = new String[arrayAntiguo.length+num];
        System.arraycopy(arrayAntiguo, 0, arrayNuevo, 0, arrayAntiguo.length);
        return arrayNuevo;
    }


} // cluedo
