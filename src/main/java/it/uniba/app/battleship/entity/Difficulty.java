package it.uniba.app.battleship.entity;
/**
 * La classe Difficulty rappresenta una singola difficoltà di gioco.
 */
public class Difficulty implements Cloneable {
    private String name;
    private int maxFailedAttempts;
  /**
   * Costruttore predefinito per la classe Difficulty.
   * Questo costruttore crea un'istanza di Difficulty con valori predefiniti.
   */
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
    public void setLevel(final String level) {
        name = level;
    }
   /**
    * Restituisce il valore massimo di tentativi falliti.
    * @return il valore massimo di tentativi falliti
    */
    public int getMaxFailedAttempts() {
        return maxFailedAttempts;
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
