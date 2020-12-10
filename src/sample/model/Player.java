package sample.model;

public class Player {


    private final String name;
    private Board board;
    private int points;
    public boolean turn;

    public Player() {
        name = "Computer";
        points = 0;
        createBoard();
        turn = false;
    }

    public Player(String name) {
        this.name = name;
        points = 0;
        turn = true;
    }

    public void createBoard() {
        board = new Board(new BoardGenerator());
    }

    public void addPoint() {
        points++;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWinner() {
        return points == 20;
    }

}
