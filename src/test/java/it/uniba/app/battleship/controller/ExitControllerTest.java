package it.uniba.app.battleship.controller;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *  Test suite per la classe ExitController.
 */
class ExitControllerTest {
    /**
     *   Verifica il corretto funzionamento del
     *   metodo requestExit() della classe ExitController.
     */
    @Test
    void testRequestExit() {
        ExitController.requestExit();
        assertTrue(ExitController.isExitRequested(), "ExitController.requestExit() non funziona correttamente");
    }
}
