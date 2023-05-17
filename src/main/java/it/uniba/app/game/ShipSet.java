package it.uniba.app.game;
import java.util.LinkedList;

public final class ShipSet {
    private ShipSet() { }
    private static LinkedList<Ship> shipsList = new LinkedList<Ship>();

    public static LinkedList<Ship> getShipsSet() {
        Cacciatorpediniere p    = new Cacciatorpediniere();
        Incrociatore s          = new Incrociatore();
        Corazzata l             = new Corazzata();
        Portaerei c             = new Portaerei();

        for (int i = 0; i < p.getNumberOfInstances(); i++) {
            shipsList.add(new Cacciatorpediniere());
        }


    }
}
