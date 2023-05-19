package it.uniba.app.utility;

public class ExitRequest {
    private static boolean requestedExit = false;

    private ExitRequest() { };

    public static void send(){
        requestedExit = true;
    }
    
    public static boolean status(){
        return requestedExit;
    }
}
