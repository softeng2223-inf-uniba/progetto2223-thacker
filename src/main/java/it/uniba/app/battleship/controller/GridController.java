package it.uniba.app.battleship.controller;

import java.util.LinkedList;
import java.util.Random;

import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.entity.Ship;
import it.uniba.app.utility.Color;

/**
 * Classe che contiene i metodi utili alla visualizzazione della
 * mappa delle navi e della mappa dei colpi.
 */
public final class GridController {
    private static final Random RAND = new Random();
    private static final int POSSIBLE_DIRECTIONS = 2;
    private static final int VERTICAL = 0;

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
        for (int row = 0; row < Grid.getSize(); row++) {
            str += row + ":    ";
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coords = new Coordinate(row, col);
                if (grid.isCellHit(coords)) {
                    if (grid.isCellEmpty(coords)) {
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

    /**
     * Genera un {@code int} compreso tra {@code 0} ed {@code 1} che corrisponde all'orientamento.
     * della nave. 0 indica un orientamento verticale; 1 indica un orientamento
     * orizzontale.
     * @return un intero compreso tra {@code 0} ed {@code 1} generato randomicamente.
     */
    private static int getRandomDirection() {
        return RAND.nextInt(POSSIBLE_DIRECTIONS);
    }

    /**
     * Restituisce un nuovo oggetto di tipo {@code Coordinates}
     * con valori interi randomici compresi
     * tra 0 e dimensione massima della mappa di gioco.
     * @return {@code Coordinate} con valori randomici.
     */
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

    /**
     * Inserisce la nave con orientamento verticale nelle coordinate specificate.
     * @param coord coordinate in cui cominciare ad inserire la nave in verticale.
     * @param ship specifica quale nave dev'essere inserita nella mappa.
     */
    private static void insertShipVertical(final Coordinate coord, final Ship ship, final Grid grid) {
        for (int i = 0; i < ship.getSize(); i++) {
            grid.set(coord, ship);
            coord.setRow(coord.getRow() + 1);
        }
    }

    /**
     * Inserisce la nave con orientamento orizzontale nelle coordinate specificate.
     * @param coord coordinate in cui cominciare ad inserire la nave in verticale.
     * @param ship specifica quale nave dev'essere inserita nella mappa.
     */
    private static void insertShipHorizontal(final Coordinate coord, final Ship ship, final Grid grid) {
        for (int i = 0; i < ship.getSize(); i++) {
            grid.set(coord, ship);
            coord.setCol(coord.getCol() + 1);
        }
    }

    /**
     * Controlla che, a partire dalla posizione indicata da {@code Coordinates} nella
     * mappa navi, ci siano abbastanza posizioni disponibili per poter inserire la nave
     * passata come parametro in base all'orientamento scelto.<p>
     * Se la direzione di inserimento è verticale viene invocata la funzione {@code checkVerticalPosition},
     * altrimenti viene invocata {@code checkHorizontalPosition}.
     * @param coord coordinate da cui partire per effettuare il controllo.
     * @param direction orientamento della nave.
     * @param ship nave da inserire nella mappa.
     * @return {@code true} se ci sono abbastanza posizioni disponibili nella mappa
     * per ospitare la nave, {@code false} altrimenti.
     */
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

    /**
     * Controlla se una nave può essere inserita in orizzontale.
     * Chiama il metodo privato {@code isOutOfRange} per controllare
     * se c'è prima di tutto un numero sufficiente di celle disponibili,
     * se non ci sono, il metodo termina immediatamente l'esecuzione restituendo
     * {@code false}, altrimenti controlla che ogni singola cella all'interno della
     * mappa a partire dalla coordinata specificata non contenga già una nave al suo
     * interno ed aumentando di 1 il valore della colonna della coordinata fino a che
     * non si raggiunge la dimensione della nave. Se durante il ciclo sulle celle
     * viene trovata una cella già occupato da una nave, restituisce {@code false}.
     * @param coord coordinata da cui iniziare a controllare la disponibilità di spazio nella mappa
     * per l'inserimento della nave.
     * @param ship nave da inserire in orizzontale nella mappa.
     * @return restituisce {@code true} se c'è abbastanza spazio nella mappa per ospitare
     * una nave e se nelle celle non è già posizionata una nave, {@code false} altrimenti.
     */
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