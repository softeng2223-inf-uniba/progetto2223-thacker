package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import it.uniba.app.utility.Color;

/**
 * Javadoc momentaneo.
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

            switch (result) {
                case 1:
                    System.out.println(
                        Color.get("red")
                        + "\nCOLPITO E AFFONDATO\n"
                        + Color.getReset()
                    );
                    break;
                case 0:
                    System.out.println(
                        Color.get("blue")
                        + "\nCOLPITO\n"
                        + Color.getReset()
                    );
                    break;
                default:
                    System.out.println(
                        Color.get("yellow")
                        + "\nACQUA\n"
                        + Color.getReset()
                    );
            }
        }
}
