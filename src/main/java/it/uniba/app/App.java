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

        Boolean exit = false;

        System.out.println("Digitare un comando");
        while (!exit) {
            String command = CommandHandler.readCommand();

            if (command.compareTo("/q") != 0) {
                CommandHandler.executeCommand(command);
            } else {
                exit = true;
            }

        }
    }
}
