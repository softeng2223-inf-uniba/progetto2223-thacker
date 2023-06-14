package it.uniba.battleship;

import it.uniba.app.battleship.GameController;
import it.uniba.battleship.entity.GameMock;

import it.uniba.app.battleship.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.InvalidValueException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    private GameController gc;
    private GameMock gameMock;
    @BeforeEach
    void setUp() {
        gameMock = new GameMock();
        gc = GameController.getInstance();
        gc.reset(gameMock);
    }
    @Test
    void testGetInstance() {
        GameController gc2 = GameController.getInstance();
        assertEquals(gc, gc2);
    }
    @Test
    void testSetEasyDifficultyIfSessionNotStarted() {
        try {
            gc.setEasyDifficulty(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }
    @Test
    void testSetEasyDifficultyIfSessionStarted() {
        gc.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gc.setEasyDifficulty(gameMock);
        });
    }
}
