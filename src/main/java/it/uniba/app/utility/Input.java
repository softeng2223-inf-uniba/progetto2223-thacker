package it.uniba.app.utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader; 

public class Input {
    public static final String CHARSET_NAME = "UTF-8";
    
    private Input() { }
    
    public static String get() throws IOException { 
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in, CHARSET_NAME)); 
        return buffer.readLine(); 
    } 
}
