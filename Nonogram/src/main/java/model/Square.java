package model;

import javafx.scene.paint.Color;

/*
A pair-like class representing a square, asserting that each square isn't blank and colored simultaneously.
 */
public class Square {
    private Color color = Color.BLACK;
    private SquareState state = SquareState.UNKNOWN;

    public Square() {}
    public Square(Square square) {
        this(square.getState(), square.getColor());
    }

    public Square(SquareState state, Color color) {
        this.state = state;
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
    }

    public void setUnknown() {
        color = Color.BLACK;
        state = SquareState.UNKNOWN;
    }

    public void setEmpty(){
        color = Color.BLACK;
        state = SquareState.EMPTY;
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

    @Override
    public String toString() {
        String result = state.toString() + ' ';
        if(color == null) {
            result += "NONE";
        }
        else {
            result += color.toString();
        }
        return result;
    }
}
