package it.uniba.app.game;

import java.util.Random;

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
}
