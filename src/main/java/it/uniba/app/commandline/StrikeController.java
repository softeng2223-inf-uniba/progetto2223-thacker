package it.uniba.app.commandline;

import it.uniba.app.battleship.controller.GameController;
import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.battleship.exception.SessionNotStartedException;

/**
 * {@code <<Control>>}<hr>
 * La classe {@code StrikeController} fornisce i servizi
 * per gestire il comando che lancia un colpo.
 */
public final class StrikeController {
    private static final int CHAR_CONVERT = 97;
    private static final int NUM_START_INDEX = 2;

    private StrikeController() { }

    private static Coordinate convert(final String command) {
        char letter = command.charAt(0);
        int col = letter - CHAR_CONVERT;

        String sub = command.substring(NUM_START_INDEX);
        int row = Integer.parseInt(sub) - 1;

        return new Coordinate(row, col);
    }

    /**
     * Permette di colpire una cella della mappa. Se si tenta di effettuare
     * un colpo su una cella che è già stata colpita in precedenza, viene lanciata
     * una eccezione di tipo `CellAlreadyMarkedException`,.
     * Se le coordinate scelte sono al di fuori dei confini della mappa, viene
     * lanciata una eccezione di tipo `OutOfMapException`.
     * @param command contiene la coordinata in formato stringa.
     * @param game sessione di gioco
     *
    */
    public static void strike(final Game game, final String command)
        throws SessionNotStartedException, CellAlreadyMarkedException, OutOfMapException {
            Coordinate coord = convert(command);

            int result = GameController.strike(game, coord);
            Output.clearScreen();
            Output.print("Lancio colpo in " + command + "\n" + "Esito: ");

            switch (result) {
                case 1  -> Output.printShipSunken();
                case 0  -> Output.printHitShip();
                default -> Output.printHitWater();
            }
        }
}
