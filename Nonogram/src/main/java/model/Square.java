package model;

import javafx.scene.paint.Color;

/*
A pair-like class representing a square, asserting that each square isn't blank and colored simultaneously.
 */
public class Square {
    private Color color;
    private SquareState state=SquareState.UNKNOWN;

    public Square() {}
    public Square(Square square) {
        color = new Color(square.getColor().getRed(),
                square.getColor().getGreen(),
                square.getColor().getBlue(),
                square.getColor().getOpacity());
        state = square.getState();
    }

    public void setUnknown() {
        color = null;
        state=SquareState.UNKNOWN;
    }

    public void setEmpty(){
        color=null;
        state=SquareState.EMPTY;
    }
    public void setColor(Color color) {
        this.color = color;
        state = SquareState.COLORED;
    }

    public SquareState getState() {
        return state;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Square)) {
            return false;
        }
        return color.equals(((Square) obj).getColor()) && state == ((Square) obj).getState();
    }
}
