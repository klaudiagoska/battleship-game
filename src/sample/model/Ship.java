package sample.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final List<Point> ship;
    private int size;

    public Ship() {
        ship = new ArrayList<>();
        size = 0;
    }

    public void addMast(Point point) {
        ship.add(point);
        size++;
    }

    public int getSize() {
        return size;
    }

    public Point getMast(int idx) {
        return ship.get(idx);
    }

    public List<Point> getShip() {
        return ship;
    }

    public boolean containsMast(Point point) {
        for (Point m : ship) {
            if (m.equals(point)) {
                return true;
            }
        }
        return false;
    }
}
