package it.uniba.app.commandline;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * javadoc momentaneo.
 */
class FlahHandlerTest {
    private FlagHandler fh;

    @BeforeEach
    void setUp() {
        fh = FlagHandler.getInstance();
    }

    @Test
    void testLengthOfArgsTyped() {
        String[] args = {"--help", "-help", "-h"};
        assertFalse(fh.execute(args),
                "ERR [FH:1]: execute() non restituisce false quando più di un"
                + "flag viene passato all'applicazione");
    }

    @Test
    void testRightNumberButIncorrectFlag() {
        String[] args = {"--h"};
        assertFalse(fh.execute(args),
            "ERR [FH:2]: execute() non restituisce false quando viene passato un"
            + "singolo flag ma il flag non è valido");
    }
}
