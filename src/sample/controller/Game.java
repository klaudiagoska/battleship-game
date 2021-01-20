package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import sample.model.Point;
import sample.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Game extends GameController {

    public GridPane playerBoard;
    public GridPane enemyBoard;
    private final List<Point> enemyShots = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initPlayerBoard();
        initEnemyBoard();
        alert("You can start your game! Good luck!");
    }

    private void initPlayerBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                addToGrid(playerBoard, new Point(i, j));
            }
        }
        addMastsToPlayerBoard(playerBoard);
    }

    private void initEnemyBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Point point = new Point(i, j);
                point.setOnMouseClicked(mouseEvent -> playerMove(point));
                addToGrid(enemyBoard, point);
            }
        }
    }

    public void playerMove(Point point) {
        if (getPlayer().turn) {
            if (getEnemy().getBoard().containsMast(point)) {
                playerHit(point);
            } else {
                playerMiss(point);
            }
        }
    }

    public void enemyMove() {
        Point point = getRandomShot();
        if (getPlayer().getBoard().containsMast(point)) {
            enemyHit(point);
        } else {
            enemyMiss(point);
        }
    }

    private void playerHit(Point point) {
        addPoint(getPlayer());
        changePointOnEnemyBoard(point, true);
        if (getPlayer().isWinner()) {
            victory();
        }
    }

    private void playerMiss(Point point) {
        getPlayer().turn = false;
        changePointOnEnemyBoard(point, false);
        enemyMove();
    }

    private void enemyHit(Point point) {
        addPoint(getEnemy());
        changePointOnPlayerBoard(point, true);
        if (getEnemy().isWinner()) {
            defeat();
        }
        enemyMove();
    }

    private void enemyMiss(Point point) {
        changePointOnPlayerBoard(point, false);
        getPlayer().turn = true;
    }

    private void addPoint(Player player) {
        player.addPoint();
    }

    private void changePointOnPlayerBoard(Point point, boolean shot) {
        point.changeColor(shot);
        addToGrid(playerBoard, point);
    }

    private void changePointOnEnemyBoard(Point point, boolean shot) {
        point.changeColor(shot);
        addToGrid(enemyBoard, new Point(point.getX(), point.getY()));
    }

    private void addToGrid(GridPane gridPane, Point point) {
        GridPane.setRowIndex(point, point.getX());
        GridPane.setColumnIndex(point, point.getY());
        gridPane.getChildren().add(point);
    }

    private Point getRandomShot() {
        Point point = new Point();
        while (enemyShots.contains(point)) {
            point = new Point();
        }
        enemyShots.add(point);
        return point;
    }

    private void victory() {
        alert("You are the winner!!!");
    }

    private void defeat() {
        alert("Computer is the winner!");
    }

    public void endGame(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void restart(ActionEvent actionEvent) throws IOException {
        goTo(actionEvent, "../view/createBoard.fxml");
    }
}