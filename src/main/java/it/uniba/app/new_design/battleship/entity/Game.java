package it.uniba.app.new_design.battleship.entity;

import java.util.LinkedList;

import it.uniba.app.game.entities.Difficulty;
import it.uniba.app.new_design.battleship.controller.GameFiller;

import it.uniba.app.game.exceptions.DifficultyNotSetException;
import it.uniba.app.game.exceptions.SessionAlreadyStartedException;
import it.uniba.app.game.exceptions.SessionNotStartedException;

/**
 * Entity class <hr>
 *
 * Fornisce servizi per gestire una sessione di gioco (o partita) di <i>Battleship solitaire</i>.
 */
public final class Game {
    private static final LinkedList<Ship> SHIPS = getShipSet();

    private boolean isSessionStarted;
    private boolean isDifficultySet;

    private Difficulty difficulty;
    private Grid grid;

    private int totAttempts;
    private int failedAttempts;
    

    /** Istanzia un oggetto della classe Game. */
    public Game() {
        difficulty = new Difficulty();
        isSessionStarted = false;
        isDifficultySet = false;
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
    public void startSession()
        throws SessionAlreadyStartedException, DifficultyNotSetException {
            if (isSessionStarted) {
                throw new SessionAlreadyStartedException();
            }

            if (!isDifficultySet) {
                throw new DifficultyNotSetException();
            }

            grid = new Grid();
            GameFiller.randomlyFill(SHIPS, grid);   //TODO cercare di rimuovere questa dipendenza

            totAttempts = 0;
            failedAttempts = 0;

            isSessionStarted = true;
        }

    /**
     * Termina una sessione di gioco in corso.
     *
     * @throws SessionNotStartedException Non è possibile terminare una sessione se non è in corso.
     */
    public void endSession() throws SessionNotStartedException {
        if (!isSessionStarted) {
            throw new SessionNotStartedException();
        }
        isSessionStarted = false;
    }

    /**
     * Imposta una difficoltà.
     *
     * @param choice difficoltà da impostare
     */
    public void setDifficulty(final Difficulty choice)
        throws SessionAlreadyStartedException, CloneNotSupportedException {
            if (isSessionStarted) {
                throw new SessionAlreadyStartedException();
            }

            difficulty = choice.clone();
            isDifficultySet = true;
        }

    /**
     * Fornisce le informazioni relative alla difficoltà impostata in un determinato istante.
     *
     * @return difficoltà selezionata
     * @throws CloneNotSupportedException
     */
    public Difficulty getDifficulty() throws CloneNotSupportedException {
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
    public Grid getSessionGrid() throws SessionNotStartedException {
        if (!isSessionStarted) {
            throw new SessionNotStartedException();
        }
        return grid.clone();
    }


    /**
     * Fornisce il numero di tentativi effettuati fino ad un dato istante
     * durante una sessione di gioco.
     * 
     * @return tentativi totali nell'istante corrente
     * @throws SessionNotStartedException
     */
    public int getAttempts() throws SessionNotStartedException {
        if(!isSessionStarted) {
            throw new SessionNotStartedException();
        }
        return totAttempts;
    }

    /**
     * Incrementa numero di tentativi corrente durante una sessione di gioco.
     * 
     * @throws SessionNotStartedException
     */
    // TODO decidere visibilità
    void increaseAttempts() throws SessionNotStartedException {
        if(!isSessionStarted) {
            throw new SessionNotStartedException();
        }
        totAttempts += 1;
    }


    /**
     * Fornisce il numero di tentativi falliti fino ad un certo istante di una sessione di gioco.
     * 
     * @return tentativi falliti all'istante corrente
     * @throws SessionNotStartedException
     */
    public int getFailedAttempts() throws SessionNotStartedException {
        if(!isSessionStarted) {
            throw new SessionNotStartedException();
        }
        return failedAttempts;
    }

    /* === PRIVATE METHODS === */

      /**
     * Restituisce una lista di navi contenente, per ogni tipo di nave, tante istanze
     * quanti sono numero di esemplari previsti per tale tipo.
     *
     * @return lista di navi
     */
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
