package it.uniba.app.new_design.battleship.controller;

import java.util.LinkedList;

import it.uniba.app.game.controllers.DifficultyController;
import it.uniba.app.game.entities.Difficulty;
import it.uniba.app.new_design.battleship.entity.Grid;
import it.uniba.app.new_design.battleship.entity.Ship;
import it.uniba.app.game.exceptions.DifficultyNotSetException;
import it.uniba.app.game.exceptions.SessionAlreadyStartedException;
import it.uniba.app.game.exceptions.SessionNotStartedException;

/**
 * Control class <hr>
 *
 * Fornisce servizi per gestire una sessione di gioco (o partita) di <i>Battleship solitaire</i>.
 *
 * TODO espandi.
 */
public final class GameController {
    private static final LinkedList<Ship> SHIPS = getShipSet();

    private static boolean isSessionStarted = false;
    private static boolean isDifficultySet = false;

    private static Difficulty difficulty = new Difficulty();
    private static Grid grid;


    private GameController() { }

    /**
     * Avvia una nuova sessione di gioco.<hr>
     * Esegue tutte le inizializzazioni necessarie per giocare.
     *
     * @throws SessionAlreadyStartedException
     *      Non è possibile avviare un altra sessione se un'altra è in corso.
     * @throws DifficultyNotSetException
     *      Non è possibile avviare una sessione se la difficoltà non è stata impostata.
     */
    public static void startSession()
        throws SessionAlreadyStartedException, DifficultyNotSetException {
            if (isSessionStarted) {
                throw new SessionAlreadyStartedException();
            }

            if (!isDifficultySet) {
                throw new DifficultyNotSetException();
            }

            grid = new Grid();
            GameFiller.randomlyFill(SHIPS, grid);

            isSessionStarted = true;
        }

    /**
     * Termina una sessione di gioco in corso.
     *
     * @throws SessionNotStartedException Non è possibile terminare una sessione se non è in corso.
     */
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
        isDifficultySet = true;
    }

    /**
     * Imposta la difficoltà a Medium.
     */
    public static void setMediumDifficulty() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        DifficultyController.setMedium(difficulty);
        isDifficultySet = true;
    }

    /**
     * Imposta la difficoltà a Hard.
     */
    public static void setHardDifficulty() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        DifficultyController.setHard(difficulty);
        isDifficultySet = true;
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

    /**
     * Fornisce una copia della griglia di gioco in un determinato istante della sessione in corso.
     * Non è possibile ottenere una griglia prima che una sessione di gioco sia iniziata.
     *
     * Attenzione: Da usare solo per scopi di presentazione,
     * operare sulla copia non influenza il corso della sessione.
     *
     * @return griglia della sessione corrente nell'istante corrente
     * @throws SessionNotStartedException
     */
    static Grid getSessionGrid() throws SessionNotStartedException {
        if (!isSessionStarted) {
            throw new SessionNotStartedException();
        }
        return grid.clone();
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
