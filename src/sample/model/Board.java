package sample.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final String[] boardArray;
    private final List<Ship> ships;

    public Board(BoardGenerator boardGenerator) {
        boardGenerator.generateBoard();
        boardArray = boardGenerator.getBoard();
        ships = boardGenerator.getShips();
    }

    public String[] getBoardArray() {
        return boardArray;
    }

    public List<Ship> getAllShips() {
        return ships;
    }

    public List<Point> getAllMasts() {
        List<Point> points = new ArrayList<>();
        for (var ship : ships) {
            points.addAll(ship.getShip());
        }
        return points;
    }

    public boolean containsMast(Point point) {
        for (var ship : ships) {
            if (ship.containsMast(point)) {
                return true;
            }
        }
        return false;
    }
}
