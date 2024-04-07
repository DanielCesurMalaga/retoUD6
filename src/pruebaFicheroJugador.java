import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.List;

public class pruebaFicheroJugador {
    public static void main(String[] args) throws IOException {
        String nombreFichero = "partidaSoloJugadores.dat";
        ObjectInputStream lector = null;
        List<Object> listaObjetos = new ArrayList<Object>();
        try {
            FileInputStream flujoDesdeFichero = new FileInputStream(nombreFichero);
            lector = new ObjectInputStream(flujoDesdeFichero);
            while (true) {
                listaObjetos.addAll(getObject(lector));
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (lector != null)
                lector.close();
        }
        for (Object object : listaObjetos) {
            if (object instanceof String) {
                System.out.println((String) object);
            } else {
                System.out.println((Jugador) object);
            }
        }
    } // end of main

    public static List<Object> getObject(ObjectInputStream lector) throws IOException {
        List<Object> listaObjetos = new ArrayList<Object>();
        try {
            while (true) {
                listaObjetos.add(lector.readObject());
            }
            // a partir de aquí no se ejecuta código, en el try.
        } catch (StreamCorruptedException e) {
            // nothing to do
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("finalizada lectura con Excepción StreamCorrupted");
        return listaObjetos;
    }
}
