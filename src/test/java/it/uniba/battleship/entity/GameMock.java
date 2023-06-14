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
    public boolean isSessionStarted() {
        return sessionStartedMock;
    }
    public boolean isDifficultySet() {
        return diffSetMock;
    }
    public HashSet<Coordinate> getAttemptsList() {
        return attemptsMock;
    }
    public void setAttemptsList(HashSet<Coordinate> set) {
        attemptsMock = set;
    }
    public int getAttempts() {
        return attemptsMock.size();
    }

    public Grid getGrid() {
        return gridMock.clone();
    }

    public void endSession() {
        sessionStartedMock = false;
    }

    public void setDifficulty(final Difficulty choice) {
        difficultyMock = choice.clone();
        diffSetMock = true;
    }

    public Difficulty getDifficulty() {
        return difficultyMock.clone();
    }

    public Grid getSessionGrid() {
        return gridMock.clone();
    }
}
