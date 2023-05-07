package model;

/*
This class is ONLY a static representation of a board, meant for board editor, file IO etc.
 */

public class Board implements FileIO {
    private final int height, width;
    private Square[][] board;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;

        board = new Square[height][width];
        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                board[h][w] = new Square();
            }
        }
    }

    public Square getSquare(int h, int w) {
        return board[h][w];
    }
}
