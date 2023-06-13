package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.entity.Ship;
import it.uniba.app.battleship.entity.Time;

/**
 * {@code <<Control>>}<hr>
 * Fornisce servizi per gestire una sessione di gioco.
 */
public final class GameController {

    /* COSTANTI PER I LIVELLI DI DIFFICOLTÀ */
    private static final String EASY_NAME   = "Facile";
    private static final String MEDIUM_NAME = "Medio";
    private static final String HARD_NAME   = "Difficile";
    private static final String CUSTOM_NAME = "Difficoltà personalizzata";

    private static final int DEFAULT_EASY_MAX_FAILED_ATTEMPTS   = 50;
    private static final int DEFAULT_MEDIUM_MAX_FAILED_ATTEMPTS = 30;
    private static final int DEFAULT_HARD_MAX_FAILED_ATTEMPTS   = 10;

    private int easyMaxFailedAttempts   = DEFAULT_EASY_MAX_FAILED_ATTEMPTS;
    private int mediumMaxFailedAttempts = DEFAULT_MEDIUM_MAX_FAILED_ATTEMPTS;
    private int hardMaxFailedAttempts   = DEFAULT_HARD_MAX_FAILED_ATTEMPTS;

    private static class Holder {
        private static final GameController INSTANCE = new GameController();
    }

    private GameController() { };

    public static GameController getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Avvia una nuova sessione di gioco.<hr>
     * Esegue tutte le inizializzazioni necessarie per giocare.
     *
     * @throws SessionAlreadyStartedException
     *      Non è possibile avviare un altra sessione se un'altra è in corso.
    */
    public void startSession(final Game game)
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
    public void endSession(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        game.endSession();
    }
    /**
     * Permette di settare il numero massimo di
     * minuti a disposizione per giocare.
     * @param game oggetto che conserva i dati di gioco.
     * @param value intero che contiene il numero di minuti
     * a disposizione per giocare.
     * @throws SessionAlreadyStartedException
     */
    public void setTime(final Game game, final int value) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }

        Time time = new Time();
        TimeController.getInstance().setTimeLimit(time, value);
        game.setTime(time);
    }

    /**
     * Gestisce la chiamata di strike, se una nave viene affondata, incrementa
     * il numero di navi affondate in Game, se invece il colpo va a vuoto,
     * viene incrementato il valore di tentativi falliti di Game.
     * @param game oggetto che conserva i parametri di gioco
     * @param coord coordinate
     * @throws SessionNotStartedException non è possibile lanciare il colpo se una
     * partita non è cominciata
     * @throws CellAlreadyMarkedException non è possibile colpire una cella
     * già colpita in precedenza
     * @throws OutOfMapException non è possibile lanciare il colpo fuori dalla
     * portata della mappa
     */
    public int strike(final Game game, final Coordinate coord)
        throws SessionNotStartedException, CellAlreadyMarkedException,
        OutOfMapException {
            if (!game.isSessionStarted()) {
                throw new SessionNotStartedException();
            }
            if (!game.isAttemptWithinBounds(coord)) {
                throw new OutOfMapException();
            }
            if (game.isAlreadyAttempted(coord)) {
                throw new CellAlreadyMarkedException();
            }

            game.addAttempt(coord);
            Grid curGrid = game.getSessionGrid();

            if (!curGrid.isCellEmpty(coord)) {
                Ship occupantShip = curGrid.get(coord);
                occupantShip.hit();
                if (occupantShip.isSunk()) {
                    game.incrementSunkShips();
                    return 1;
                } else {
                    return 0;
                }
            }
            // se non è stata colpita alcuna nave:
            game.incrementFailedAttempt();
            return -1;
        }
        /**
         * Imposta la difficoltà a una personalizzata con valori di tentativi fallibili personalizzati.
         * @param game oggetto che conserva i dati di gioco.
         * @param val intero che contiene il numero di tentativi falliti.
         * @throws SessionAlreadyStartedException non è possibile impostare la difficoltà
         */
        public void setCustomDifficulty(final Game game, final int val) throws SessionAlreadyStartedException {
            if (game.isSessionStarted()) {
                throw new SessionAlreadyStartedException();
            }
            Difficulty diff = new Difficulty();
            DifficultyController.getInstance().setCustomDifficulty(diff, val);
            try {
                game.setDifficulty(diff);
            } catch (CloneNotSupportedException e) { }
        }

    /**
     * Imposta la difficoltà a facile.
     * @param game sessione di gioco
     * @throws SessionAlreadyStartedException
     *      non è possibile impostare la difficoltà durante una sessione in corso
     */
    public void setEasyDifficulty(final Game game) throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        Difficulty diff = new Difficulty();
        diff.setNameLevel(EASY_NAME);
        diff.setMaxFailedAttempts(easyMaxFailedAttempts);
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
        DifficultyController.getInstance().setDefaultMedium(diff);

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
        DifficultyController.getInstance().setDefaultHard(diff);

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

    /**
     * Fornisce il numero di tentativi effettuati fino al momento corrente di una sessione di gioco in corso.
     *
     * @param game la sessione
     * @return numero di tentativi totali all'istante corrente
     * @throws SessionNotStartedException
     *      non è possibile ottenere questa informazione per una sessione non in corso.
     */
    public int getAttempts(final Game game) throws SessionNotStartedException {
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
    public int getFailedAttempts(final Game game) throws SessionNotStartedException {
        if (!game.isSessionStarted()) {
            throw new SessionNotStartedException();
        }
        return game.getFailedAttempts();
    }

}
