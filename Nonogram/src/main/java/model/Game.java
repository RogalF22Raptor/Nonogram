package model;

/*
This class is meant to represent an interactive board (both player's actions and expected coloring),
along with possible additional data that such as HP/remaining time etc.
 */
public class Game implements FileIO {
    private final Board correctColoring;
    private Board currentColoring;

    public Game(Board correctColoring, Board currentColoring) {
        this.correctColoring = correctColoring;
        this.currentColoring = currentColoring;
    }
    public Board getCurrentColoring() {
        return currentColoring;
    }

    public Board getCorrectColoring() {
        return correctColoring;
    }

}
