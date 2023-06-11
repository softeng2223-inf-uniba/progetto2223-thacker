package it.uniba.app.battleship.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *  Test suite per la classe ExitController.
 */
class ExitControllerTest {
    private ExitController instance;
    @BeforeEach
    void setUp() throws Exception {
        Method getInstanceMethod = ExitController.class.getDeclaredMethod("getInstance");
        getInstanceMethod.setAccessible(true);
        instance = (ExitController) getInstanceMethod.invoke(null);
    }
    /**
     *  1) Test: verifica che chiamare getInstance() restituisca
     *  sempre la stessa istanza di ExitController.
     *  <p>
     *  Atteso: true.
     */
    @Test
    void testGetInstanceReturnsSameInstance() {
        assertEquals(instance, ExitController.getInstance(),
                "err [EX:1]: Il metodo getInstance() non restituisce la stessa istanza di ExitController");
    }
    /**
     *  2) Test: verifica che chiamare getInstance() restituisca
     *  un'istanza non nulla di ExitController.
     *  <p>
     *  Atteso: true.
     */
    @Test
    void testGetInstanceReturnsNotNullInstance() {
        assertNotNull(instance,
                "err [EX:2]: Il metodo getInstance() restituisce un'istanza nulla di ExitController");
    }
    /**
     *  3) Test: Verifica che chiamare il metodo requestExit() imposti
     *  correttamente l'attributo requestExit a true.
     *  <p>
     *  Atteso: true.
     */
    @Test
    void testRequestExit() {
        instance.requestExit();
        assertTrue(instance.isExitRequested(),
                "err [EX:3]: Il metodo requestExit() non imposta correttamente il flag isExitRequested a true");
    }
    /**
     * 4) Test: verifica che chiamare il metodo requestExit() due
     * volte consecutivamente imposti correttamente l'attributo
     * requestExit a true.
     * <p>
     * Atteso: true.
     */
    @Test
    void testRequestExitTwice() {
        instance.requestExit();
        instance.requestExit();
        assertTrue(instance.isExitRequested(),
                "err [EX:4]: Il metodo requestExit() chiamato due volte non imposta isExitRequested a true");
    }
    /**
     *  5) Test: Verifica che chiamare il metodo isExitRequested() senza
     *  una richiesta attiva restituisca false.
     *  <p>
     *  Atteso: true.
     */
    @Test
    void testIsNotExitRequestIfNotRequested() {
        assertFalse(instance.isExitRequested(),
                "err [EX:5]: Il metodo isExitRequested() restituisce true senza una richiesta attiva di uscita");
    }
    /**
     * 6) Test: Verifica che il metodo getInstance() non alteri
     * lo stato interno di ExitController.
     * <p>
     * Atteso: false.
     */
    @Test
    void testGetInstanceDoesNotAlterState() {
        assertFalse(ExitController.getInstance().isExitRequested(),
                "err [EX:6]: Il metodo getInstance() altera lo stato interno di ExitController");
    }
    /**
     * 7) Test: Verifica che il metodo getInstance() dopo aver
     * chiamato requestExit() restituisca un'istanza di ExitController
     * con requestedExit impostato a true.
     */
    @Test
    void testGetInstanceReturnsInstanceWithRequestedExitTrue() {
        instance.requestExit();
        assertTrue(ExitController.getInstance().isExitRequested(),
                "err [EX:7]: Il metodo getInstance() restituisce un'istanza di ExitController "
                        + "con requestedExit impostato a false");
    }
}
