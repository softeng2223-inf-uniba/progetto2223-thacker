package it.uniba.app;

import it.uniba.app.battleship.entity.Game;
import it.uniba.app.commandline.controller.CommandHandler;
import it.uniba.app.battleship.controller.ExitController;
import it.uniba.app.utility.commands.FlagHandler;
import it.uniba.app.battleship.Game;

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

        if (args.length > 0) {
            FlagHandler.execute(args);
        }

        Game game = new Game();
        System.out.println("Digita un comando");
        while (!ExitController.isExitRequested()) {
            CommandHandler.execute(game);
        }
    }
}
