package it.uniba.app.utility.help;
import java.io.*;
import java.nio.file.*;


public class Help {
    static BufferedReader reader;
    static BufferedReader readertesto;

    static String testo = "";
    static String titolo = "";

    public static void Stampatitolo() throws IOException,InterruptedException{
        try {
            reader = new BufferedReader(new FileReader("src/main/java/it/uniba/app/utility/help/files/regole_base.txt"));
            while (reader.read() != -1) {
                titolo = reader.readLine();
                Thread.sleep(100);
                titolo = titolo + "\r";
                System.out.println(titolo);
                
            }
            titolo = titolo + "\r";
        }catch (IOException | InterruptedException e){
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static void Stampatesto() throws FileNotFoundException,InterruptedException{
        try {
            readertesto = new BufferedReader(new FileReader("src/main/java/it/uniba/app/utility/help/files/testo_base.txt"));
            while(readertesto.read() != -1) {
                testo = readertesto.readLine();
                for (char c : testo.toCharArray()) {
                    System.out.print(c);
                    Thread.sleep(5);
                }
            }
        }catch (InterruptedException p){
            System.out.println(p + "Thread interrotto");
        }catch (IOException e ){
            System.out.println(e + "Errore di Input/Ouput");
        }

    }
}
