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
     * 1) Test: verifica che getInstance() funzioni correttamente.
     * <p>
     * Atteso: true.
     * </p>
     */
    @Test
    void testGetInstance() {
        GameController gc2 = GameController.getInstance();
        assertEquals(gameController, gc2);
    }

    /**
     * 2) Test: verifica che chiamare setEasyDifficulty() prima
     * di startSession() imposti correttamente la difficoltà
     * a Facile.
     * <p>
     * Atteso: non deve lanciare SessionAlreadyStartedException.
     * </p>
     */
    @Test
    void testSetEasyDifficultyIfSessionNotStarted() {
        try {
            gameController.setEasyDifficulty(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * 3) Test: verifica che chiamare setEasyDifficulty() dopo
     * startSession() imposti lanci correttamente l'eccezione.
     * <p>
     * Atteso: SessionAlreadyStartedException.
     * </p>
     */
    @Test
    void testSetEasyDifficultyIfSessionStarted() {
        gameController.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gameController.setEasyDifficulty(gameMock);
        });
    }

    /**
     * 4) Test: verifica che chiamare setMediumDifficulty() prima
     * di startSession() imposti correttamente la difficoltà
     * a Media.
     * <p>
     *     Atteso: non deve lanciare SessionAlreadyStartedException.
     * </p>
     */
    @Test
    void testSetMediumDifficultyIfSessionNotStarted() {
        try {
            gameController.setMediumDifficulty(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * 5) Test: Verifica che il metodo setMediumDifficulty() non imposti
     * la difficolta' a 'Media' durante una sessione
     * di gioco.
     * <p>
     *  Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetMediumDifficultyIfSessionStarted() {
        gameController.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gameController.setMediumDifficulty(gameMock);
        });
    }

    /**
     * 6) Test: Verifica che il metodo setHardDifficulty() imposti
     * la difficolta' a 'Difficile' prima dell'inizio di una sessione
     * di gioco.
     * <p>
     *  Atteso:  non deve lanciare SessionAlreadyStartedException.
     */
    @Test
    void testSetHardDifficultyIfSessionNotStarted() {
        try {
            gameController.setEasyDifficulty(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * 7) Test: Verifica che il metodo setHardDifficulty() non imposti
     * la difficolta' a 'Difficile' durante una sessione
     * di gioco.
     * <p>
     *  Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetHardDifficultyIfSessionStarted() {
        gameController.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gameController.setHardDifficulty(gameMock);
        });
    }

    /**
     * 8) Test: Verifica che il metodo setCustomEasyDifficulty() imposti
     * la difficolta' 'Facile' con un numero di tentativi scelto dall'utente
     * prima dell'inizio di una sessione di gioco.
     * <p>
     *  Atteso: non deve lanciare SessionAlreadyStartedException.
     */
    @Test
    void testSetCustomEasyDifficultyIfSessionNotStarted() {
        try {
            gameController.setCustomEasyDifficulty(gameMock, 1);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * 9) Test: Verifica che il metodo setCustomEasyDifficulty() non imposti
     * la difficolta' 'Facile' con un numero di tentativi scelto dall'utente
     * durante una sessione di gioco.
     * <p>
     *  Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetCustomEasyDifficultyIfSessionStarted() {
        gameController.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gameController.setCustomEasyDifficulty(gameMock, 1);
        });
    }

    /**
     * 10) Test: Verifica che il metodo setCustomEasyDifficulty() lanci
     * un'eccezione se si prova ad impostare la difficolta' 'Facile'
     * con un numero di tentativi non valido.
     * <p>
     *  Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetCustomEasyDifficultyIfInvalidValue() {
        assertThrows(InvalidValueException.class, () -> {
            gameController.setCustomEasyDifficulty(gameMock, 0);
        });
    }

    /**
     * 11) Test: Verifica che il metodo setCustomEasyDifficulty() non lanci
     * un'eccezione se si prova ad impostare un numero di tentativi
     * valido.
     * <p>
     *  Atteso: non deve lanciare SessionAlreadyStartedException.
     */
    @Test
    void testSetCustomEasyDifficultyIfValidValue() {
        try {
            gameController.setCustomEasyDifficulty(gameMock, 1);
        } catch (InvalidValueException e) {
            fail("InvalidValueException");
        }
    }

    /**
     * 12) Test: Verifica che il metodo setCustomMediumDifficulty() imposti
     * la difficolta' 'Media' con un numero di tentativi scelto dall'utente
     * prima dell'inizio di una sessione di gioco.
     * <p>
     *  Atteso: non deve lanciare SessionAlreadyStartedException.
     */
    @Test
    void testSetCustomMediumDifficultyIfSessionNotStarted() {
        try {
            gameController.setCustomMediumDifficulty(gameMock, 1);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * 13) Test: Verifica che il metodo setCustomMediumDifficulty() non imposti
     * la difficolta' 'Media' con un numero di tentativi scelto dall'utente
     * durante una sessione di gioco.
     * <p>
     *  Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetCustomMediumDifficultyIfSessionStarted() {
        gameController.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gameController.setCustomMediumDifficulty(gameMock, 1);
        });
    }

    /**
     * 14) Test: Verifica che il metodo setCustomMediumDifficulty() lanci
     * un'eccezione se si prova ad impostare la difficolta' 'Media'
     * con un numero di tentativi non valido.
     * <p>
     *  Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetCustomMediumDifficultyIfInvalidValue() {
        assertThrows(InvalidValueException.class, () -> {
            gameController.setCustomMediumDifficulty(gameMock, 0);
        });
    }

    @Test
    void testSetCustomMediumDifficultyIfValidValue() {
        try {
            gameController.setCustomMediumDifficulty(gameMock, 1);
        } catch (InvalidValueException e) {
            fail("InvalidValueException");
        }
    }

    @Test
    void testGetDifficultyDoesNotReturnNull() {
        assertNotNull(gameController.getDifficulty(gameMock));
    }

    @Test
    void testGetDifficultyReturnCorrectly() {
        gameController.setEasyDifficulty(gameMock);
        assertEquals("Facile", gameController.getDifficulty(gameMock).getNameLevel());
    }

    @Test
    void testStrikeIfSessionNotStarted() {
        assertThrows(SessionNotStartedException.class, () -> {
            gameController.strike(gameMock, new Coordinate(1, 1));
        });
    }

    @Test
    void testStrikeIfSessionStarted() {
        gameController.startSession(gameMock);
        try {
            gameController.strike(gameMock, new Coordinate(1, 1));
        } catch (SessionNotStartedException e) {
            fail("SessionNotStartedException");
        }
    }

    @Test
    void testStrikeIfCellAlreadyMarked() {
        gameController.startSession(gameMock);
        gameController.strike(gameMock, new Coordinate(1, 1));
        assertThrows(CellAlreadyMarkedException.class, () -> {
            gameController.strike(gameMock, new Coordinate(1, 1));
        });
    }

    @Test
    void testStrikeIfCellNotAlreadyMarked() {
        gameController.startSession(gameMock);
        try {
            gameController.strike(gameMock, new Coordinate(1, 1));
        } catch (CellAlreadyMarkedException e) {
            fail("CellAlreadyMarkedException");
        }
    }

    @Test
    void testStrikeIfHitNotOnMap() {
        gameController.startSession(gameMock);
        assertThrows(OutOfMapException.class, () -> {
            gameController.strike(gameMock, new Coordinate(Grid.getSize() + 1, Grid.getSize() + 1));
        });
    }
}
