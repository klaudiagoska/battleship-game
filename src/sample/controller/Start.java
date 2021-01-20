package sample.controller;

import javafx.event.ActionEvent;
import sample.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Start extends GameController {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void goTo(ActionEvent actionEvent) throws IOException {
        setPlayer(new Player(true));
        setEnemy(new Player(false));
        goTo(actionEvent, "../view/createBoard.fxml");
    }
}
