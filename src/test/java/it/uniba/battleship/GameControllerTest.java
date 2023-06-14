package it.uniba.battleship;

import it.uniba.app.battleship.GameController;
import it.uniba.battleship.entity.GameMock;

import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Ship;
import it.uniba.app.battleship.entity.Grid;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.InvalidValueException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *  Test suite per la classe GameController.
 */
public class GameControllerTest {
    private GameController gameController;
    private GameMock gameMock;
    private static final int SHIPS_PART = 30;

    /**
     * SetUp della classe GameControllerTest, in cui vengono inizializzati
     * gameController e gameMock.
     */
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
     * 4) Test: verifica che, chiamare setMediumDifficulty() prima
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

    /**
     * Verifica se viene sollevata l'eccezione "SessionAlreadyStartedException"
     * quando si tenta di impostare una dimensione di griglia grande
     * se la sessione di gioco è già stata avviata.
     */
    @Test
    void testLargeGridSizeIfSessionStarted() {
        gameController.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gameController.largeGridSize(gameMock);
        },
        "errore, il metodo non lancia una eccezione di tipo SessionAlreadyStartedExpection");
    }

    /**
     * Verifica che non venga sollevata l'eccezione "SessionAlreadyStartedException"
     * quando si tenta di impostare una dimensione di griglia standard
     * se la sessione di gioco non è ancora stata avviata.
    */
    @Test
    void testStandardGridSizeIfSessionNotStarted() {
        try {
            gameController.standardGridSize(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * Verifica che non venga sollevata l'eccezione "SessionAlreadyStartedException"
     * quando si tenta di impostare una dimensione di griglia standard
     * se la sessione di gioco non è ancora stata avviata.
     */
    @Test
    void testStandardGridSizeIfSessionStarted() {
        gameController.startSession(gameMock);
        assertThrows(SessionAlreadyStartedException.class, () -> {
            gameController.standardGridSize(gameMock);
        },
        "errore, standardGridSize() dovrebbe lanciare una ecezione");
    }

    /**
     * Verifica che non venga sollevata l'eccezione "SessionAlreadyStartedException"
     * quando si tenta di impostare una dimensione di griglia grande
     * se la sessione di gioco non è ancora stata avviata.
     */
    @Test
    void testLargeGridSizeIfSessionNotStarted() {
        try {
            gameController.largeGridSize(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * Verifica se il metodo "getFailedAttempts" restituisce il numero corretto
     *  di tentativi falliti quando la sessione di gioco è stata avviata.
     * Viene effettuato un tentativo di colpo su una griglia di gioco e si verifica
     * se il numero di tentativi falliti restituito è corretto.
     */
    @Test
    void testGetFailedAttemptsIfSessionStarted() {
        gameController.startSession(gameMock);
        Grid grid = new Grid();
        gameMock.setGridMock(grid);
        gameController.strike(gameMock, new Coordinate(0, 0));
        gameController.strike(gameMock, new Coordinate(1, 1));
        try {
            assertEquals(2, gameController.getFailedAttempts(gameMock),
                "errore, i tentativi falliti non vengono aggiornati correttamente");
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }


    /**
     * x) Test: verifica che il metodo getAttempts() restituisca una
     * eccezione di tipo SessionNotStartedException quando una
     * partita non è un corso.
     */
    @Test
    void testGetAttemptsIfSessionNotStarted() {
        assertThrows(SessionNotStartedException.class, () -> {
            gameController.getAttempts(gameMock);
        },
        "errore, getAttempts() non lancia una eccezione di tipo"
        + "SessionNotStartedException quando la sessione non è in corso");
    }

    /**
     * x) Test: verifica che il numero di tentativi falliti ad
     * inizio partita sia zero.
     * <p>
     * Atteso: 0.
     */
    @Test
    void testGetFailedAttemptsIfSessionStartedAndNoAttempts() {
        gameController.startSession(gameMock);
        Grid grid = new Grid();
        gameMock.setGridMock(grid);
        try {
            assertEquals(0, gameController.getFailedAttempts(gameMock),
                "errore, il numero di tentativi falliti ad inizio partita non è zero");
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * x) Test: Verifica che, quando viene effettuato un colpo
     * la coordinata colpita venga inserita nella lista di coordinate
     * che sono state colpite.
     */
    @Test
    void testStrikeAddToListIfCellIsEmpty() {
        gameController.startSession(gameMock);
        Coordinate testCoord = new Coordinate(0, 0);

        try {
            gameController.strike(gameMock, testCoord);
        } catch (CellAlreadyMarkedException e) {
            fail("CellAlreadyMarkedException");
        }

        assertTrue(gameMock.getAttemptsList().contains(testCoord),
                "errore, la lista dei tentativi non contiene tutti i tentativi effettuati");
    }

    /**
     * x) test: Verifica che il metodo strike() aumenti correttamente
     * il numero di navi affondate.
     * <p>
     * Atteso: 1.
     */
    @Test
    void testStrikeIncrementSunkSkipsIfHitSunkShip() {
        gameController.startSession(gameMock);

        Grid testGrid = new Grid();
        Ship testShip = new Ship(0);
        Coordinate firstCoord = new Coordinate(0, 0);
        Coordinate secCoord = new Coordinate(0, 1);

        testGrid.set(firstCoord, testShip);
        testGrid.set(secCoord, testShip);
        gameMock.setGridMock(testGrid);

        int expectedValue = gameMock.getSunkShips() + 1;
        try {
            gameController.strike(gameMock, firstCoord);
            gameController.strike(gameMock, secCoord);
        } catch (CellAlreadyMarkedException e) {
            fail("CellAlreadyMarkedException");
        }

        assertEquals(expectedValue, gameMock.getSunkShips(),
        "errore, il numero di navi affondate non viene incrementato correttamente");
    }

    /**
     * x) Test: verifica che il metodo getAttempts() restituisca una eccezione
     * di tipo SessionNotStartedException quando una sessione non è cominciata.
     * <p>
     * Atteso: eccezione SessionNotStartedException.
     */
    @Test
    void testGetFailedAttemptsIfSessionNotStarted() {
        assertThrows(SessionNotStartedException.class, () -> {
            gameController.getFailedAttempts(gameMock);
        },
        "errore, dovrebbe essere lanciata una eccezione di tipo SessionNotStartedException");
    }

    /**
     * x) Test: verifica che il metodo getAttempts() funzioni
     * correttamente senza lanciare una eccezione di tipo
     * SessionNotStartedException quando una sessione è
     * in corso e sono stati tirati dei colpi.
     */
    @Test
    void testGetAttemptsIfSessionStarted() {
        gameController.startSession(gameMock);
        gameController.strike(gameMock, new Coordinate(0, 0));
        gameController.strike(gameMock, new Coordinate(1, 1));
        try {
            assertEquals(2, gameController.getAttempts(gameMock), "err");
        } catch (SessionAlreadyStartedException e) {
            fail("SessionNotStartedException");
        }
    }

    /**
     * x) Test: verifica che il metodo getAttempts() funzioni
     * correttamente senza lanciare una eccezione di tipo
     * SessionNotStartedException quando una sessione è
     * in corso.
     */
    @Test
    void testGetAttemptsIfSessionStartedAndNoAttempts() {
        gameController.startSession(gameMock);
        try {
            assertEquals(0, gameController.getAttempts(gameMock), "err");
        } catch (SessionAlreadyStartedException e) {
            fail("SessionNotStartedException");
        }
    }

    /**
     * x) Test: verifica che il metodo getSessionGrid() funzioni
     * correttamente senza lanciare una eccezione di tipo
     * SessionNotStartedException quando una sessione è
     * in corso.
     */
    @Test
    void testGetSessionGridIfSessionStarted() {
        gameController.startSession(gameMock);
        try {
            gameController.getSessionGrid(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionNotStartedException");
        }
    }


    /**
     * x) Test: verifica che il metodo getSessionGrid() lanci una eccezione
     * di tipo SessionNotStartedException quando una sessione non è ancora
     * cominciata.
     * <p>
     * Atteso: eccezione SessionNotStartedException.
     */
    @Test
    void testGetSessionGridIfSessionNotStarted() {
        assertThrows(SessionNotStartedException.class, () -> {
            gameController.getSessionGrid(gameMock);
        },
        "errore, il metodo getSessionGrid avrebbe dovuto lanciare una eccezione di tipo SessionNotStartedException");
    }

    /**
     * x) Test: verifica che il metodo extraLargeGrid() lanci
     * una eccezione quando una sessione è già in corso.
     * Atteso: eccezione SessionAlreadyStartedException.
     */
    @Test
    void testExtraLargeGridSizeIfSessionNotStarted() {
        try {
            gameController.extraLargeGridSize(gameMock);
        } catch (SessionAlreadyStartedException e) {
            fail("SessionAlreadyStartedException");
        }
    }

    /**
     * x) Test: Verifica che il metodo randomlyFill() inserisca
     * il giusto numero di navi all'interno della mappa.
     * <p>
     * Atteso: 30
     */
    @Test
    void testRandomlyFillInsertsEveryShipInTheGrid() {
        gameController.startSession(gameMock);
        int expected = SHIPS_PART;
        int actual = 0;
        for (int i = 0; i < Grid.getSize(); i++) {
            for (int j = 0; j < Grid.getSize(); j++) {
                if (gameMock.getSessionGrid().get(new Coordinate(i, j)) != null) {
                    actual += 1;
                }
            }
        }
        assertEquals(expected, actual, "errore, navi non posizionate correttamente");
    }
}
