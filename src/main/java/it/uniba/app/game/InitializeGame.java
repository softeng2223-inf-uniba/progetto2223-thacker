package it.uniba.app.game;

import java.util.Random;
import java.util.List;
import it.uniba.app.utility.Coordinate;

/**
 * JavaDoc momentaneo.
 */
public class InitializeGame {
    private Board map;

    /**
     * JavaDoc momentaneo costruttore.
     * @param gameMap mappa da inizializzare.
     */
    public InitializeGame(final Board gameMap) {
        map = gameMap;
    }

    /**
     * JavaDoc momentaneo.
     */
    private void randomlyFillMap(List<Ship> ShipsType) {
        // deve chiamare un metodo per inserire ogni nave
        // singolarmente sulla mappa.
        // Per farlo serve un generatore di numeri casuali
        // per direzione nave e coordinate
        for (Ship ship : ShipsType) {
            randomlyInsertShip(ship);
        }
    }

    private void randomlyInsertShip(Ship ship) {
        int direction = getRandomDirection();

        // deve essere fatto un controllo sulla mappa se
        // la posizione determinata dalle coordinate è disponibile
        // per il posizionamento di una nave.
        Coordinate coord = getRandomCoordinates();

        if (direction == 0) {
            for (int i = 0; i <= ship.getShipSize(); i++) {
                map.setElement(coord, ship);
                coord.setCol(coord.getCol() + 1);
            }
        } else {
            for (int i = 0; i <= ship.getShipSize(); i++) {
                map.setElement(coord, ship);
                coord.setRow(coord.getRow() + 1);
            }
        }
    }

    /**
     * JavaDoc momentaneo.
     * @return numero randomico.
     */
    private int getRandomDirection() {
        Random rand = new Random();
        return rand.nextInt(2);
    }

    /**
     * JavaDoc momentaneo.
     * @return coordinate randomiche.
     */
    private Coordinate getRandomCoordinates() {
        Random rand  = new Random();
        return new Coordinate(rand.nextInt(Board.getSize()),
            rand.nextInt(Board.getSize()));
    }
}
