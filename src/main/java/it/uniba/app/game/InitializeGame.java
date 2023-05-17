package it.uniba.app.game;

import java.util.Random;
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
    private void randomlyFillMap() {
        // deve chiamare un metodo per inserire ogni nave
        // singolarmente sulla mappa.
        // Per farlo serve un generatore di numeri casuali
        // per direzione nave e coordinate
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
