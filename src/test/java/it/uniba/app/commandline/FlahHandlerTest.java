package it.uniba.app.commandline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    /**
     * 1) Test: verifica che execute() dia il giusto risultato
     * passando più di un flag.
     * <p>
     * Atteso: false.
     * </p>
     */
    @Test
    void testLengthOfArgsTyped() {
        String[] args = {"--help", "-help", "-h" };
        assertFalse(fh.execute(args),
                "ERR [FH:1]: execute() non restituisce false quando più di un"
                + "flag viene passato all'applicazione");
    }

    /**
     * 2) Test: verifica che execute() dia il giusto risultato
     * passando il giusto numero di flag, ma non valido.
     * <p>
     * Atteso: false.
     * </p>
     */
    @Test
    void testRightNumberButIncorrectFlag() {
        String[] args = {"--h" };
        assertFalse(fh.execute(args),
                "ERR [FH:2]: execute() non restituisce false quando viene passato un"
                + "singolo flag ma il flag non è valido");
    }

    /**
     * 3) Test: verifica che execute() dia il giusto risultato
     * passando unicamente il falg valod --help
     * <p>
     * Atteso: true.
     * </p>
     */
    @Test
    void testRightNumberAndCorrectFlagHelp() {
        String[] args = {"--help" };
        assertTrue(fh.execute(args),
                "ERR [FH:3]: execute() non restiusce true quando viene passato il singolo flag valido --help");
    }

    /**
     * 4) Test: verifica che execute() dia il giusto risultato
     * passando unicamente il falg valod -h
     * <p>
     * Atteso: true.
     * </p>
     */
    @Test
    void testRightNumberAndCorrectFlagH() {
        String[] args = {"-h" };
        assertTrue(fh.execute(args),
                "ERR [FH:4]: execute() non restituisce true quando viene passato il singolo flag valido -h");
    }

    /**
     * 5) Test: verifica che execute() dia il giusto risultato
     * passando contemporaneamente i due flag validi --help e -h
     * <p>
     * Atteso: false.
     * </p>
     */
    @Test
    void testCorrectFlagsCombinedTogether() {
        String[] args = {"--help", "-h"};
        assertFalse(fh.execute(args),
            "ERR [FH:5]: execute() non restituisce false quando vengono passati entrambi i flag validi"
            + " --help e -h contemporaneamente");
    }
}
