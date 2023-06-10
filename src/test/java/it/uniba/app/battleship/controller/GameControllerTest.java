package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.exception.DifficultyNotSetException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Test suite per la classe GameController.
 */
class GameControllerTest {
    Game game;
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
        try{
            GameController.setEasyDifficulty(game);
            GameController.startSession(game);
            assertTrue(game.isSessionStarted());
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:1]: " + e.getMessage());
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
        try{
            GameController.setMediumDifficulty(game);
            GameController.startSession(game);
            assertTrue(game.isSessionStarted());
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:2]: " + e.getMessage());
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
        try{
            GameController.setHardDifficulty(game);
            GameController.startSession(game);
            assertTrue(game.isSessionStarted());
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:3]: " + e.getMessage());
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
        try{
            GameController.startSession(game);
            assertTrue(game.isSessionStarted());
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:4]: " + e.getMessage());
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
        try{
            GameController.startSession(game);
            Difficulty diff = game.getDifficulty();
            diff.setLevel("Facile");
            assertEquals(game.getDifficulty().getLevel(), diff.getLevel());
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:5.1]: " + e.getMessage());
        } catch (CloneNotSupportedException e) {
            fail("errore [GC:5.2]: " + e.getMessage());
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
        assertThrows(SessionAlreadyStartedException.class, () -> GameController.startSession(game));
    }
    /**
     * 7) Test: Verifica che il metodo endSession() termini
     * correttamente una sessione di gioco avviata.
     * <p>
     * Atteso: true
     */
    @Test
    void testEndSessionAlreadyStarted() {
        try{
            GameController.startSession(game);
            GameController.endSession(game);
            assertFalse(game.isSessionStarted());
        } catch (SessionAlreadyStartedException e) {
            fail("errore [GC:7.1]: " + e.getMessage());
        } catch (SessionNotStartedException e) {
            fail("errore [GC:7.2]: " + e.getMessage());
        }
    }
    /**
     * 8) Test: Verifica che il metodo endSession() generi
     * correttamente un'eccezione di tipo SessionNotStartedException
     * nel caso in cui una sessione di gioco non sia stata avviata.
     * <p>
     * Atteso: SessionNotStartedException
     */
}

