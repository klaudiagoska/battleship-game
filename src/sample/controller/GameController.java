package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.model.Mast;
import sample.model.Player;

import java.io.IOException;
import java.util.List;

public abstract class GameController implements Initializable {

    private static Player firstPlayer;
    private static Player secondPlayer;

    public void setFirstPlayer(Player firstPlayer) {
        GameController.firstPlayer = firstPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        GameController.secondPlayer = secondPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public FXMLLoader getLoader(String path) {
        return new FXMLLoader(getClass().getResource(path));
    }

    public void goTo(ActionEvent actionEvent, String path) throws IOException {
        changeWindow(actionEvent, getLoader(path));
    }

    public static void changeWindow(ActionEvent actionEvent, FXMLLoader loader) throws IOException {
        Parent homePageParent = loader.load();
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    public void addMastsToPlayerBoard(GridPane gridPane) {
        List<Mast> masts = getFirstPlayer().getBoard().getAllMasts();
        for (var mast : masts) {
            mast.setStyle("-fx-background-color: black");
            GridPane.setRowIndex(mast, mast.getX());
            GridPane.setColumnIndex(mast, mast.getY());
            gridPane.getChildren().add(mast);
        }
    }

    public void alert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
