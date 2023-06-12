package it.uniba.app;

import it.uniba.app.battleship.entity.Game;
import it.uniba.app.commandline.Output;
import it.uniba.app.commandline.controller.CommandHandler;
import it.uniba.app.commandline.controller.FlagHandler;
import it.uniba.app.battleship.controller.ExitController;

/**
 * Main class of the application.
 */
public final class App {
    private App() { }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {

        FlagHandler.execute(args);

        Game game = new Game();
        Output.printEnterCommand();
        while (!ExitController.getInstance().isExitRequested()) {
            CommandHandler.handleCommand(game);
        }
    }
}
