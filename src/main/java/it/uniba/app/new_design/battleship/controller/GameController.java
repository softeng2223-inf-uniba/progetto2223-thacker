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
     * Fornisce le informazioni relative alla difficoltà impostata in un determinato istante.
     *
     * @return difficoltà selezionata
     * @throws CloneNotSupportedException
     */
    public static Difficulty getDifficulty() throws CloneNotSupportedException {
        return difficulty.clone();
    }
}
