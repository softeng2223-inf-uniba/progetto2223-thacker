package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.exception.DifficultyNotSetException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 *  Test suite per la classe GameController.
 */
class GameControllerTest {
    private Game game;
    @BeforeEach
    void setUp() {
        game = new Game();
    }
    /**
     *  1) Test: Verifica che il metodo startSession() avvii
     *  correttamente una sessione di gioco con difficoltà impostata
     *  come "Facile".
     * <p>
     *  Atteso: true
     */
    @Test
    void testStartSessionEasy() {
        try {
            GameController.setEasyDifficulty(game);
            GameController.startSession(game);
            assertTrue(game.isSessionStarted(),
                    "errore [GC:1] : startSession() non ha avviato la sessione di gioco");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:1]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        }
    }
    /**
     * 2) Test: Verifica che il metodo startSession() avvii
     * correttamente una sessione di gioco con difficoltà impostata
     * come "Media".
     * <p>
     * Atteso: true
     */
    @Test
    void testStartSessionMedium() {
        try {
            GameController.setMediumDifficulty(game);
            GameController.startSession(game);
            assertTrue(game.isSessionStarted(),
                    "errore [GC:2] : startSession() non ha avviato la sessione di gioco");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:2]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        }
    }
    /**
     * 3) Test: Verifica che il metodo startSession() avvii
     * correttamente una sessione di gioco con difficoltà impostata
     * come "Difficile".
     * <p>
     * Atteso: true
     */
    @Test
    void testStartSessionHard() {
        try {
            GameController.setHardDifficulty(game);
            GameController.startSession(game);
            assertTrue(game.isSessionStarted(),
                    "errore [GC:3] : startSession() non ha avviato la sessione di gioco");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:3]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        }
    }
    /**
     * 4) Test: Verifica che il metodo startSession() avvii
     * correttamente una sessione di gioco senza difficoltà impostata.
     * <p>
     * Atteso: true
     */
    @Test
    void testStartSessioneDefault() {
        try {
            GameController.startSession(game);
            assertTrue(game.isSessionStarted(),
                    "errore [GC:4] : startSession() non ha avviato la sessione di gioco");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:4]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        }
    }
    /**
     * 5) Test: Verifica che il metodo startSession() imposti
     * correttamente la difficoltà di gioco come "Facile" nel caso
     * in cui non sia stata impostata alcuna difficoltà.
     * <p>
     * Atteso: true
     */
    @Test
    void testStartSessionDefaultIsEasy() {
        try {
            GameController.startSession(game);
            Difficulty diff = game.getDifficulty();
            diff.setLevel("Facile");
            assertEquals(game.getDifficulty().getLevel(), diff.getLevel(),
                    "errore [GC:5] : startSession() non ha impostato la difficoltà di gioco di default = \"Facile\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:5]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:5]: " + e.getMessage());
        }
    }
    /**
     * 6) Test: Verifica che il metodo startSession() generi
     * correttamente un'eccezione di tipo SessionAlreadyStartedException
     * nel caso in cui una sessione di gioco sia già stata avviata.
     * <p>
     * Atteso: SessionAlreadyStartedException
     */
    @Test
    void testStartSessionAlreadyStarted() throws SessionAlreadyStartedException {
        GameController.startSession(game);
        assertThrows(SessionAlreadyStartedException.class, () -> GameController.startSession(game),
                "errore [GC:6] : startSession() non ha generato l'eccezione SessionAlreadyStartedException");
    }
    /**
     * 7) Test: Verifica che il metodo endSession() termini
     * correttamente una sessione di gioco avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testEndSessionAlreadyStarted() {
        try {
            GameController.startSession(game);
            GameController.endSession(game);
            assertFalse(game.isSessionStarted(),
                    "errore [GC:7] : endSession() non ha terminato la sessione di gioco");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:7]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        } catch (SessionNotStartedException e) {
            fail("errore [GC:7]: Non è possibile terminare una sessione di gioco se non ne è stata avviata una");
        }
    }
    /**
     * 8) Test: Verifica che il metodo endSession() generi
     * correttamente un'eccezione di tipo SessionNotStartedException
     * nel caso in cui una sessione di gioco non sia stata avviata.
     * <p>
     * Atteso: SessionNotStartedException
     */
    @Test
    void testEndSessionNotStarted() {
        assertThrows(SessionNotStartedException.class, () -> GameController.endSession(game),
                "errore [GC:8] : endSession() non ha generato l'eccezione SessionNotStartedException");
    }
    /**
     * 9) Test: Verifica che il metodo setEasyDifficulty() imposti
     * correttamente la difficoltà di gioco come "Facile" quando
     * una partita non è stata ancora avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testSetEasyDifficultyNotStarted() {
        try {
            GameController.setEasyDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Facile",
                    "errore [GC:9] : setEasyDifficulty() non ha impostato la difficoltà di gioco come \"Facile\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:9]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:9]: " + e.getMessage());
        }
    }
    /**
     * 10) Test: Verifica che il metodo setEasyDifficulty() generi
     * correttamente un'eccezione di tipo SessionAlreadyStartedException
     * nel caso in cui una sessione di gioco sia già stata avviata.
     * <p>
     * Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetEasyDifficultyStarted() {
        try {
            GameController.startSession(game);
            assertThrows(SessionAlreadyStartedException.class, () -> GameController.setEasyDifficulty(game),
                    "errore [GC:10] : setEasyDifficulty() non ha generato l'eccezione SessionAlreadyStartedException");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:10]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        }
    }
    /**
     * 11) Test: Verifica che il metodo setEasyDifficulty() imposti
     * correttamente la difficoltà di gioco come "Facile" anche quando
     * una difficoltà è stata già impostata dall'utente.
     * <p>
     * Atteso: true
     */
    @Test
    void testSetEasyDifficultyAlreadySet() {
        try {
            GameController.setMediumDifficulty(game);
            GameController.setEasyDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Facile",
                    "errore [GC:11] : setEasyDifficulty() non ha impostato la difficoltà di gioco come \"Facile\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:11]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:11]: " + e.getMessage());
        }
    }
    /**
     * 12) Test: Verifica che il metodo setMediumDifficulty() imposti
     * correttamente la difficoltà di gioco come "Medio" quando
     * una partita non è stata ancora avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testSetMediumDifficultyNotStarted() {
        try {
            GameController.setMediumDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Medio",
                    "errore [GC:12] : setMediumDifficulty() non ha impostato la difficoltà di gioco come \"Medio\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:12]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:12]: " + e.getMessage());
        }
    }
    /**
     * 13) Test: Verifica che il metodo setMediumDifficulty() generi
     * correttamente un'eccezione di tipo SessionAlreadyStartedException
     * nel caso in cui una sessione di gioco sia già stata avviata.
     * <p>
     * Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetMediumDifficultyStarted() {
        try {
            GameController.startSession(game);
            assertThrows(SessionAlreadyStartedException.class, () -> GameController.setMediumDifficulty(game),
                    "errore [GC:13]: setMediumDifficulty() non ha generato l'eccezione SessionAlreadyStartedException");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:13]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        }
    }
    /**
     * 14) Test: Verifica che il metodo setMediumDifficulty() imposti
     * correttamente la difficoltà di gioco come "Medio" anche quando
     * una difficoltà è stata già impostata dall'utente.
     * <p>
     * Atteso: true
     */
    @Test
    void testSetMediumDifficultyAlreadySet() {
        try {
            GameController.setEasyDifficulty(game);
            GameController.setMediumDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Medio",
                    "errore [GC:14] : setMediumDifficulty() non ha impostato la difficoltà di gioco come \"Medio\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:14]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:14]: " + e.getMessage());
        }
    }
    /**
     * 15) Test: Verifica che il metodo setHardDifficulty() imposti
     * correttamente la difficoltà di gioco come "Medio" quando
     * una partita non è stata ancora avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testSetHardDifficultyNotStarted() {
        try {
            GameController.setHardDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Difficile",
                    "errore [GC:15] : setHardDifficulty() non ha impostato la difficoltà di gioco come \"Difficile\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:15]: Non è possibile avviare una sessione di gioco se ne è già stata avviata una");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:15]: " + e.getMessage());
        }
    }
    /**
     * 16) Test: Verifica che il metodo setHardDifficulty() generi
     * correttamente un'eccezione di tipo SessionAlreadyStartedException
     * nel caso in cui una sessione di gioco sia già stata avviata.
     * <p>
     * Atteso: SessionAlreadyStartedException
     */
    @Test
    void testSetHardDifficultyStarted() {
        try {
            GameController.startSession(game);
            assertThrows(SessionAlreadyStartedException.class, () -> GameController.setHardDifficulty(game),
                    "errore [GC:16]: setHardDifficulty() non ha generato l'eccezione SessionAlreadyStartedException");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:16]: setHardDifficulty() non ha generato l'eccezione SessionAlreadyStartedException");
        }
    }
    /**
     * 17) Test: Verifica che il metodo setHardDifficulty() imposti
     * correttamente la difficoltà di gioco come "Difficile" anche quando
     * una difficoltà è stata già impostata dall'utente.
     * <p>
     * Atteso: true
     */
    @Test
    void testSetHardDifficultyAlreadySet() {
        try {
            GameController.setEasyDifficulty(game);
            GameController.setHardDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Difficile",
                    "errore [GC:17] : setHardDifficulty() non ha impostato la difficoltà di gioco come \"Difficile\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:17]: setHardDifficulty() non ha impostato la difficoltà di gioco come \"Difficile\"");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:17]: " + e.getMessage());
        }
    }
    /**
     * 18) Test: Verifica che il metodo getDifficulty() restituisca
     * correttamente la difficoltà di gioco impostata dall'utente (Facile)
     * quando una partita non è stata ancora avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testGetDifficultyEasy() {
        try {
            GameController.setEasyDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Facile",
                    "errore [GC:18] : setEasyDifficulty() non ha impostato la difficoltà di gioco come \"Facile\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:18]: setEasyDifficulty() non ha impostato la difficoltà di gioco come \"Facile\"");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:18]: " + e.getMessage());
        }
    }
    /**
     * 19) Test: Verifica che il metodo getDifficulty() restituisca
     * correttamente la difficoltà di gioco impostata dall'utente (Medio)
     * quando una partita non è stata ancora avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testGetDifficultyMedium() {
        try {
            GameController.setMediumDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Medio",
                    "errore [GC:19] : setMediumDifficulty() non ha impostato la difficoltà di gioco come \"Medio\"");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:19]: setMediumDifficulty() non ha impostato la difficoltà di gioco come \"Medio\"");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:19]: " + e.getMessage());
        }
    }
    /**
     * 20) Test: Verifica che il metodo getDifficulty() restituisca
     * correttamente la difficoltà di gioco impostata dall'utente (Difficile)
     * quando una partita non è stata ancora avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testGetDifficultyHard() {
        try {
            GameController.setHardDifficulty(game);
            assertEquals(game.getDifficulty().getLevel(), "Difficile",
                    "errore [GC:20]: setHardDifficulty() non ha impostato la difficolta' di gioco come Difficile");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:20]: non e' stato possibile impostare la difficolta' di gioco come \"Difficile\"");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:20]: " + e.getMessage());
        }
    }
    /**
     * 21) Test: Verifica che il metodo getDifficulty() generi
     * correttamente un'eccezione di tipo DifficultyNotSetException
     * nel caso in cui una difficoltà di gioco non sia stata ancora impostata.
     * <p>
     * Atteso: DifficultyNotSetException
     */
    @Test
    void testGetDifficultyNotSet() {
        assertThrows(DifficultyNotSetException.class, () -> GameController.getDifficulty(game),
                "errore [GC:21]: getDifficulty() non ha generato l'eccezione DifficultyNotSetException");
    }
    /**
     * 22) Test: Verifica che il metodo getDifficulty() restituisce
     * correttamente la difficoltà di gioca impostata dall'utente (Difficile)
     * durante una sessione di gioco avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testGetDifficultyStarted() {
        try {
            GameController.setHardDifficulty(game);
            GameController.startSession(game);
            assertEquals(game.getDifficulty().getLevel(), "Difficile",
                    "errore [GC:22]: getDifficulty() non ha restituito la difficolta' di gioco impostata dall'utente");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:22]: getDifficulty() non ha restituito la difficolta' di gioco impostata dall'utente");
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:22]: " + e.getMessage());
        }
    }
    /**
     * 23) Test: Verifica che il metodo getSession() restituisca
     * correttamente la griglia di gioco quando una partita è avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testGetSessionGridStarted() {
        try {
            GameController.startSession(game);
            assertNotNull(GameController.getSessionGrid(game),
                    "errore [GC:23]: getSessionGrid() non ha restituito la griglia di gioco");
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:23]: Non puoi avviae una sessione di gioco se una partita e' gia' stata avviata");
        } catch (SessionNotStartedException e) {
            fail("errore [GC:23]: Non puoi ottenere la griglia di gioco se una partita non e' stata avviata");
        }
    }
    /**
     * 24) Test: Verifica che il metodo getSession() generi
     * correttamente un'eccezione di tipo SessionNotStartedException
     * nel caso in cui una partita non sia stata ancora avviata.
     * <p>
     * Atteso: SessionNotStartedException
     */
    @Test
    void testGetSessionGridNotStarted() {
        assertThrows(SessionNotStartedException.class, () -> GameController.getSessionGrid(game),
                "errore [GC:24]: getSessionGrid() non ha generato l'eccezione SessionNotStartedException");
    }
}

