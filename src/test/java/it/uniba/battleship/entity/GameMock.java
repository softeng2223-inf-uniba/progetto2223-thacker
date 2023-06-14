package it.uniba.battleship.entity;

import it.uniba.app.battleship.GameController;
import it.uniba.app.battleship.entity.*;

import java.util.HashSet;
import java.util.LinkedList;

public class GameMock {
    private static final    LinkedList<Ship> SHIPS = getShipSet();
    private boolean sessionStartedMock;
    private boolean diffSetMock;
    private int sunkShipsMock;
    private Difficulty difficultyMock;
    private Grid gridMock;
    private Time timeMock;
    private HashSet<Coordinate> attemptsMock;
    private int failedAttemptsMock;
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
    public void startSession() {
        gridMock = new Grid();
        GameController.getInstance().randomlyFill(SHIPS, gridMock);
        GameController.getInstance().setTime(timeMock);

        sunkShipsMock = 0;
        attemptsMock = new HashSet<>();
        failedAttemptsMock = 0;
        sessionStartedMock = true;
    }
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
