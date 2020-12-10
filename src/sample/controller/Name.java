package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import sample.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Name extends GameController {

    public TextField nameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goTo(ActionEvent actionEvent) throws IOException {
        if (nameField.getText().equals("")) {
            alert("Error", "Set your name.");
        } else {
            setFirstPlayer(new Player(nameField.getText()));
            setSecondPlayer(new Player());
            goTo(actionEvent, "../view/createBoard.fxml");
        }
    }
}
