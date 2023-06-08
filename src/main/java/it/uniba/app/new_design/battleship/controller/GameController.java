package it.uniba.app.new_design.battleship.controller;

import java.util.LinkedList;

import it.uniba.app.game.controllers.DifficultyController;
import it.uniba.app.game.entities.Difficulty;
import it.uniba.app.new_design.battleship.entity.Grid;
import it.uniba.app.new_design.battleship.entity.Ship;
import it.uniba.app.game.exceptions.SessionAlreadyStartedException;
import it.uniba.app.game.exceptions.SessionNotStartedException;

public class GameController {
    private static final LinkedList<Ship> SHIPS = getShipSet();

    private static boolean isSessionStarted = false;

    private static Difficulty difficulty = new Difficulty();
    private static Grid grid;


    private GameController() { }

    public static void startSession() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        DifficultyController.setEasy(difficulty);
        
        grid = new Grid();
        GameFiller.randomlyFill(SHIPS, grid);

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

    /* === PRIVATE METHODS === */

      /**
     * Restituisce una lista di navi contenente, per ogni tipo di nave, tante istanze
     * quanti sono numero di esemplari previsti per tale tipo.
     *
     * @return lista di navi
     */
    //TODO improve
    private static LinkedList<Ship> getShipSet() {
        LinkedList<Ship> shipsList = new LinkedList<Ship>();

        for (int tId = 0; tId < Ship.getNumberOfTypes(); tId++) {
            for (int i = 0; i < Ship.getMaxInstances(tId); i++) {
                shipsList.add(new Ship(tId));
            }
        }
        return shipsList;
    }

}
