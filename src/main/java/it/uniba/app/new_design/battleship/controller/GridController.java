package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.new_design.battleship.entity.Coordinate;
import it.uniba.app.new_design.battleship.entity.Grid;
import it.uniba.app.new_design.battleship.entity.Ship;
import it.uniba.app.utility.Color;

/**
 * Classe che contiene i metodi utili alla visualizzazione della
 * mappa delle navi e della mappa dei colpi.
 */
public class GridController {

    private GridController() { }

    /**
     * Restituisce una oggetto di tipi {@code String} che
     * contiene la mappa delle navi che viene mostrata col
     * comando {@code /svelagriglia}.
     * @param grid oggetto che contiene la mappa delle navi.
     * @return stringa contenente tutta la mappa delle navi.
    */
    public static String genShipMap(final Grid grid) {
        String str = "      A    B    C    D    E    F    G    H    I    J\n\n";
        for (int row = 0; row < Grid.getSize(); row++) {
            str += row + ":    ";
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coords = new Coordinate(row, col);
                Ship item = grid.get(coords);
                if (item == null) {
                    str += Grid.getWaterSymbol() + "    ";
                } else {
                    str += item + "    ";
                }
            }
            str += "\n\n";
        }
        return str;
    }

    /**
     * Restituisce una oggetto di tipi {@code String} che
     * contiene la mappa dei colpi che viene mostrata col
     * quando comincia il gioco. Rappresenta la mappa del
     * giocatore dove vengono segnati i colpi.
     * @param grid oggetto che contiene la mappa delle navi.
     * @return stringa contenente tutta la mappa delle navi.
     */
    public static String genHitsMap(final Grid grid) {
        String str = "      A    B    C    D    E    F    G    H    I    J\n\n";
        for (int row = 0; row < Grid.getSize(); row++){
            str += row + ":    ";
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coords = new Coordinate(row, col);
                if (grid.isCellHit(coords)) {
                    if (grid.isCellEmpty(coords)){
                        str += Color.get("blue") + Grid.getWaterSymbol()
                            + Color.getReset() + "    ";
                    } else {
                        str += Color.get("red") + Grid.getHitSymbol()
                         + Color.getReset() + "    ";
                    }
                } else {
                    str += Grid.getDotSymbol() + Grid.getHitSymbol()
                        + Color.getReset() + "    ";
                }
            }
        }
        return str;
    }
}
