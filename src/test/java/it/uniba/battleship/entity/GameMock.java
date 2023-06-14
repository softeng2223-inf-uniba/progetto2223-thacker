package it.uniba.battleship.entity;

import it.uniba.app.battleship.GameController;
import it.uniba.app.battleship.entity.*;

import java.util.HashSet;
import java.util.LinkedList;

public class GameMock extends Game{
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
    public void setAttemptsList(HashSet<Coordinate> set) {
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

    public boolean isAlreadyAttempted(final Coordinate coord) {
        return attemptsMock.contains(coord);
    }

    public boolean isAttemptWithinBounds(final Coordinate coord) {
        return gridMock.isWithinBounds(coord);
    }

    public int getFailedAttempts() {
        return failedAttemptsMock;
    }

    public Time getTime() {
        return timeMock.clone();
    }

    public void setTime(final Time t) {
        timeMock = t.clone();
    }
}
