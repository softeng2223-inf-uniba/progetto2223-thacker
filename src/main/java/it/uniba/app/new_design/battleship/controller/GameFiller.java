package it.uniba.app.new_design.battleship.controller;

import java.util.LinkedList;
import java.util.Random;

import it.uniba.app.new_design.battleship.entity.Coordinate;
import it.uniba.app.new_design.battleship.entity.Grid;
import it.uniba.app.new_design.battleship.entity.Ship;


/**
 * La classe GameFiller permette di utilizzare il singolo metodo {@code randomlyFill}
 * per posizionare le navi in maniera randomica all'interno della mappa di gioco.
 */
public final class GameFiller {
    private static final Random RAND = new Random();
    private static final int POSSIBLE_DIRECTIONS = 2;
    private static final int VERTICAL = 0;

    private GameFiller() { }

    /**
     * Inserisce nella mappa delle navi ad una ad una tutte le navi disponibili nel gioco.
     * @param ships lista di navi da inserire nella mappa.
     * @param grid mappa su cui inserire le navi.
     */
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

    /**
     * Controlla, sottraendo la dimensione massima della mappa con il valore di indice,
     * se il valore ottenuto è minore dello spazio rimanente disponibile.
     * Se lo spazio rimanente è maggiore o uguale alla dimensione della nave, restituisce valore
     * {@code true}, altrimenti {@code false}.
     * Viene preso in input un solo {@code int} come parametro che rappresenta il valore dell'indice delle
     * righe o delle colonne di una coordinata.
     * Dovrà essere passato il valore di indice di colonna nel caso di inserimento orizzontale,
     * un indice di riga nel caso di inserimento verticale.
     * @param axis indice su cui calcolare la disponibilità di celle libere.
     * @param ship nave che dovrà essere inserita nella mappa, da cui si ricava la dimensione.
     * @return restituisce {@code true} se il risultato della sottrazione tra dimensione mappa
     * e indice è minore della dimensione della nave, {@code false} altrimenti.
    */
    private static boolean isOutOfRange(final int axis, final Ship ship) {
        return (Grid.getSize() - axis) < ship.getSize();
    }

    /**
     * Posiziona una singola nave in modo randomico all'interno della mappa.
     * Sia l'orientamento della nave che le coordinate vengono generate
     * randomicamente, dopodiché la nave viene posizionata secondo l'orientamento
     * e le coordinate generate.
     * @param ship nave da inserire in posizione randomica.
    */
    private static void randomlyInsertShip(final Ship ship, final Grid grid) {
        int direction = getRandomDirection();

        Coordinate coord;
        do {
            coord = getRandomCoordinates();
        } while (!isPositionAvailable(coord, direction, ship, grid));

        if (direction == VERTICAL) {
            insertShipVertical(coord, ship, grid);
        } else {
            insertShipHorizontal(coord, ship, grid);
        }
    }

    private static void insertShipVertical(final Coordinate coord, final Ship ship, final Grid grid) {
        for (int i = 0; i < ship.getSize(); i++) {
            grid.set(coord, ship);
            coord.setRow(coord.getRow() + 1);
        }
    }

    private static void insertShipHorizontal(final Coordinate coord, final Ship ship, final Grid grid) {
        for (int i = 0; i < ship.getSize(); i++) {
            grid.set(coord, ship);
            coord.setCol(coord.getCol() + 1);
        }
    }

    private static boolean isPositionAvailable(final Coordinate coord,
        final int direction, final Ship ship, final Grid grid) {
            Coordinate temp = (Coordinate) coord.clone();

            if (direction == VERTICAL) {
                return checkVerticalPosition(temp, ship, grid);
            }
            return checkHorizontalPosition(temp, ship, grid);
        }

        /**
         * Controlla se una nave può essere inserita in verticale.
         * Chiama il metodo privato {@code isOutOfRange} per controllare
         * se c'è prima di tutto un numero sufficiente di celle disponibili,
         * se non ci sono, il metodo termina immediatamente l'esecuzione restituendo
         * {@code false}, altrimenti controlla che ogni singola cella all'interno della
         * mappa a partire dalla coordinata specificata non contenga già una nave al suo
         * interno ed aumentando di 1 il valore della riga della coordinata fino a che
         * non si raggiunge la dimensione della nave. Se durante il ciclo sulle celle
         * viene trovata una cella già occupato da una nave, restituisce {@code false}
         * @param coord coordinata da cui iniziare a controllare la disponibilità di spazio nella mappa
         * per l'inserimento della nave.
         * @param ship nave da inserire in verticale nella mappa.
         * @return restituisce {@code true} se c'è abbastanza spazio nella mappa per ospitare
         * una nave e se nelle celle non è già posizionata una nave, {@code false} altrimenti.
        */
        private static boolean checkVerticalPosition(final Coordinate coord, final Ship ship, final Grid grid) {
            if (isOutOfRange(coord.getRow(), ship)) {
                return false;
            }

            for (int i = 0; i < ship.getSize(); i++) {
                if (!grid.isCellEmpty(coord)) {
                    return false;
                }
                coord.setRow(coord.getRow() + 1);
            }
            return true;
        }

        private static boolean checkHorizontalPosition(final Coordinate coord, final Ship ship, final Grid grid) {
            if (isOutOfRange(coord.getCol(), ship)) {
                return false;
            }

            for (int i = 0; i < ship.getSize(); i++) {
                if (!grid.isCellEmpty(coord)) {
                    return false;
                }
                coord.setCol(coord.getCol() + 1);
            }
            return true;
        }
}
