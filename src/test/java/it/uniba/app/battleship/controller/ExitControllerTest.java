package it.uniba.app.battleship.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Test suite per la classe ExitController.
 */
class ExitControllerTest {
    ExitController instance;
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
    void testGetInstance_ReturnsSameInstance(){
        assertEquals(instance, ExitController.getInstance());
    }
    /**
     *  Verifica che chiamare getInstance() restituisca un'istanza
     *  di ExitController non nulla.
     */
    @Test
    void testGetInstance_ReturnsNotNullInstance(){
        assertTrue(instance != null);
    }
    /**
     *  Verifica che chiamare il metodo requestExit() imposti
     *  correttamente l'attributo requestExit a true.
     */
    @Test
    void testRequestExit(){
        instance.requestExit();
        assertTrue(instance.isExitRequested());
    }
    /**
     * Verifica che chiamare il metodo requestExit() due volte
     * consecutivamente imposti comunque l'attributo requestExit a true.
     */
    @Test
    void testRequestExit_twice(){
        instance.requestExit();
        instance.requestExit();
        assertTrue(instance.isExitRequested());
    }
    /**
     *  Verifica che chiamare il metodo isExitRequested() senza
     *  una richiesta attiva restituisca false.
     */
    @Test
    void testIsExitRequested_NotRequested(){
        assertFalse(instance.isExitRequested());
    }
}
