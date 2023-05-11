package model;

import javafx.scene.paint.Color;

import java.util.Random;

/*
Random black/white board
 */
public class RandomBoard extends Board{
    public RandomBoard(int height, int width) {
        super(height, width);
        Random rnd=new Random();
        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                int x=rnd.nextInt(2);
                if(x%2==1) board[h][w] = new Square(SquareState.EMPTY, Color.BLACK);
                if(x%2==0) board[h][w] = new Square(SquareState.COLORED, Color.BLACK);
            }
        }
    }
}
