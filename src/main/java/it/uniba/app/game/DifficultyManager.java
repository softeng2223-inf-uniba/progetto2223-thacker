package it.uniba.app.game;

// TODO javadoc
public class DifficultyManager {

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

    public static void setEasyLevel() {
        curLevelName = EASY_NAME;
        maxFailable = EASY_MAX_ATTEMPTS;
    }

    public static void setMedLevel() {
        curLevelName = MED_NAME;
        maxFailable = MED_MAX_ATTEMPTS;
    }

    public static void setHardLevel() {
        curLevelName = HARD_NAME;
        maxFailable = HARD_MAX_ATTEMPTS;
    }

    public static String getLevelName() {
        return curLevelName;
    }

    public static int getMaxFailedAttempts() {
        return maxFailable;
    }

}