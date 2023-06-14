package it.uniba.app.battleship;

import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.InvalidValueException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;

import java.util.LinkedList;
import java.util.Random;

import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.entity.Ship;
import it.uniba.app.battleship.entity.Time;

/**
 * {@code <<Control>>}<hr>
 * Fornisce servizi per gestire una sessione di gioco.
 */
public final class GameController {

    /* COSTANTI PER IL TEMPO */
    private static final int SECOND = 60;
    private static final int MILLISECONDS = 1000;

    /* COSTANTI PER I LIVELLI DI DIFFICOLTÀ */
    private static final String EASY_NAME   = "Facile";
    private static final String MEDIUM_NAME = "Medio";
    private static final String HARD_NAME   = "Difficile";
    private static final String CUSTOM_NAME = "Difficoltà personalizzata";

    private static final int DEFAULT_EASY_MAX_FAILED_ATTEMPTS   = 50;
    private static final int DEFAULT_MEDIUM_MAX_FAILED_ATTEMPTS = 30;
    private static final int DEFAULT_HARD_MAX_FAILED_ATTEMPTS   = 10;

    private int easyMaxFailedAttempts   = DEFAULT_EASY_MAX_FAILED_ATTEMPTS;
    private int mediumMaxFailedAttempts = DEFAULT_MEDIUM_MAX_FAILED_ATTEMPTS;
    private int hardMaxFailedAttempts   = DEFAULT_HARD_MAX_FAILED_ATTEMPTS;

    /* COSTANTI PER IL RIEMPIMENTO DELLA GRIGLIA */
    private static final Random RAND = new Random();
    private static final int POSSIBLE_DIRECTIONS = 2;
    private static final int VERTICAL = 0;

    private static class Holder {
        private static final GameController INSTANCE = new GameController();
    }

    private GameController() { };

    public static GameController getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Reimposta i valori di default per la difficoltà
     * e la dimensione della griglia.
     * @param game oggetto che conserva i dati di gioco.
     */
    public void reset(final Game game) {
        easyMaxFailedAttempts   = DEFAULT_EASY_MAX_FAILED_ATTEMPTS;
        mediumMaxFailedAttempts = DEFAULT_MEDIUM_MAX_FAILED_ATTEMPTS;
        hardMaxFailedAttempts   = DEFAULT_HARD_MAX_FAILED_ATTEMPTS;
        standardGridSize(game);
    }

    /**
     * Avvia una nuova sessione di gioco.<hr>
     * Esegue tutte le inizializzazioni necessarie per giocare.
     *
     * @throws SessionAlreadyStartedException
     *      Non è possibile avviare un altra sessione se un'altra è in corso.
    */
    public void startSession(final Game game)
        throws SessionAlreadyStartedException {
            if (game.isSessionStarted()) {
                throw new SessionAlreadyStartedException();
            }

            if (!game.isDifficultySet()) {
                setEasyDifficulty(game);
            }

            game.startSession();
        }

    /**
     * Termina una sessione di gioco in corso.
     *
     * @throws SessionNotStartedException Non è possibile terminare una sessione se non è in corso.
     */
    public void endSession(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        game.endSession();
    }


    /* METODI PER IL TEMPO */

    /**
     * Permette di settare il numero massimo di
     * minuti a disposizione per giocare.
     * @param game oggetto che conserva i dati di gioco.
     * @param value intero che contiene il numero di minuti
     * a disposizione per giocare.
     * @throws SessionAlreadyStartedException
     * @throws InvalidValueException
     */
    public void setGameTimeMinute(final Game game, final int value)
            throws SessionAlreadyStartedException, InvalidValueException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        if (value < 0) {
            throw new InvalidValueException();
        }
        Time time = new Time();
        time.setTimeLimitMin(value);
        game.setTime(time);
    }

    /**
     * Permette di settare il limite di tempo e il
     * tempo d'inizio in millisecondi.
     * @param time contiene l'istanza di Time da
     * aggiornare con i valori corretti.
     */
    public void setTime(final Time time) {
        time.setTimeLimitMillis(time.getTimeLimitMin() * SECOND * MILLISECONDS);
        time.setStartTimeMill(System.currentTimeMillis());
    }

    /**
     * Permette di controllare quanto tempo è trascorso
     * dall'inizio della partita all'istante in cui
     * viene chiamato il metodo.
     * @param game contiene i dati della partita in corso.
     * @return il tempo trascorso in millisecondi.
     */
    public long checkTimePassedMillis(final Game game) {
        return game.getTime().getCurrentTimeMillis() - game.getTime().getStartTimeMillis();
    }

    /**
     * Permette di controllare se il tempo a dispozione
     * per giocare è terminato, assicurandosi che il limite di tempo
     * sia stato impostato e che la partita sia cominciata.
     * @param game contiene i dati della partita in corso.
     * @return {@code true} se il tempo a dispozione è finito / {@code false} in caso contrario.
     */
    public boolean isTimeOver(final Game game) {
        if ((checkTimePassedMillis(game) > game.getTime().getTimeLimitMill())
                && (game.getTime().getTimeLimitMin() != 0) && game.isSessionStarted()) {
            return true;
        }
        return false;
    }


    /* METODI PER LA DIFFICOLTÀ */

    /**
     * Crea una difficoltà con un numero di tentativi fallibili personalizzato e la imposta.
     * @param game sessione di gioco.
     * @param value numero di tentativi fallibili
     * @throws SessionAlreadyStartedException
     *      non è possibile impostare la difficoltà durante una sessione in corso
     * @throws InvalidValueException
     *      non è possibile inserire un valore minore o uguale a 1
     */
    public void setCustomDifficulty(final Game game, final int value)
        throws SessionAlreadyStartedException, InvalidValueException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        if (value <= 1) {
            throw new InvalidValueException();
        }
        Difficulty diff = new Difficulty();
        diff.setNameLevel(CUSTOM_NAME);
        diff.setMaxFailedAttempts(value);
        game.setDifficulty(diff);
    }

    /**
     * Imposta la difficoltà a facile.
     * @param game sessione di gioco
     * @throws SessionAlreadyStartedException
     *      non è possibile impostare la difficoltà durante una sessione in corso
     */
    public void setEasyDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        diff.setNameLevel(EASY_NAME);
        diff.setMaxFailedAttempts(easyMaxFailedAttempts);
        game.setDifficulty(diff);
    }

     /**
     * Imposta la difficoltà a medio.
     * @param game sessione di gioco.
     * @throws SessionAlreadyStartedException
     *      non è possibile impostare la difficoltà durante una sessione in corso
     */
    public void setMediumDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        diff.setNameLevel(MEDIUM_NAME);
        diff.setMaxFailedAttempts(mediumMaxFailedAttempts);
        game.setDifficulty(diff);
    }

    /**
     * Imposta la difficoltà a difficile.
     * @param game sessione di gioco.
     * @throws SessionAlreadyStartedException
     *      non è possibile impostare la difficoltà durante una sessione in corso
     */
    public void setHardDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        diff.setNameLevel(HARD_NAME);
        diff.setMaxFailedAttempts(hardMaxFailedAttempts);
        game.setDifficulty(diff);
    }

    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Facile" e
     * il numero massimo di tentativi falliti a maxFailedAttempts.
     *
     * @param maxFailedAttempts il numero massimo di tentativi falliti.
     *
     * @throws SessionAlreadyStartedException
     *         se la sessione è già iniziata non si può modificare la difficoltà.
     * @throws InvalidValueException
     *         se il valore inserito è minore di 1 non si può impostare la difficoltà.
     */
    public void setCustomEasyDifficulty(final Game game,
                                        final int maxFailedAttempts)
            throws SessionAlreadyStartedException, InvalidValueException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        if (maxFailedAttempts < 1) {
            throw new InvalidValueException();
        }
        easyMaxFailedAttempts = maxFailedAttempts;
    }

    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Medio" e
     * il numero massimo di tentativi falliti a maxFailedAttempts.
     *
     * @param maxFailedAttempts il numero massimo di tentativi falliti.
     */
    public void setCustomMediumDifficulty(final Game game,
                                          final int maxFailedAttempts)
            throws SessionAlreadyStartedException, InvalidValueException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        if (maxFailedAttempts < 1) {
            throw new InvalidValueException();
        }
        mediumMaxFailedAttempts = maxFailedAttempts;
    }

    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Difficile" e
     * il numero massimo di tentativi falliti a maxFailedAttempts.
     *
     * @param maxFailedAttempts il numero massimo di tentativi falliti.
     */
    public void setCustomHardDifficulty(final int maxFailedAttempts) {
        hardMaxFailedAttempts = maxFailedAttempts;
    }

    /**
     * Fornisce le informazioni relative alla difficoltà impostata in un determinato istante.
     *
     * @return difficoltà selezionata
     * @throws CloneNotSupportedException
     */
    public Difficulty getDifficulty(final Game game) throws CloneNotSupportedException {
        return game.getDifficulty();
    }

    // METODI PER I TENTATIVI
    /**
     * Gestisce la chiamata di strike, se una nave viene affondata, incrementa
     * il numero di navi affondate in Game, se invece il colpo va a vuoto,
     * viene incrementato il valore di tentativi falliti di Game.
     * @param game oggetto che conserva i parametri di gioco
     * @param coord coordinate
     * @throws SessionNotStartedException non è possibile lanciare il colpo se una
     * partita non è cominciata
     * @throws CellAlreadyMarkedException non è possibile colpire una cella
     * già colpita in precedenza
     * @throws OutOfMapException non è possibile lanciare il colpo fuori dalla
     * portata della mappa
     */
    public int strike(final Game game, final Coordinate coord)
        throws SessionNotStartedException, CellAlreadyMarkedException,
            OutOfMapException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        if (!game.isAttemptWithinBounds(coord)) {
            throw new OutOfMapException();
        }
        if (game.isAlreadyAttempted(coord)) {
            throw new CellAlreadyMarkedException();
        }

        game.addAttempt(coord);
        Grid curGrid = game.getSessionGrid();

        if (!curGrid.isCellEmpty(coord)) {
            Ship occupantShip = curGrid.get(coord);
            occupantShip.hit();
            if (occupantShip.isSunk()) {
                game.incrementSunkShips();
                return 1;
            } else {
                return 0;
            }
        }
        // se non è stata colpita alcuna nave:
        game.incrementFailedAttempt();
        return -1;
    }

    /**
     * Fornisce il numero di tentativi effettuati fino al momento corrente di una sessione di gioco in corso.
     *
     * @param game la sessione
     * @return numero di tentativi totali all'istante corrente
     * @throws SessionNotStartedException
     *      non è possibile ottenere questa informazione per una sessione non in corso.
     */
    public int getAttempts(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        return game.getAttempts();
    }

    /**
     * Fornisce il numero di tentativi effettuati e falliti fino al momento corrente di una sessione di gioco in corso.
     * Per fallito si intende un tentativo che non ha colpito alcuna nave.
     *
     * @param game la sessione
     * @return numero di tentativi falliti all'istante corrente
     * @throws SessionNotStartedException
     *      non è possibile ottenere questa informazione per una sessione non in corso.
     */
    public int getFailedAttempts(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        return game.getFailedAttempts();
    }

    /* METODI PER LA GRIGLIA */

    /**
     * Fornisce una copia della griglia di gioco in un determinato istante della sessione in corso.
     * Non è possibile ottenere una griglia prima che una sessione di gioco sia iniziata.
     *
     * Attenzione: Da usare solo per scopi di presentazione,
     * operare sulla copia non influenza il corso della sessione.
     * @param game la sessione di gioco
     * @return griglia della sessione corrente nell'istante corrente
     * @throws SessionNotStartedException
     */
    public Grid getSessionGrid(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        return game.getSessionGrid();
    }

    /**
     * Imposta la dimensione della mappa a 10x10.
     */
    public void standardGridSize(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Grid.setChosenSize(Grid.getDefaultSize());
    }

    /**
     * Imposta la dimensione della mappa a 18x18.
     */
    public void largeGridSize(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Grid.setChosenSize(Grid.getLargeSize());
    }

    /**
     * Imposta la dimensione della mappa a 26x26.
     */
    public void extraLargeGridSize(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Grid.setChosenSize(Grid.getExtraLargeSize());
    }

    /**
     * Inserisce nella mappa delle navi ad una ad una tutte le navi disponibili nel gioco.
     * @param ships lista di navi da inserire nella mappa.
     * @param grid mappa su cui inserire le navi.
     */
    public void randomlyFill(final LinkedList<Ship> ships, final Grid grid) {
        for (Ship ship : ships) {
            randomlyInsertShip(ship, grid);
        }
    }

    /* METODI (PRIVATI) DI SUPPORTO */


    /**
     * Genera un {@code int} compreso tra {@code 0} ed {@code 1} che corrisponde all'orientamento.
     * della nave. 0 indica un orientamento verticale; 1 indica un orientamento
     * orizzontale.
     * @return un intero compreso tra {@code 0} ed {@code 1} generato randomicamente.
     */
    private int getRandomDirection() {
        return RAND.nextInt(POSSIBLE_DIRECTIONS);
    }

    /**
     * Restituisce un nuovo oggetto di tipo {@code Coordinates}
     * con valori interi randomici compresi
     * tra 0 e dimensione massima della mappa di gioco.
     * @return {@code Coordinate} con valori randomici.
     */
    private Coordinate getRandomCoordinates() {
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
    private boolean isOutOfRange(final int axis, final Ship ship) {
        return (Grid.getSize() - axis) < ship.getSize();
    }

    /**
     * Posiziona una singola nave in modo randomico all'interno della mappa.
     * Sia l'orientamento della nave che le coordinate vengono generate
     * randomicamente, dopodiché la nave viene posizionata secondo l'orientamento
     * e le coordinate generate.
     * @param ship nave da inserire in posizione randomica.
    */
    private void randomlyInsertShip(final Ship ship, final Grid grid) {
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
    private void insertShipVertical(final Coordinate coord, final Ship ship, final Grid grid) {
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
    private void insertShipHorizontal(final Coordinate coord, final Ship ship, final Grid grid) {
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
    private boolean isPositionAvailable(final Coordinate coord,
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
    private boolean checkVerticalPosition(final Coordinate coord, final Ship ship, final Grid grid) {
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
    private boolean checkHorizontalPosition(final Coordinate coord, final Ship ship, final Grid grid) {
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
