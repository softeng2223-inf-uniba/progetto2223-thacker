package it.uniba.app.new_design.battleship.controller;

import java.util.LinkedList;
import java.util.Random;

import it.uniba.app.new_design.battleship.entity.Coordinate;
import it.uniba.app.new_design.battleship.entity.Grid;
import it.uniba.app.new_design.battleship.entity.Ship;


/**
 * Javadoc momentaneo.
 */
public final class GameFiller {
    private static final Random RAND = new Random();
    private static final int POSSIBLE_DIRECTIONS = 2;
    private static final int VERTICAL = 0;

    private GameFiller() { }

    public static void randomlyFill(final LinkedList<Ship> ships, final Grid grid) {
        for (Ship ship : ships) {
            randomlyInsertShip(ship, grid);
        }
    }

    private static int getRandomDirection() {
        return RAND.nextInt(POSSIBLE_DIRECTIONS);
    }

    private static Coordinate getRandomCoordinates() {
        int first = RAND.nextInt(Grid.getSize());
        int sec   = RAND.nextInt(Grid.getSize());
        return new Coordinate(first, sec);
    }
}
