import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

public class ComprobarFichero {
    public static void main(String[] args) throws IOException {
        String cabecera = null;
        File fichero = new File("partida.dat");
        ObjectInputStream lector= null;
        Jugador jugador;

        try {
            lector = new ObjectInputStream(new FileInputStream(fichero));
            Object objeto;
            while (true){
                objeto = lector.readObject();
                if (objeto.getClass()==String.class){
                    System.out.print((String) objeto+" // ");
                    System.out.println(objeto.getClass());
                } else {
                jugador=(Jugador) objeto;               
                System.out.print(jugador+ " // ");
                System.out.println(objeto.getClass());
                }
            }
            // a partir de aquí no se ejecuta código, en el try.
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (EOFException e) {

        } catch (StreamCorruptedException e){
            System.out.println(e.getMessage());
            System.out.println("excepción StreamCorrupted");
        }
        finally {
            if (lector!=null) lector.close();
        }

    }
}
