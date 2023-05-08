package viewmodel;

import javafx.scene.paint.Color;

public class Clue {
    private final Integer number;
    private final Color color;

    Clue(Integer number,Color color){
        this.number=number;
        this.color=color;
    }
    public Color getColor() {
        return color;
    }

    public Integer getNumber() {
        return number;
    }
}
