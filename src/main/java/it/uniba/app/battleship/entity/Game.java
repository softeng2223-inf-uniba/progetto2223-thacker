package it.uniba.app.battleship.entity;

import java.util.HashSet;
import java.util.LinkedList;

import it.uniba.app.battleship.GameController;

/**
 * {@code <<entity>>}
 * Fornisce servizi per gestire una sessione di gioco (o partita) di <i>Battleship solitaire</i>.
 */
public class Game {
    private static final LinkedList<Ship> SHIPS = getShipSet();

    private boolean sessionStarted;
    private boolean diffSet;
    private int sunkShips;

    private Difficulty difficulty;
    private Grid grid;
    private Time time;

    private HashSet<Coordinate> attempts;
    private int failedAttempts;


    /** Istanzia un oggetto della classe Game. */
    public Game() {
        sessionStarted = false;
        diffSet = false;
        difficulty = new Difficulty();
        time = new Time();
    }

    /**
     * Restituisce il tempo impsotat.
     * @return restituisce il tempo.
     */
    public Time getTime() {
        return time.clone();
    }

    /**
     * Permette di impostare il tempo di gioco.
     * @param t tempo selezionato.
     */
    public void setTime(final Time t) {
        time = t.clone();
    }

    /**
     * Incrementa il valore delle navi affondate.
     */
    public void incrementSunkShips() {
        sunkShips += 1;
    }

    /**
     * Incrementa il valore dei tentativi falliti.
     */
    public void incrementFailedAttempt() {
        failedAttempts += 1;
    }

    /**
     * Restituisce il valore delle navi che sono
     * state già affondate.
     * @return numero di navi affondate
     */
    public int getSunkShips() {
        return sunkShips;
    }

    /**
     * Aggiunge un tentativo alla partita.
     *
     * @param attempt coordinata
     */
    public void addAttempt(final Coordinate attempt) {
        attempts.add(attempt);
    }

    /**
     * Informa se una sessione di gioco è iniziata e quindi in corso o meno.
     *
     * @return true se la sessione è in corso, false altrimenti.
     */
    public boolean isSessionStarted() {
        return sessionStarted;
    }

    /**
     * Informa se la difficoltà è stata impostata o meno.
     *
     * @return <code>true</code> se la difficoltà è stata impostata, <code>false</code> altrimenti.
     */
    public boolean isDifficultySet() {
        return diffSet;
    }

    /**
     * Avvia una nuova sessione di gioco.<hr>
     * Esegue tutte le inizializzazioni necessarie per giocare.
     */
    public void startSession() {
        grid = new Grid();
        GameController.getInstance().randomlyFill(SHIPS, grid);
        GameController.getInstance().setTime(time);

        sunkShips = 0;
        attempts = new HashSet<>();
        failedAttempts = 0;
        sessionStarted = true;
    }

    /** Termina una sessione di gioca. */
    public void endSession() {
        sessionStarted = false;
    }

    /**
     * Imposta una difficoltà.
     *
     * @param choice difficoltà da impostare
     */
    public void setDifficulty(final Difficulty choice) {
        difficulty = choice.clone();
        diffSet = true;
        }

    /**
     * Fornisce le informazioni relative alla difficoltà impostata in un determinato istante.
     *
     * @return difficoltà selezionata
     */
    public Difficulty getDifficulty() {
        return difficulty.clone();
    }

    /**
     * Fornisce una copia della griglia di gioco in un determinato istante della sessione in corso.
     * Non è possibile ottenere una griglia prima che una sessione di gioco sia iniziata.
     *
     * Attenzione: Da usare solo per scopi di presentazione,
     * operare sulla copia non influenza il corso della sessione.
     *
     * @return griglia della sessione corrente nell'istante corrente
     */
    public Grid getSessionGrid() {
        return grid.clone();
    }


    /**
     * Fornisce il numero di tentativi effettuati fino ad un dato istante
     * durante una sessione di gioco.
     *
     * @return tentativi totali nell'istante corrente
     */
    public int getAttempts() {
        return attempts.size();
    }

    /**
     * Effettua un controllo sulle coordinate che sono già state colpite.
     * Se la {@code Coordinate} si rivela essere una coordinata già colpita,
     * allora il controllo darà esito positivo, altrimenti sarà negativo.
     * @param coord coordinate su cui effettuare il controllo
     * @return {@code true} se è una posizione già colpita, {@code false} altrimenti
     */
    public boolean isAlreadyAttempted(final Coordinate coord) {
        return attempts.contains(coord);
    }

    /**
     * Controlla se una coordinata rientra tra quelle possibili per la griglia di gioco.
     *
     * @param coord coordinata
     * @return
     */
    public boolean isAttemptWithinBounds(final Coordinate coord) {
        return grid.isWithinBounds(coord);
    }

    /**
     * Fornisce il numero di tentativi falliti fino ad un certo istante di una sessione di gioco.
     *
     * @return tentativi falliti all'istante corrente
     */
    public int getFailedAttempts() {
        return failedAttempts;
    }

    /* === PRIVATE METHODS === */

      /**
     * Restituisce una lista di navi contenente, per ogni tipo di nave, tante istanze
     * quanti sono numero di esemplari previsti per tale tipo.
     *
     * @return lista di navi
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

}
