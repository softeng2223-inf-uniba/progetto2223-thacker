package it.uniba.app.game;

/**
 * Modella il gestore della difficoltà che condiziona la singola partita.
 *
 * Attualmente i livelli impostabili sono tre: <i>Easy, Medium, Hard</i>;
 * Il livello predefinito è <i>Easy</i>.
 * Un livello può essere impostato invocando il rispettivo metodo:
 *
 * <pre>
 * setEasyLevel();   //imposta Easy
 * setMedLevel();    //imposta Medium
 * setHardLevel();   //imposta Hard
 * </pre>
 */
public final class DifficultyManager {

    /* CONSTANTS */
    private static final String EASY_NAME = "Facile";
    private static final String MED_NAME = "Medio";
    private static final String HARD_NAME = "Difficile";

    private static final int EASY_MAX_ATTEMPTS = 50;
    private static final int MED_MAX_ATTEMPTS  = 30;
    private static final int HARD_MAX_ATTEMPTS = 10;

    /* VARIABLES */
    private static String curLevelName;
    private static int maxFailable;

    /* METHODS */

    private DifficultyManager() {
        setEasyLevel();
    }

    /**
     * Imposta il livello Easy (Facile).
    */
    public static void setEasyLevel() {
        curLevelName = EASY_NAME;
        maxFailable = EASY_MAX_ATTEMPTS;
    }

    /**
     * Imposta il livello Medium (Medio).
     */
    public static void setMedLevel() {
        curLevelName = MED_NAME;
        maxFailable = MED_MAX_ATTEMPTS;
    }

    /**
     * Imposta il livello Hard (Difficile).
     */
    public static void setHardLevel() {
        curLevelName = HARD_NAME;
        maxFailable = HARD_MAX_ATTEMPTS;
    }

    /**
     * Fornisce il nome del livello di difficoltà impostato correntemente.
     *
     * @return nome del livello corrente.
     */
    public static String getLevelName() {
        return curLevelName;
    }

    /**
     * Fornisce il numero massimo di tentativi falliti,
     * basato sul livello di difficoltà corrente.
     *
     * @return numero massimo di tentativi falliti.
     */
    public static int getMaxFailedAttempts() {
        return maxFailable;
    }

}
