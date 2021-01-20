package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateBoard extends GameController {

    public GridPane board;
    private boolean created = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void clearBoard() {
        board.getChildren().retainAll(board.getChildren().get(0));
    }

    public void createBoard(ActionEvent actionEvent) {
        clearBoard();
        getPlayer().createBoard();
        addMastsToPlayerBoard(board);
        created = true;
    }

    public void goTo(ActionEvent actionEvent) throws IOException {
        if (created) {
            goTo(actionEvent, "../view/game.fxml");
        } else {
            alert("You have to create your board!");
        }
    }
}
