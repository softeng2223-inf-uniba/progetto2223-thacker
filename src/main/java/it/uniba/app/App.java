package it.uniba.app;

import it.uniba.app.utility.CommandHandler;

/**
 * Main class of the application.
 */
public final class App {

    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public String getGreeting() {
        return "Hello World!!!";
    }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        System.out.println(new App().getGreeting());

        CommandHandler.executeFlags(args);
        String command = "";
        System.out.println("Digita un comando");
        while (true) {
            command = CommandHandler.readCommand();
            CommandHandler.executeCommand(command);
        }
    }
}
