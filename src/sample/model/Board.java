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

    public List<Mast> getAllMasts() {
        List<Mast> masts = new ArrayList<>();
        for (var ship : ships) {
            masts.addAll(ship.getShip());
        }
        return masts;
    }

    public boolean containsMast(Mast mast) {
        for (var ship : ships) {
            if (ship.containsMast(mast)) {
                return true;
            }
        }
        return false;
    }
}
