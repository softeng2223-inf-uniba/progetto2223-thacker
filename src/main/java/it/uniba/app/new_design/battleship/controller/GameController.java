package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.game.controllers.DifficultyController;
import it.uniba.app.game.entities.Difficulty;
import it.uniba.app.new_design.battleship.entity.Game;
import it.uniba.app.new_design.battleship.entity.Grid;
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
    private static Game game = new Game();

    private static boolean isSessionStarted = false;
    private static boolean isDifficultySet = false;

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

            game.startSession();
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
        Difficulty diff = new Difficulty();
        DifficultyController.setEasy(diff);

        try {
            game.setDifficulty(diff);
        } catch (CloneNotSupportedException e) { }

        isDifficultySet = true;
    }

    /**
     * Imposta la difficoltà a Medium.
     */
    public static void setMediumDifficulty() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        DifficultyController.setMedium(diff);

        try {
            game.setDifficulty(diff);
        } catch (CloneNotSupportedException e) { }

        isDifficultySet = true;
    }

    /**
     * Imposta la difficoltà a Hard.
     */
    public static void setHardDifficulty() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        DifficultyController.setHard(diff);

        try {
            game.setDifficulty(diff);
        } catch (CloneNotSupportedException e) { }

        isDifficultySet = true;
    }

    /**
     * Fornisce le informazioni relative alla difficoltà impostata in un determinato istante.
     *
     * @return difficoltà selezionata
     * @throws CloneNotSupportedException
     */
    static Difficulty getDifficulty() throws CloneNotSupportedException {
        return game.getDifficulty();
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
        return game.getSessionGrid();
    }

}
