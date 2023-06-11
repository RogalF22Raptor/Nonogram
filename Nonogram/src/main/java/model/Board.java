package model;

/*
This class is ONLY a static representation of a board, meant for board editor, file IO etc.
 */

/*
reads/writes to a specified file.
*/

import javafx.scene.paint.Color;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Board implements FileIO {
    protected int height, width;
    protected Square[][] board;

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

    public Board(String path) {
        readFromFile(path);
    }

    @Override
    public void readFromFile(String path) throws CorruptedFile {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            String[] separateValues = everything.split("\\s+");

            int i = 0;
            height = Integer.parseInt(separateValues[i++]);
            width = Integer.parseInt(separateValues[i++]);

            board = new Square[height][width];
            for(int h = 0; h < height; h++) {
                for(int w = 0; w < width; w++) {
                    String state = separateValues[i++];
                    String color = separateValues[i++];
                    board[h][w] = new Square(SquareState.valueOf(state), Color.valueOf(color));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new CorruptedFile(path);
        }
    }

    @Override
    public void saveToFile(String path) throws IOException {
        PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);
        writer.println(height + " " + width);
        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                writer.println(board[h][w]);
            }
        }
        writer.close();
    }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Square getSquare(int h, int w) {
        return board[h][w];
    }
}
