package it.uniba.app.new_design.battleship.controller;



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

}
