package model;

/*
This class is meant to represent an interactive board (both player's actions and expected coloring),
along with possible additional data that such as HP/remaining time etc.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
reads/writes to a specified FOLDER (in contrast to Board.java) :)
 */
public class Game implements FileIO {
    private Board correctColoring;
    private Board currentColoring;

    public Game(Board correctColoring, Board currentColoring) {
        this.correctColoring = correctColoring;
        this.currentColoring = currentColoring;
    }

    public Game(String path) {
        readFromFile(path);
    }

    public Board getCurrentColoring() {
        return currentColoring;
    }

    public Board getCorrectColoring() {
        return correctColoring;
    }

    @Override
    public void readFromFile(String path) throws CorruptedFile {
        correctColoring = new Board(path + "/" + "correctColoring");
        currentColoring = new Board(path + "/" + "currentColoring");
    }

    @Override
    public void saveToFile(String path) throws IOException {
        Files.createDirectories(Path.of(path));
        correctColoring.saveToFile(path + "/" + "correctColoring");
        currentColoring.saveToFile(path + "/" + "currentColoring");
    }
}
