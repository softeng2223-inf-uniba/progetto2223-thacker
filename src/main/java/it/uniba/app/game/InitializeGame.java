package it.uniba.app.game;

import java.util.Random;
import java.util.LinkedList;
import it.uniba.app.utility.Coordinate;

/**
 * JavaDoc momentaneo.
 */
public final class InitializeGame {
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
     * Utilizzato per creare valori randomici affidabili, in quanto se
     * dovesse essere istanziato più volte un oggetto di tipo {@code Random}
     * non produrrebbe valori affidabili, al contrario di avere uno
     * fisso per classe.
     */
    private static Random rand = new Random();
    /**
     * Se il gioco è in corso prende valore {@code true}, altrimenti {@code false}.
     */
    private static boolean gameRunning = false;

    /**
     * Cotruttore a zero parametri che inizializza un oggetto di tipo InitializeGame.
     * Una volta costruito l'oggetto, il gioco è considerato in corso.
     */
    public InitializeGame() {
        setGameRunningToTrue();
    }

    /**
     * Imposta la mappa di gioco posizionando le navi da colpire in modo randomico.
     */
    public void initGame(final Board map) {
        randomlyFillMap(map);
        setGameRunningToTrue();
    }

    private static void setGameRunningToTrue() {
        gameRunning = true;
    }

    /**
     * Inserisce nella mappa delle navi ad una ad una tutte le navi disponibili nel gioco.
     */
    private void randomlyFillMap(final Board map) {
        LinkedList<Ship> shipList = getShipsSet();

        for (Ship ship : shipList) {
            randomlyInsertShip(ship, map);
        }
    }

    /**
     * Posiziona una singola nave in modo randomico all'interno della mappa.
     * Sia l'orientamento della nave che le coordinate vengono generate
     * randomicamente, dopodiché la nave viene posizionata secondo l'orientamento
     * e le coordinate generate.
     * @param ship nave da inserire in posizione randomica.
     */
    private void randomlyInsertShip(final Ship ship, final Board map) {
        int direction = getRandomDirection();

        Coordinate coord;
        do {
            coord = getRandomCoordinates();
        } while (!isPositionAvailable(coord, direction, ship, map));

        if (direction == VERTICAL) {
            insertShipVertical(coord, ship, map);
        } else {
            insertShipHorizontal(coord, ship, map);
        }
    }

    /**
     * Inserisce la nave con orientamento verticale nelle coordinate specificate.
     * @param coord coordinate in cui cominciare ad inserire la nave in verticale.
     * @param ship specifica quale nave dev'essere inserita nella mappa.
     */
    private void insertShipVertical(final Coordinate coord, final Ship ship, final Board map) {
        for (int i = 0; i < ship.getShipSize(); i++) {
            map.setElement(coord, ship);
            coord.setRow(coord.getRow() + 1);
        }
    }

    /**
     * Inserisce la nave con orientamento orizzontale nelle coordinate specificate.
     * @param coord coordinate in cui cominciare ad inserire la nave in verticale.
     * @param ship specifica quale nave dev'essere inserita nella mappa.
     */
    private void insertShipHorizontal(final Coordinate coord, final Ship ship, final Board map) {
        for (int i = 0; i < ship.getShipSize(); i++) {
            map.setElement(coord, ship);
            coord.setCol(coord.getCol() + 1);
        }
    }

    /**
     * Genera un {@code int} compreso tra {@code 0} ed {@code 1} che corrisponde all'orientamento.
     * della nave. 0 indica un orientamento verticale; 1 indica un orientamento
     * orizzontale.
     * @return un intero compreso tra {@code 0} ed {@code 1} generato randomicamente.
     */
    private int getRandomDirection() {
        return rand.nextInt(POSSIBLE_DIRECTIONS);
    }

     /**
     * Restituisce un nuovo oggetto di tipo {@code Coordinates}
     * con valori interi randomici compresi
     * tra 0 e dimensione massima della mappa di gioco.
     * @return {@code Coordinate} con valori randomici.
     */
    private Coordinate getRandomCoordinates() {
        int    first = rand.nextInt(Board.getSize());
        int    sec   = rand.nextInt(Board.getSize());
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
    private boolean isOutOfRange(final int axis, final Ship ship) {
        return (Board.getSize() - axis) < ship.getShipSize();
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
    private boolean isPositionAvailable(final Coordinate coord,
        final int direction, final Ship ship, final Board map) {
            Coordinate temp = (Coordinate) coord.clone();

            if (direction == VERTICAL) {
                return checkVerticalPosition(temp, ship, map);
            }
            return checkHorizontalPosition(temp, ship, map);
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
    private boolean checkVerticalPosition(final Coordinate coord, final Ship ship, final Board map) {
        if (isOutOfRange(coord.getRow(), ship)) {
            return false;
        }
        for (int i = 0; i < ship.getShipSize(); i++) {
            if (map.isCellOccupiedByShip(coord)) {
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
    private boolean checkHorizontalPosition(final Coordinate coord, final Ship ship, final Board map) {
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

    public static boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Restituisce una lista di navi contenente, per tipo di nave, il numero di istanze
     * pari al numero di esemplari di quel tipo di nave che si possono inserire nella mappa.
     * @return lista di navi.
     */
    private LinkedList<Ship> getShipsSet() {
        LinkedList<Ship>   shipsList = new LinkedList<Ship>();
        Cacciatorpediniere p = new Cacciatorpediniere();
        Incrociatore       s = new Incrociatore();
        Corazzata          l = new Corazzata();
        Portaerei          c = new Portaerei();

        for (int i = 0; i < p.getNumberOfInstances(); i++) {
            shipsList.add(new Cacciatorpediniere());
        }
        for (int i = 0; i < s.getNumberOfInstances(); i++) {
            shipsList.add(new Incrociatore());
        }
        for (int i = 0; i < l.getNumberOfInstances(); i++) {
            shipsList.add(new Corazzata());
        }
        for (int i = 0; i < c.getNumberOfInstances(); i++) {
            shipsList.add(new Portaerei());
        }
        return shipsList;
    }
}
