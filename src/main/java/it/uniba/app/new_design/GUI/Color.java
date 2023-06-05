package it.uniba.app.new_design.GUI;
import java.util.HashMap;
import java.util.Map;
class Color{
    private static final Map<String, String> COLOR = new HashMap<>(){
        {
            put("reset", "\\u001B[0m");
        }
    };
    private Color(){}
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
