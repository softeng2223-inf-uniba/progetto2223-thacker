package it.uniba.app.game.entities;
/**
 * La classe Difficulty rappresenta una singola difficoltà di gioco.
 */
public class Difficulty implements Cloneable{
    private String name;
    private int maxFailedAttempts;
    public Difficulty() { }
    public String getLevel() {
        return name;
    }
    public void setLevel(String level) {
        name = level;
    }
    public void setMaxFailedAttempts(final int mfa) {
        maxFailedAttempts = mfa;
    }
    public Difficulty clone() throws CloneNotSupportedException {
        return (Difficulty) super.clone();
    }
}
