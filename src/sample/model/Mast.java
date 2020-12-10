package sample.model;

import javafx.scene.layout.Pane;

public class Mast extends Pane {
    private final int x, y;

    public Mast() {
        x = (int) (Math.random() * 10);
        y = (int) (Math.random() * 10);
    }

    public Mast(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean correctCoordinates() {
        return x >= 0 && x <= 9 && y >= 0 && y <= 9;
    }

    public void changeColor(boolean shot) {
        if (shot) {
            setStyle("-fx-background-color: #cb1111");
        } else {
            setStyle("-fx-background-color: #4c91fa");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Mast mast = (Mast) obj;
        return x == mast.x && y == mast.y;
    }

}
