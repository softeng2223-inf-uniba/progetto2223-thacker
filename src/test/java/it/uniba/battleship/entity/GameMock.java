package it.uniba.battleship.entity;

import it.uniba.app.battleship.GameController;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Time;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Ship;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Classe di mock per il testing.
 * Questa classe viene utilizzata per simulare un oggetto reale durante i test.
 */
public class GameMock extends Game {
    private static final    LinkedList<Ship> SHIPS = getShipSet();
    private boolean sessionStartedMock;
    private boolean diffSetMock;
    private int sunkShipsMock;
    private Difficulty difficultyMock;
    private Grid gridMock;
    private Time timeMock;
    private HashSet<Coordinate> attemptsMock;
    private int failedAttemptsMock;

    /**
     * Costruttore di GameMock().
     * Inizializza i valori della classe.
     */
    public GameMock() {
        sessionStartedMock = false;
        diffSetMock = false;
        sunkShipsMock = 0;
        difficultyMock = new Difficulty();
        gridMock = new Grid();
        timeMock = new Time();
        attemptsMock = new HashSet<>();
        failedAttemptsMock = 0;
    }

    /**
     * Permette di avviare una partita e imposta correttamente
     * i valori necessari per poter giocare.
     */
    public void startSession() {
        gridMock = new Grid();
        GameController.getInstance().randomlyFill(SHIPS, gridMock);
        GameController.getInstance().setTime(timeMock);

        sunkShipsMock = 0;
        attemptsMock = new HashSet<>();
        failedAttemptsMock = 0;
        sessionStartedMock = true;
    }

    /**
     * Restituisce una lista di navi.
     * @return lista di navi.
     */
    private static LinkedList<Ship> getShipSet() {
        LinkedList<Ship> shipsList = new LinkedList<Ship>();

        for (int tId = 0; tId < Ship.getNumberOfTypes(); tId++) {
            for (int i = 0; i < Ship.getMaxInstances(tId); i++) {
                shipsList.add(new Ship(tId));
            }
        }
        return shipsList;
    }

    /**
     * Informa se una sessione di gioco e' iniziata e quindi in corso o meno.
     *
     * @return true se la sessione e' in corso, false altrimenti.
     */
    public boolean isSessionStarted() {
        return sessionStartedMock;
    }

    /**
     * Informa se la difficolta' e' stata impostata o meno.
     *
     * @return <code>true</code> se la difficolta' e' stata impostata, <code>false</code> altrimenti.
     */
    public boolean isDifficultySet() {
        return diffSetMock;
    }

    /**
     * Restituisce il set che contiene le coordinate colpite.
     * @return set di coordinate
     */
    public HashSet<Coordinate> getAttemptsList() {
        return attemptsMock;
    }

    /**
    * Imposta attemptsMock con un dato HashSet di oggetti Coordinate.
    *
    * @param set l'HashSet di oggetti Coordinate che contiene l'elenco dei tentativi
    */
    public void setAttemptsList(final HashSet<Coordinate> set) {
        attemptsMock = set;
    }

    /**
     * Fornisce il numero di tentativi effettuati fino ad un dato istante
     * durante una sessione di gioco.
     *
     * @return tentativi totali nell'istante corrente
     */
    public int getAttempts() {
        return attemptsMock.size();
    }

    /**
     * Fornisce una copia della griglia di gioco in un determinato istante della sessione in corso.
     *
     * Attenzione: Da usare solo per scopi di presentazione,
     * operare sulla copia non influenza il corso della sessione.
     *
     * @return griglia della sessione corrente nell'istante corrente
     */
    public Grid getGrid() {
        return gridMock.clone();
    }

    /** Termina una sessione di gioca. */
    public void endSession() {
        sessionStartedMock = false;
    }

    /**
     * Imposta una difficolta'.
     *
     * @param choice difficolta' da impostare
     */
    public void setDifficulty(final Difficulty choice) {
        difficultyMock = choice.clone();
        diffSetMock = true;
    }

    /**
     * Fornisce le informazioni relative alla difficolta'
     * impostata in un determinato istante.
     *
     * @return difficolta' selezionata
     */
    public Difficulty getDifficulty() {
        return difficultyMock.clone();
    }

    /**
     * Fornisce una copia della griglia di gioco in un determinato istante della sessione in corso.
     * Non e' possibile ottenere una griglia prima che una sessione di gioco sia iniziata.
     *
     * Attenzione: Da usare solo per scopi di presentazione,
     * operare sulla copia non influenza il corso della sessione.
     *
     * @return griglia della sessione corrente nell'istante corrente
     */
    public Grid getSessionGrid() {
        return gridMock.clone();
    }

    /**
     * Effettua un controllo sulle coordinate che sono gia' state colpite.
     * Se la {@code Coordinate} si rivela essere una coordinata gia' colpita,
     * allora il controllo dara' esito positivo, altrimenti sara' negativo.
     * @param coord coordinate su cui effettuare il controllo
     * @return {@code true} se e' una posizione gia' colpita, {@code false} altrimenti
     */
    public boolean isAlreadyAttempted(final Coordinate coord) {
        return attemptsMock.contains(coord);
    }

    /**
     * Controlla se una coordinata rientra tra quelle possibili per la griglia di gioco.
     *
     * @param coord coordinata
     * @return
     */
    public boolean isAttemptWithinBounds(final Coordinate coord) {
        return gridMock.isWithinBounds(coord);
    }

    /**
     * Fornisce il numero di tentativi falliti fino ad un certo istante di una sessione di gioco.
     *
     * @return tentativi falliti all'istante corrente
     */
    public int getFailedAttempts() {
        return failedAttemptsMock;
    }

    /**
     * Fornisce un clone dell'oggetto di tipo Time
     * istanziato in Game.
     */
    public Time getTime() {
        return timeMock.clone();
    }

    /**
     * Setta l'oggetto time istanziato in Game ai
     * valori contenuti nell'oggetto t passato in
     * input.
     */
    public void setTime(final Time t) {
        timeMock = t.clone();
    }

    /**
     * Restituisce il valore delle navi che sono
     * state gia' affondate.
     * @return numero di navi affondate
     */
    public int getSunkShips() {
        return sunkShipsMock;
    }

    // Additional methods for setting mock values
    /**
     * Restituisce sunkShips aggiornato con
     * il valore passato in input.
     * @param sunkShips
     */
    public void setSunkShipsMock(final int sunkShips) {
        sunkShipsMock = sunkShips;
    }

    /**
     * Restituisce una griglia di gioco uguale alla
     * griglia passata in input.
     * @param grid
     */
    public void setGridMock(final Grid grid) {
        gridMock = grid.clone();
    }

    /**
     * Setta il {@code timeMock} con i valori
     * contenuti nel {@code time} passato in input.
     * @param time contiene i valori che servono per
     * inizializzare il {@code timeMock}.
     */
    public void setTimeMock(final Time time) {
        timeMock = time.clone();
    }

    /**
    * Imposta il conteggio dei tentativi falliti con il valore intero specificato.
    *
    * @param failedAttempts il numero di tentativi falliti da impostare
    */
    public void setFailedAttemptsMock(final int failedAttempts) {
        failedAttemptsMock = failedAttempts;
    }

    public void incrementSunkShips() {
        sunkShipsMock++;
    }

    public void incrementFailedAttempt() {
        failedAttemptsMock++;
    }

    public void addAttempt(final Coordinate attempt) {
        attemptsMock.add(attempt);
    }
}
