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
    private static final String SYMBOL_INPUT_PROMPT = "> ";

    private Input() { }

    /**
     * Permette di ottenere una stringa in input da parte dell'utente.
     * @return stringa presa in input da tastiera.
     */
    public static String get() throws IOException {
        System.out.print(SYMBOL_INPUT_PROMPT);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in, CHARSET_NAME));
        return buffer.readLine();
    }

    /**
     * Il metodo richiede in input il nome della nave di cui si vuole leggere il file di proprietà.
     * Il metodo restituisce un oggetto {@code Properties} che contiene le proprietà della nave.
     * @param shipName nome della nave di cui si vuole leggere il file di proprietà.
     * @return {@code Properties} che contiene le proprietà della nave.
     */
    public static Properties getShipProperties(final String shipName) {
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/main/java/it/uniba/app"
                + "/battleship/ship/" + shipName + ".properties");
            properties.load(file);
            file.close();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        return properties;
    }

    /**
     * Restituisce il nome di una nave, dopo aver effettuato il
     * parsing del file di proprietà della nave.
     * La funzione restituisce la stringa {@code "error"} se il parsing del nome
     * non è andato a buon fine.
     * @param properties file di proprietà della nave.
     * @return {@code nome} della nave se il parsing avviene con successo,
     * {@code error} altrimenti.
    */
    public static String parseShipName(final Properties properties) {
        String nameString = properties.getProperty("name");
        return nameString != null && !nameString.isEmpty() ? nameString : "error";
    }

   /**
     * Restituisce il colore di una nave, dopo aver effettuato il
     * parsing del file di proprietà della nave.
     * La funzione restituisce la stringa {@code "error"} se il parsing del colore
     * non è andato a buon fine.
     * @param properties file di proprietà della nave.
     * @return {@code color} della nave se il parsing avviene con successo,
     * {@code error} altrimenti.
    */
    public static String parseShipColor(final Properties properties) {
        String nameString = properties.getProperty("color");
        return nameString != null && !nameString.isEmpty() ? nameString : "error";
    }

   /**
    * Restituisce la dimensione di una nave, dopo aver effettuato il
    * parsing del file di proprietà della nave.
    * Il valore restituito è {@code 0} quando avviene un errore nel parsing
    * e se il valore della dimensione trovato nel file properties è negativo.
    * @param properties file di proprietà della nave.
    * @return {@code size} se il parsing avviene con successo e il valore trovato
    * è maggiore o uguale a 0, {@code 0} altrimenti.
    */
    public static int parseShipSize(final Properties properties) {
        String str = properties.getProperty("size");
        if (str != null && !str.isEmpty()) {
            int val = Integer.parseInt(str);
            if (val >= 0) {
                return val;
            }
        }
        return 0;
    }

    /**
     * Restituisce il numero di istanze di una nave, dopo aver effettuato il
     * parsing del file di proprietà della nave.
     * Il valore restituito è {@code 0} quando avviene un errore nel parsing
     * e se il valore di istanze trovato nel file properties è negativo.
     * @param properties file di proprietà della nave.
     * @return {@code instances} se il parsing avviene con successo e il valore trovato
     * è maggiore o uguale a 0, {@code 0} altrimenti.
    */
    public static int parseShipInstances(final Properties properties) {
        String str = properties.getProperty("instances");
        if (str != null && !str.isEmpty()) {
            int val = Integer.parseInt(str);
            if (val >= 0) {
                return val;
            }
        }
        return 0;
    }
}
