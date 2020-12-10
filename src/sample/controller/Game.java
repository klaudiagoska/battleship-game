package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import sample.model.Mast;
import sample.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Game extends GameController {

    public GridPane playerBoard;
    public GridPane enemyBoard;
    private final List<Mast> enemyShots = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initPlayerBoard();
        initEnemyBoard();
        alert("Game is starting...", "You can start your game! Good luck!");
    }

    private void initPlayerBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                addToGrid(playerBoard, new Mast(i, j));
            }
        }
        addMastsToPlayerBoard(playerBoard);
    }

    private void initEnemyBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Mast mast = new Mast(i, j);
                mast.setOnMouseClicked(mouseEvent -> playerMove(mast));
                addToGrid(enemyBoard, mast);
            }
        }
    }

    public void playerMove(Mast mast) {
        boolean shot;
        if (getFirstPlayer().turn) {
            if (getSecondPlayer().getBoard().containsMast(mast)) {
                shot = true;
                addPoint(getFirstPlayer());
            } else {
                shot = false;
                getFirstPlayer().turn = false;
                enemyMove();
            }
            changeMastOnEnemyBoard(mast, shot);
        }
    }

    public void enemyMove() {
        Mast mast = getCorrectMast();
        boolean shot;
        if (getFirstPlayer().getBoard().containsMast(mast)) {
            shot = true;
            addPoint(getSecondPlayer());
            enemyMove();
        } else {
            shot = false;
            getFirstPlayer().turn = true;
        }
        changeMastOnPlayerBoard(mast, shot);
    }

    private void addPoint(Player player) {
        player.addPoint();
        checkVictory(player);
    }

    private void changeMastOnPlayerBoard(Mast mast, boolean shot) {
        mast.changeColor(shot);
        addToGrid(playerBoard, mast);
    }

    private void changeMastOnEnemyBoard(Mast mast, boolean shot) {
        mast.changeColor(shot);
        addToGrid(enemyBoard, new Mast(mast.getX(), mast.getY()));
    }

    private void addToGrid(GridPane gridPane, Mast mast) {
        GridPane.setRowIndex(mast, mast.getX());
        GridPane.setColumnIndex(mast, mast.getY());
        gridPane.getChildren().add(mast);
    }

    private Mast getCorrectMast() {
        Mast mast = new Mast();
        while (enemyShots.contains(mast)) {
            mast = new Mast();
        }
        enemyShots.add(mast);
        return mast;
    }

    private void checkVictory(Player player) {
        if (player.isWinner()) {
            alert("Victory!", player.getName() + " wins!");
        }
    }

    public void endGame(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void restart(ActionEvent actionEvent) throws IOException {
        goTo(actionEvent, "../view/name.fxml");
    }
}