package it.uniba.app.new_design.battleship.entity;

import java.util.LinkedList;
import java.util.function.BiPredicate;

import it.uniba.app.game.entities.Difficulty;
import it.uniba.app.new_design.battleship.controller.GameFiller;

/**
 * Entity class <hr>
 *
 * Fornisce servizi per gestire una sessione di gioco (o partita) di <i>Battleship solitaire</i>.
 */
public final class Game {
    private static final LinkedList<Ship> SHIPS = getShipSet();

    private boolean sessionStarted;

    private Difficulty difficulty;
    private Grid grid;

    private int totAttempts;
    private int failedAttempts;
    

    /** Istanzia un oggetto della classe Game. */
    public Game() {
        difficulty = new Difficulty();
        sessionStarted = false;
    }

    public boolean isSessionStarted() {
        return sessionStarted;
    }

    /**
     * Avvia una nuova sessione di gioco.<hr>
     * Esegue tutte le inizializzazioni necessarie per giocare.
     */
    public void startSession() {
        grid = new Grid();
        GameFiller.randomlyFill(SHIPS, grid);   //TODO cercare di rimuovere questa dipendenza

        totAttempts = 0;
        failedAttempts = 0;
        sessionStarted = true;
    }

    public void endSession() {
        sessionStarted = false;
    }

    /**
     * Imposta una difficoltà.
     *
     * @param choice difficoltà da impostare
     */
    public void setDifficulty(final Difficulty choice)
        throws CloneNotSupportedException {
            difficulty = choice.clone();
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
     */
    public Grid getSessionGrid() {
        return grid.clone();
    }


    /**
     * Fornisce il numero di tentativi effettuati fino ad un dato istante
     * durante una sessione di gioco.
     * 
     * @return tentativi totali nell'istante corrente
     */
    public int getAttempts() {
        return totAttempts;
    }

    /**
     * Fornisce il numero di tentativi falliti fino ad un certo istante di una sessione di gioco.
     * 
     * @return tentativi falliti all'istante corrente
     */
    public int getFailedAttempts() {
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
