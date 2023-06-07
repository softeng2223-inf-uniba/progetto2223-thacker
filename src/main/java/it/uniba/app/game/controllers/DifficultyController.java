package it.uniba.app.game.controllers;
import it.uniba.app.game.entities.Difficulty;

public class DifficultyController {
    private static final String EASY_NAME = "Facile";
    private static final String MEDIUM_NAME = "Medio";
    private static final String HARD_NAME = "Difficile";
    private static final int EASY_MAX_FAILED_ATTEMPTS = 50;
    private static final int MEDIUM_MAX_FAILED_ATTEMPTS = 30;
    private static final int HARD_MAX_FAILED_ATTEMPTS = 10;
    private DifficultyController() { }
}
