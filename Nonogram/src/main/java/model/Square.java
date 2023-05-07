package model;

import javafx.scene.paint.Color;

/*
A pair-like class representing a square, asserting that each square isn't blank and colored simultaneously.
 */
public class Square {
    private Color color;
    private boolean blank = true;

    public Square() {}
    public Square(Square square) {
        color = new Color(square.getColor().getRed(),
                square.getColor().getGreen(),
                square.getColor().getBlue(),
                square.getColor().getOpacity());
        blank = square.isBlank();
    }

    public void setBlank() {
        color = null;
        blank = true;
    }

    public void setColor(Color color) {
        this.color = color;
        blank = false;
    }

    public boolean isBlank() {
        return blank;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Square)) {
            return false;
        }
        return color.equals(((Square) obj).getColor()) && blank == ((Square) obj).isBlank();
    }
}
