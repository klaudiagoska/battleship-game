package sample.model;

import java.util.ArrayList;
import java.util.List;

public class BoardGenerator {

    private final int boardSize = 10;
    private final StringBuilder[] board = new StringBuilder[boardSize];
    private final List<Ship> ships = new ArrayList<>();

    public BoardGenerator() {
        for (int i = 0; i < boardSize; i++) {
            board[i] = new StringBuilder();
        }
    }

    private void placeShip(Ship ship) {
        for (Point point : ship.getShip()) {
            board[point.getX()].setCharAt(point.getY(), '#');
        }
    }

    private boolean notWater(Point point) {
        return board[point.getX()].charAt(point.getY()) != '.';
    }

    private boolean notAvailableLocation(Ship ship, int x, int y) {
        Point point = new Point(x, y);
        if (point.correctCoordinates()) {
            if (notContainsMast(ship, point)) {
                return notWater(point);
            }
        }
        return false;
    }

    private boolean checkAroundMast(Ship ship, Point point) {
        int x = point.getX(), y = point.getY();
        if (notAvailableLocation(ship, x, y - 1)) {
            return false;
        }
        if (notAvailableLocation(ship, x - 1, y - 1)) {
            return false;
        }
        if (notAvailableLocation(ship, x - 1, y)) {
            return false;
        }
        if (notAvailableLocation(ship, x - 1, y + 1)) {
            return false;
        }
        if (notAvailableLocation(ship, x, y + 1)) {
            return false;
        }
        if (notAvailableLocation(ship, x + 1, y + 1)) {
            return false;
        }
        if (notAvailableLocation(ship, x + 1, y)) {
            return false;
        }
        if (notAvailableLocation(ship, x + 1, y - 1)) {
            return false;
        }
        return true;
    }

    private boolean checkShip(Ship ship) {
        for (Point pointToCheck : ship.getShip()) {
            if (!pointToCheck.correctCoordinates()) {
                return false;
            }
            if (notWater(pointToCheck)) {
                return false;
            }
            if (!checkAroundMast(ship, pointToCheck)) {
                return false;
            }
        }
        return true;
    }

    private boolean notContainsMast(Ship ship, Point point) {
        return !ship.containsMast(point);
    }

    private boolean addMast(Ship ship, int x, int y) {
        Point newPoint = new Point(x, y);
        if (notContainsMast(ship, newPoint)) {
            ship.addMast(newPoint);
            return true;
        }
        return false;
    }

    private void addToShip(Ship ship, Point point) {
        int x = point.getX(), y = point.getY();
        boolean success = false;
        while (!success) {
            int whichSide = (int) (Math.random() * 4);
            switch (whichSide) {
                case 0: // left
                    success = addMast(ship, x, y - 1);
                    break;
                case 1: // top
                    success = addMast(ship, x - 1, y);
                    break;
                case 2: // right
                    success = addMast(ship, x, y + 1);
                    break;
                case 3: // bottom
                    success = addMast(ship, x + 1, y);
                    break;
            }
        }
    }

    private Ship createShip(int size) {
        Ship ship = new Ship();
        ship.addMast(new Point());
        while (size != ship.getSize()) {
            int addToThisMast = (int) (Math.random() * ship.getSize());
            addToShip(ship, ship.getMast(addToThisMast));
        }
        return ship;
    }

    private void generateShips(int shipSize, int howMany) {
        for (int i = 0; i < howMany; i++) {
            boolean success = false;
            while (!success) {
                Ship ship = createShip(shipSize);
                if (checkShip(ship)) {
                    placeShip(ship);
                    ships.add(ship);
                    success = true;
                }
            }
        }
    }

    private void emptyMap() {
        for (int i = 0; i < boardSize; i++) {
            board[i].append(".".repeat(10));
        }
    }

    private void generate() {
        emptyMap();
        for (int shipSize = 1; shipSize <= 4; shipSize++) {
            switch (shipSize) {
                case 1:
                    generateShips(1, 4);
                    break;
                case 2:
                    generateShips(2, 3);
                    break;
                case 3:
                    generateShips(3, 2);
                    break;
                case 4:
                    generateShips(4, 1);
                    break;
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            System.out.println(board[i]);
        }
    }

    public void generateBoard() {
        generate();
    }

    public String[] getBoard() {
        String[] result = new String[boardSize];
        for (int i = 0; i < boardSize; i++) {
            result[i] = board[i].toString();
        }
        return result;
    }

    public List<Ship> getShips() {
        return ships;
    }
}