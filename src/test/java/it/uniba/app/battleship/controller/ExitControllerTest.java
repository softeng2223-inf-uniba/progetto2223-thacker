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
     *  Verifica che chiamare getInstance() più volte restituisca
     *  sempre la stessa instance di ExitController.
     */
    @Test
    void testGetInstanceReturnsSameInstance() {
        assertEquals(instance, ExitController.getInstance(),
                "Il metodo getInstance() non restituisce la stessa istanza di ExitController");
    }
    /**
     *  Verifica che chiamare getInstance() restituisca un'istanza
     *  di ExitController non nulla.
     */
    @Test
    void testGetInstanceReturnsNotNullInstance() {
        assertNotNull(instance,
                "Il metodo getInstance() restituisce un'istanza nulla di ExitController");
    }
    /**
     *  Verifica che chiamare il metodo requestExit() imposti
     *  correttamente l'attributo requestExit a true.
     */
    @Test
    void testRequestExit() {
        instance.requestExit();
        assertTrue(instance.isExitRequested(),
                "Il metodo requestExit() non imposta correttamente il flag isExitRequested a true");
    }
    /**
     * Verifica che chiamare il metodo requestExit() due volte
     * consecutivamente imposti comunque l'attributo requestExit a true.
     */
    @Test
    void testRequestExitTwice() {
        instance.requestExit();
        instance.requestExit();
        assertTrue(instance.isExitRequested(),
                "Il metodo requestExit() chiamato due volte consecutivamente non imposta isExitRequested a true");
    }
    /**
     *  Verifica che chiamare il metodo isExitRequested() senza
     *  una richiesta attiva restituisca false.
     */
    @Test
    void testIsExitRequestedNotRequested() {
        assertFalse(instance.isExitRequested(),
                "Il metodo isExitRequested() restituisce true senza una richiesta attiva di uscita");
    }
}
