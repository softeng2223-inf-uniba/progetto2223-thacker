package it.uniba.app.utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * La classe gestisce l'input da parte dell'utente.
 * Permette di usare il metodo {@code get()} per ottenre una stringa.
 * Gestisce in maniera automatica l'uso del charset.
 */
public final class Input {
    public static final String CHARSET_NAME = "UTF-8";

    private Input() { }

    /**
     * Javadoc momenntaneo.
     */
    public static String get() throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in, CHARSET_NAME));
        return buffer.readLine();
    }
}
