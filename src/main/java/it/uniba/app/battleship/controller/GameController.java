package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.exception.DifficultyNotSetException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Grid;

/**
 * Control class <hr>
 *
 * Fornisce servizi per gestire una sessione di gioco (o partita) di <i>Battleship solitaire</i>.
 *
 * TODO espandi.
 */
public final class GameController {
    private static GameController instance;

    private GameController() { }
    /**
     * Restituisce l'unica istanza della classe (@code GameController).
     * Se l'oggetto è già stato istanziato, il metodo restituisce
     * la sua istanza, altrimenti ne crea una nuova e la restituisce.
     * @return Oggetto della classe (@code GameController).
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    /**
     * Avvia una nuova sessione di gioco.<hr>
     * Esegue tutte le inizializzazioni necessarie per giocare.
     *
     * @throws SessionAlreadyStartedException
     *      Non è possibile avviare un altra sessione se un'altra è in corso.
     * @throws DifficultyNotSetException
     *      Non è possibile avviare una sessione se la difficoltà non è stata impostata.
     */
    public void startSession(final Game game)
        throws SessionAlreadyStartedException, DifficultyNotSetException {
            if (game.isSessionStarted()) {
                throw new SessionAlreadyStartedException();
            }

            if (!game.isDifficultySet()) {
                throw new DifficultyNotSetException();
            }

            game.startSession();
        }

    /**
     * Termina una sessione di gioco in corso.
     *
     * @throws SessionNotStartedException Non è possibile terminare una sessione se non è in corso.
     */
    public void endSession(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        game.endSession();
    }

    /**
     * Imposta la difficoltà ad Easy.
     */
    public void setEasyDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        DifficultyController.setEasy(diff);

        try {
            game.setDifficulty(diff);
        } catch (CloneNotSupportedException e) { }
    }

    /**
     * Imposta la difficoltà a Medium.
     */
    public void setMediumDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        DifficultyController.setMedium(diff);

        try {
            game.setDifficulty(diff);
        } catch (CloneNotSupportedException e) { }
    }

    /**
     * Imposta la difficoltà a Hard.
     */
    public void setHardDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        DifficultyController.setHard(diff);

        try {
            game.setDifficulty(diff);
        } catch (CloneNotSupportedException e) { }
    }

    /**
     * Fornisce le informazioni relative alla difficoltà impostata in un determinato istante.
     *
     * @return difficoltà selezionata
     * @throws CloneNotSupportedException
     */
    public Difficulty getDifficulty(final Game game) throws CloneNotSupportedException {
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
    public Grid getSessionGrid(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        return game.getSessionGrid();
    }

}
