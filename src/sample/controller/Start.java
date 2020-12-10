package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Start extends GameController {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void goTo(ActionEvent actionEvent) throws IOException {
        goTo(actionEvent, "../view/name.fxml");
    }
}
