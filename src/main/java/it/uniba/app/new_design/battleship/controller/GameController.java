package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.game.controllers.DifficultyController;
import it.uniba.app.game.entities.Difficulty;
import it.uniba.app.game.exceptions.SessionAlreadyStartedException;
import it.uniba.app.game.exceptions.SessionNotStartedException;

public class GameController {
    private static boolean isSessionStarted = false;

    private static Difficulty difficulty = new Difficulty();

    private GameController() { }

    public static void startSession() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        DifficultyController.setEasy(difficulty);

        isSessionStarted = true;
    }

    public static void endSession() throws SessionNotStartedException {
        if (!isSessionStarted) {
            throw new SessionNotStartedException();
        }
        isSessionStarted = false;
    }

    /**
     * Imposta la difficoltà ad Easy.
     */
    public static void setEasyDifficulty() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        DifficultyController.setEasy(difficulty);
    }

    /**
     * Imposta la difficoltà a Medium.
     */
    public static void setMediumDifficulty() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        DifficultyController.setMedium(difficulty);
    }

    /**
     * Imposta la difficoltà a Hard.
     */
    public static void setHardDifficulty() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        DifficultyController.setHard(difficulty);
    }

    /**
     * Fornisce le informazioni relative alla difficoltà impostata in un determinato istante.
     *
     * @return difficoltà selezionata
     * @throws CloneNotSupportedException
     */
    static Difficulty getDifficulty() throws CloneNotSupportedException {
        return difficulty.clone();
    }


}
