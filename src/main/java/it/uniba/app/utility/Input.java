package it.uniba.app.utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * La classe gestisce l'input da parte dell'utente.
 * Permette di usare il metodo {@code get()} per ottenre una stringa.
 * Gestisce in maniera automatica l'uso del charset.
 */
public final class Input {
    public static final String CHARSET_NAME = "UTF-8";

    private Input() { }

    /**
     * Permette di ottenere una stringa in input da parte dell'utente.
     * @return stringa presa in input da tastiera.
     */
    public static String get() throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in, CHARSET_NAME));
        return buffer.readLine();
    }

    /**
     * TODO javadoc.
     * @param path
     * @return
     */
    public static Properties getShipProperties(final String path) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/it/uniba/app"
                + "/battleship/ship/" + path + ".properties");
            properties.load(fileInputStream);
        } catch (IOException err) {
        }
        return properties;
    }
}
