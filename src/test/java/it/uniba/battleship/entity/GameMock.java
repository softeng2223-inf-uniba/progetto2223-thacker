package it.uniba.battleship.entity;

import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.entity.Time;

import java.util.HashSet;

public class GameMock {
    private boolean sessionStartedMock;
    private boolean diffSetMock;
    private int sunkShipsMock;
    private Difficulty difficultyMock;
    private Grid gridMock;
    private Time timeMock;
    private HashSet<Coordinate> attemptsMock;
    private int failedAttemptsMock;
    public GameMock() {

    }
}
