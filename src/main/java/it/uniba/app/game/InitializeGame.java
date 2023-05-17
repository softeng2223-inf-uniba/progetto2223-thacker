package it.uniba.app.game;

import java.util.Random;
import java.util.List;
import it.uniba.app.utility.Coordinate;

/**
 * JavaDoc momentaneo.
 */
public class InitializeGame {
    /**
     * Rappresenta la direzione verticale, utilizata per effettuare
     * controlli per l'inserimento delle navi nella mappa.
     */
    private static final int VERTICAL = 0;
    /**
     * Rappresenta l'intervallo da utilizzare per generare una direzione
     * di posizionamento randomiche delle navi.
     */
    private static final int POSSIBLE_DIRECTIONS = 2;

    /**
     * Rappresenta la mappa di gioco su cui verranno posizionate
     * le navi del computer.
     */
    private Board map;

    /**
     * Costruisce un oggetto di tipo {@code InitializeMap} che viene usato per
     * inizializzare la mappa del computer con navi posizionate in modo
     * randomico in posizioni diverse in modo tale che ognuna occupi un suo
     * spazio e nessuna venga sovrapposta.
     * @param gameMap oggetto mappa di gioco utilizzata
     * per inizializzare l'attributo {@code map} della classe.
     */
    public InitializeGame(final Board gameMap) {
        map = gameMap;
    }

    /**
     * Imposta le mappe di gioco e posiziona le navi da colpire in modo randomico.
     */
    public void initGame(final List<Ship> shipsType) {
        randomlyFillMap(shipsType);
    }

    /**
     * JavaDoc momentaneo.
     */
    private void randomlyFillMap(final List<Ship> shipsType) {
        for (Ship ship : shipsType) {
            randomlyInsertShip(ship);
        }
    }

    /**
     * JavaDoc momentaneo.
     * @param ship
     */
    private void randomlyInsertShip(final Ship ship) {
        int direction = getRandomDirection();

        Coordinate coord;
        do {
            coord = getRandomCoordinates();
        } while (!isPositionAvailable(coord, direction, ship));

        if (direction == VERTICAL) {
            insertShipVertical(coord, ship);
        } else {
            insertShipHorizontal(coord, ship);
        }
    }

    /**
     * JavaDoc momentaneo.
     * @param coord
     * @param ship
     */
    private void insertShipVertical(final Coordinate coord, final Ship ship) {
        for (int i = 0; i < ship.getShipSize(); i++) {
            map.setElement(coord, ship);
            coord.setRow(coord.getRow() + 1);
        }
    }

    /**
     * JavaDoc momentaneo.
     * @param coord
     * @param ship
     */
    private void insertShipHorizontal(final Coordinate coord, final Ship ship) {
        for (int i = 0; i < ship.getShipSize(); i++) {
            map.setElement(coord, ship);
            coord.setCol(coord.getCol() + 1);
        }
    }

    /**
     * JavaDoc momentaneo.
     * @return numero randomico.
     */
    private int getRandomDirection() {
        Random rand = new Random();
        return rand.nextInt(POSSIBLE_DIRECTIONS);
    }

    /**
     * JavaDoc momentaneo.
     * @return coordinate randomiche.
     */
    private Coordinate getRandomCoordinates() {
        Random rand  = new Random();
        int    first = rand.nextInt(Board.getSize());
        int    sec   = rand.nextInt(Board.getSize());
        return new Coordinate(first, sec);
    }

    /**
     * JavaDoc momentaneo.
     * @param axis
     * @param ship
     * @return
     */
    private boolean isOutOfRange(final int axis, final Ship ship) {
        return Board.getSize() - axis < ship.getShipSize();
    }

    /**
     * JavaDoc momentaneo.
     * @param coord
     * @param direction
     * @param ship
     * @return
     */
    private boolean isPositionAvailable(final Coordinate coord,
        final int direction, final Ship ship) {
            Coordinate temp = (Coordinate) coord.clone();

            if (direction == VERTICAL) {
                return checkVerticalPosition(temp, ship);
            }
            return checkHorizontalPosition(temp, ship);
        }

    /**
     * JavaDoc momentaneo.
     * @param coord
     * @param ship
     * @return
     */
    private boolean checkVerticalPosition(final Coordinate coord, final Ship ship) {
        if (isOutOfRange(coord.getCol(), ship)) {
            return false;
        }
        for (int i = 0; i < ship.getShipSize(); i++) {
            if (map.isCellOccupiedByShip(coord)) {
                return false;
            }
            coord.setCol(coord.getCol() + 1);
        }
        return true;
    }

    /**
     * JavaDoc momentaneo.
     * @param coord
     * @param ship
     * @return
     */
    private boolean checkHorizontalPosition(final Coordinate coord, final Ship ship) {
        if (isOutOfRange(coord.getRow(), ship)) {
            return false;
        }
        for (int i = 0; i < ship.getShipSize(); i++) {
            if (map.isCellOccupiedByShip(coord)) {
                return false;
            }
            coord.setCol(coord.getRow() + 1);
        }
        return true;
    }
}
