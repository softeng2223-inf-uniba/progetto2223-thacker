package it.uniba.app.commandline;

import it.uniba.app.battleship.controller.GameController;
import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.entity.Ship;
import it.uniba.app.battleship.exception.SessionNotStartedException;

/**
 * {@code <<Control>>}<hr>
 * Fornisce servizi per presentare una griglia in linea di comando.
 */
public final class ShowGridController {
    private static final String LETTER_WHITE_SPACE = "       ";
    private static final String WHITE_SPACE = "    ";
    private static final String ROW_SPACE = "\n\n";
    private static final int DEFAULT_NUMBER_OF_ROW = 9;
    private static final String[] ALPH = new String[] {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private ShowGridController() { }

    /**
     * Restituisce un oggetto di tipo {@code String} che contiene
     * la struttura della mappa da stampare a video.
     * @param game sessione di gioco
     * @return mappa dei colpi in formato stringa
     */
    static String genHitMap(final Game game) throws SessionNotStartedException {
        Grid gameGrid = GameController.getSessionGrid(game);
        String str = LETTER_WHITE_SPACE;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < Grid.getSize(); i++) {
            b.append(ALPH[i]);
            b.append(WHITE_SPACE);
        }
        b.append(ROW_SPACE);
        for (int row = 0; row < Grid.getSize(); row++) {
            if (row < DEFAULT_NUMBER_OF_ROW) {
                b.append(" ");
            }
            b.append((row + 1));
            b.append(":");
            b.append(WHITE_SPACE);
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coord = new Coordinate(row, col);
                if (game.isAlreadyAttempted(coord)) {
                    Ship ship = gameGrid.get(coord);
                    if (ship != null) {
                        if (ship.isSunk()) {
                            b.append(Color.get(ship.getColor()));
                            b.append(ship);
                            b.append(Color.getReset());
                            b.append(WHITE_SPACE);
                        } else {
                            b.append(ship);
                            b.append(WHITE_SPACE);
                        }
                    } else {
                            b.append(Color.get("blue"));
                            b.append(Grid.getWaterSymbol());
                            b.append(Color.getReset());
                            b.append(WHITE_SPACE);
                    }
                } else {
                    b.append(Grid.getDotSymbol());
                    b.append(WHITE_SPACE);
                }
            }
            b.append(ROW_SPACE);
        }
        str += b.toString();
        return str;
    }

    /**
     * Restituisce una oggetto di tipi {@code String} che
     * contiene la mappa delle navi che viene mostrata col
     * comando {@code /svelagriglia}.
     * @param grid oggetto che contiene la mappa delle navi.
     * @return stringa contenente tutta la mappa delle navi.
    */
    static String genShipMap(final Grid grid) {
        String str = LETTER_WHITE_SPACE;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < Grid.getSize(); i++) {
            b.append(ALPH[i]);
            b.append(WHITE_SPACE);
        }
        b.append(ROW_SPACE);
        for (int row = 0; row < Grid.getSize(); row++) {
            if (row < DEFAULT_NUMBER_OF_ROW) {
                b.append(" ");
            }
            b.append((row + 1));
            b.append(":");
            b.append(WHITE_SPACE);
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coords = new Coordinate(row, col);
                Ship ship = grid.get(coords);
                if (ship == null) {
                    b.append(Color.get("blue"));
                    b.append(Grid.getWaterSymbol());
                    b.append(Color.getReset());
                    b.append(WHITE_SPACE);
                } else {
                    b.append(Color.get(ship.getColor()));
                    b.append(ship);
                    b.append(Color.getReset());
                    b.append(WHITE_SPACE);
                }
            }
            b.append(ROW_SPACE);
        }
        str += b.toString();
        return str;
    }
}
