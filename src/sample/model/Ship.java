package sample.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final List<Mast> ship;
    private int size;

    public Ship() {
        ship = new ArrayList<>();
        size = 0;
    }

    public void addMast(Mast mast) {
        ship.add(mast);
        size++;
    }

    public int getSize() {
        return size;
    }

    public Mast getMast(int idx) {
        return ship.get(idx);
    }

    public List<Mast> getShip() {
        return ship;
    }

    public boolean containsMast(Mast mast) {
        for (Mast m : ship) {
            if (m.equals(mast)) {
                return true;
            }
        }
        return false;
    }
}
