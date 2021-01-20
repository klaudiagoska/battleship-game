package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.model.Point;
import sample.model.Player;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public abstract class GameController implements Initializable {

    private static Player player;
    private static Player enemy;

    public void setPlayer(Player firstPlayer) {
        GameController.player = firstPlayer;
    }

    public void setEnemy(Player secondPlayer) {
        GameController.enemy = secondPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getEnemy() {
        return enemy;
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
        List<Point> points = getPlayer().getBoard().getAllMasts();
        for (var mast : points) {
            mast.setStyle("-fx-background-color: black");
            GridPane.setRowIndex(mast, mast.getX());
            GridPane.setColumnIndex(mast, mast.getY());
            gridPane.getChildren().add(mast);
        }
    }

    public void alert(String content) {
        JOptionPane.showMessageDialog(null, content);
    }
}
