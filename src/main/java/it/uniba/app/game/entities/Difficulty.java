package it.uniba.app.game.entities;
/**
 * La classe Difficulty rappresenta una singola difficoltà di gioco.
 */
public class Difficulty implements Cloneable{
    private String name;
    private int maxFailedAttempts;
    public Difficulty() { }
    /**
     * Restituisce il livello di difficoltà dell'oggetto.
     *
     * @return il livello di difficoltà.
     */
    public String getLevel() {
        return name;
    }
    /**
     * Imposta il livello di difficoltà dell'oggetto.
     *
     * @param level il livello di difficoltà da impostare
     */
    public void setLevel(String level) {
        name = level;
    }
    /**
     * Imposta il numero massimo di tentativi falliti consentiti.
     *
     * @param mfa il numero massimo di tentativi falliti
     */
    public void setMaxFailedAttempts(final int mfa) {
        maxFailedAttempts = mfa;
    }
    /**
     * Crea e restituisce una copia dell'oggetto Difficulty.
     *
     * @return una copia dell'oggetto Difficulty
     * @throws CloneNotSupportedException se la clonazione non è supportata
     */
    public Difficulty clone() throws CloneNotSupportedException {
        return (Difficulty) super.clone();
    }
}
