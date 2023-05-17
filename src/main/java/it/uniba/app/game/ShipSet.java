package it.uniba.app.game;
import java.util.LinkedList;
/**
 * Classe utilizzata per posizionare in maniera
 * randomica, sulla Griglia delle Navi, il numero
 * corretto di istanze di navi di ciascun
 * tipo di specializzazione.
 */
public final class ShipSet {
    private ShipSet() { }
    private static LinkedList<Ship> shipsList = new LinkedList<Ship>();

    /**
     * Metodo che restituisce una lista con il numero
     * d'instanze corrette di ciascun tipo di nave.
     * @return shipsList lista di navi da posizionare sulla griglia
     */
    public static LinkedList<Ship> getShipsSet() {
        Cacciatorpediniere p    = new Cacciatorpediniere();
        Incrociatore s          = new Incrociatore();
        Corazzata l             = new Corazzata();
        Portaerei c             = new Portaerei();

        for (int i = 0; i < p.getNumberOfInstances(); i++) {
            shipsList.add(new Cacciatorpediniere());
        }
        for (int i = 0; i < s.getNumberOfInstances(); i++) {
            shipsList.add(new Incrociatore());
        }
        for (int i = 0; i < l.getNumberOfInstances(); i++) {
            shipsList.add(new Corazzata());
        }
        for (int i = 0; i < c.getNumberOfInstances(); i++) {
            shipsList.add(new Portaerei());
        }
        return shipsList;
    }
}
