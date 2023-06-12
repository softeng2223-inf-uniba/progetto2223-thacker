package it.uniba.app;

import it.uniba.app.battleship.entity.Game;
import it.uniba.app.commandline.controller.CommandHandler;
import it.uniba.app.commandline.controller.FlagHandler;
import it.uniba.app.battleship.controller.ExitController;
import it.uniba.app.commandline.Output;

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
        if(!FlagHandler.execute(args)) {
            Output.printStartMessage();
        }

        Game game = new Game();
        while (!ExitController.getInstance().isExitRequested()) {
            CommandHandler.handleCommand(game);
        }
    }
}
