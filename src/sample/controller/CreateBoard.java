package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import sample.model.Mast;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateBoard extends GameController {

    public GridPane board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void clearBoard() {
        board.getChildren().retainAll(board.getChildren().get(0));
    }

    public void createBoard(ActionEvent actionEvent) {
        clearBoard();
        getFirstPlayer().createBoard();
        addMastsToPlayerBoard(board);
    }

    public void goTo(ActionEvent actionEvent) throws IOException {
        goTo(actionEvent, "../view/game.fxml");
    }
}
