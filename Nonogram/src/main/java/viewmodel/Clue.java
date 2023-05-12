package viewmodel;

import javafx.scene.paint.Color;
import model.Square;

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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Clue)) {
            return false;
        }
        return number.equals(((Clue) obj).number)&&color.equals(((Clue) obj).color);
    }

    /**temporary change of toString method
    @Override
    public String toString() {
        return number.toString();
    }**/
    @Override
    public String toString() {
        return number.toString()+" "+color.toString();
    }

}
