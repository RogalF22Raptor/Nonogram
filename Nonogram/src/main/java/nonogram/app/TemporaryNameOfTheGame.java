package nonogram.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Board;
import model.Game;
import model.Square;
import model.SquareState;

import java.io.IOException;

public class TemporaryNameOfTheGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TemporaryNameOfTheGame.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Nonogram!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Color.TEAL);
        System.out.println(SquareState.UNKNOWN);
        Board board = new Board(5, 5);
        board.getSquare(0, 0).setColor(Color.RED);
        board.getSquare(4, 4).setEmpty();

        Game game = new Game(board, new Board(5, 5));
        game.saveToFile("files/level1");

        launch();
    }
}