package it.uniba.app.battleship.controller;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
public class ExitControllerTest {
    @Test
    public void testRequestExit() {
        ExitController.requestExit();
        assertTrue(ExitController.isExitRequested());
    }
}
