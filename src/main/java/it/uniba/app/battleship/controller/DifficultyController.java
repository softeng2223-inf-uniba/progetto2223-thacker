package it.uniba.app.battleship.controller;
import it.uniba.app.battleship.entity.Difficulty;
/**
 * La classe DifficultyController gestisce le operazioni
 * relative alle difficoltà di gioco.
 */
public final class DifficultyController {
    private static final String EASY_NAME = "Facile";
    private static final String MEDIUM_NAME = "Medio";
    private static final String HARD_NAME = "Difficile";
    private static final int DEFAULT_EASY_MAX_FAILED_ATTEMPTS = 50;
    private static final int DEFAULT_MEDIUM_MAX_FAILED_ATTEMPTS = 30;
    private static final int DEFAULT_HARD_MAX_FAILED_ATTEMPTS = 10;
    private static int easyMaxFailedAttempts = DEFAULT_EASY_MAX_FAILED_ATTEMPTS;
    private static int MediumMaxFailedAttempts = DEFAULT_MEDIUM_MAX_FAILED_ATTEMPTS;
    private static int HardMaxFailedAttempts = DEFAULT_HARD_MAX_FAILED_ATTEMPTS;
    private DifficultyController() { }
    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Facile" e
     * il numero massimo di tentativi falliti a EASY_MAX_FAILED_ATTEMPTS.
     *
     * @param difficulty l'oggetto Difficulty da modificare.
     */
    public static void setDefaultEasy(final Difficulty difficulty) {
        difficulty.setNameLevel(EASY_NAME);
        difficulty.setMaxFailedAttempts(easyMaxFailedAttempts);
    }
    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Medio" e
     * il numero massimo di tentativi falliti come MEDIUM_MAX_FAILED_ATTEMPTS.
     *
     * @param difficulty l'oggetto Difficulty da modificare.
     */
    public static void setDefaultMedium(final Difficulty difficulty) {
        difficulty.setNameLevel(MEDIUM_NAME);
        difficulty.setMaxFailedAttempts(MediumMaxFailedAttempts);
    }
    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Difficile" e
     * il numero massimo di tentativi falliti come HARD_MAX_FAILED_ATTEMPTS.
     *
     * @param difficulty l'oggetto Difficulty da modificare.
     */
    public static void setDefaultHard(final Difficulty difficulty) {
        difficulty.setNameLevel(HARD_NAME);
        difficulty.setMaxFailedAttempts(HardMaxFailedAttempts);
    }

    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Facile" e
     * il numero massimo di tentativi falliti a maxFailedAttempts.
     *
     * @param maxFailedAttempts il numero massimo di tentativi falliti.
     */
    public static void setCustomEasy(final int maxFailedAttempts) {
        easyMaxFailedAttempts = maxFailedAttempts;
    }

    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Medio" e
     * il numero massimo di tentativi falliti a maxFailedAttempts.
     *
     * @param maxFailedAttempts il numero massimo di tentativi falliti.
     */
    public static void setCustomMedium(final int maxFailedAttempts) {
        MediumMaxFailedAttempts = maxFailedAttempts;
    }

    /**
     * Imposta la difficoltà dell'oggetto Difficulty come "Difficile" e
     * il numero massimo di tentativi falliti a maxFailedAttempts.
     * 
     * @param maxFailedAttempts il numero massimo di tentativi falliti.
     */
    public static void setCustomHard(final int maxFailedAttempts) {
        HardMaxFailedAttempts = maxFailedAttempts;
    }
}
