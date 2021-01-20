package sample.model;

public class Player {

    private int points;
    public boolean turn;
    private Board board;

    public Player(boolean player) {
        points = 0;
        if (player) {
            turn = true;
        } else {
            turn = false;
            createBoard();
        }
    }

    public void createBoard() {
        board = new Board(new BoardGenerator());
    }

    public void addPoint() {
        points++;
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
