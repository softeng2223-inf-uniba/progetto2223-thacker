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
    private GameController gameController;
    private GameMock gameMock;
    @BeforeEach
    void setUp() {
        gameMock = new GameMock();
        gameController = GameController.getInstance();
        gameController.reset(gameMock);
    }

    /**
     *  1) Test: verifica che getInstance() funzioni correttamente.
     *  <p>
     *      Atteso: true.
     *  </p>
     */
    @Test
    void testGetInstance() {
        GameController gc2 = GameController.getInstance();
        assertEquals(gameController, gc2);
    }
    @Test
    void testSetEasyDifficultyIfSessionNotStarted() {
        try {
            gameController.setEasyDifficulty(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }
    @Test
    void testSetEasyDifficultyIfSessionStarted() {
        gameController.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gameController.setEasyDifficulty(gameMock);
        });
    }
}
