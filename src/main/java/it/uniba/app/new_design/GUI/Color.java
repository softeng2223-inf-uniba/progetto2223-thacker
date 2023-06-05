package it.uniba.app.new_design.GUI;
import java.util.HashMap;
import java.util.Map;
/**
 * Questa classe rappresenta un'utility per gestire i colori nel terminale.
 * Fornisce metodi statici per ottenere i valori dei colori e il valore di reset.
 * Utilizza i codici di escape ANSI per applicare colori al testo visualizzato nel terminale.
 */
public class Color{
    private static final Map<String, String> COLOR = new HashMap<>(){
        {
            put("reset",  "\u001B[0m");
            put("red", "\u001B[31m");
            put("purple", "\u001B[35m");
        }
    };
    private Color(){}
    /**
     * Restituisce il valore di reset del colore.
     *
     * @return il valore di reset del colore
     */
    public static String getReset(){
        return COLOR.get("reset");
    }
    /**
     * Restituisce il valore corrispondente al nome del colore specificato.
     * Se il nome del colore è presente nella mappa COLOR, restituisce il valore associato.
     * Altrimenti, restituisce il valore di reset.
     *
     * @param colorName il nome del colore da cercare
     * @return il valore corrispondente al nome del colore, o il valore di reset se non presente
     */
    public static String get(final String colorName){
        String str = COLOR.get(colorName);
        if(str != null){
            return str;
        } else {
            return getReset();
        }
    }
}
