package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.OutOfMapException;
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
    public static void startSession(final Game game)
        throws SessionAlreadyStartedException {
            if (game.isSessionStarted()) {
                throw new SessionAlreadyStartedException();
            }

            if (!game.isDifficultySet()) {
                setEasyDifficulty(game);
            }

            game.startSession();
        }

    /**
     * Termina una sessione di gioco in corso.
     *
     * @throws SessionNotStartedException Non è possibile terminare una sessione se non è in corso.
     */
    public static void endSession(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        game.endSession();
    }

    /**
     * Gestisce la chiamata di strike, se una nave viene affondata, incrementa
     * il numero di navi affondate in Game, se invece il colpo va a vuoto,
     * viene incrementato il valore di tentativi falliti di Game.
     * @param game oggetto che conserva i parametri di gioco
     * @param command contiene le coordinate in formato stringa
     * @throws SessionNotStartedException non è possibile lanciare il colpo se una
     * partita non è cominciata
     * @throws CellAlreadyMarkedException non è possibile colpire una cella
     * già colpita in precedenza
     * @throws OutOfMapException non è possibile lanciare il colpo fuori dalla
     * portata della mappa
     */
    public static void strike(final Game game, final String command)
        throws SessionNotStartedException, CellAlreadyMarkedException,
        OutOfMapException {
            if (!game.isSessionStarted()) {
                throw new SessionNotStartedException();
            }

            int result = StrikeController.strike(command, game.getSessionGrid());
            if (result == 1) {
                game.setSunkShips(game.getSunkShips() + 1);
            }
            if (result == -1) {
                game.setFailedAttempt(game.getFailedAttempts() + 1);
            }
        }

    /**
     * Imposta la difficoltà ad Easy.
     */
    public static void setEasyDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        DifficultyController.setDefaultEasy(diff);

        try {
            game.setDifficulty(diff);
        } catch (CloneNotSupportedException e) { }
    }

    /**
     * Imposta la difficoltà a Medium.
     */
    public static void setMediumDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        DifficultyController.setDefaultMedium(diff);

        try {
            game.setDifficulty(diff);
        } catch (CloneNotSupportedException e) { }
    }

    /**
     * Imposta la difficoltà a Hard.
     */
    public static void setHardDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        DifficultyController.setDefaultHard(diff);

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
    public static Difficulty getDifficulty(final Game game) throws CloneNotSupportedException {
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
    public static Grid getSessionGrid(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        return game.getSessionGrid();
    }

    /**
     * Fornisce il numero di tentativi effettuati fino al momento corrente di una sessione di gioco in corso.
     *
     * @param game la sessione
     * @return numero di tentativi totali all'istante corrente
     * @throws SessionNotStartedException
     *      non è possibile ottenere questa informazione per una sessione non in corso.
     */
    public static int getAttempts(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        return game.getAttempts();
    }

    /**
     * Fornisce il numero di tentativi effettuati e falliti fino al momento corrente di una sessione di gioco in corso.
     * Per fallito si intende un tentativo che non ha colpito alcuna nave.
     *
     * @param game la sessione
     * @return numero di tentativi falliti all'istante corrente
     * @throws SessionNotStartedException
     *      non è possibile ottenere questa informazione per una sessione non in corso.
     */
    public static int getFailedAttempts(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        return game.getAttempts();
    }

}
