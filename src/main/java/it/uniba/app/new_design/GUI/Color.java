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
    public static String getResetColor(){
        return COLOR.get("reset");
    }
    public static String get(final String colorName){
        String str = COLOR.get(colorName);
        if(str != null){
            return str;
        } else {
            return getResetColor();
        }
    }
}
