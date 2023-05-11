package nonogram.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import viewmodel.PlayViewModel;
import viewmodel.ViewModel;

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
        Game game = new Game(new RandomBoard(5,5), new Board(5, 5));
        PlayViewModel v = new PlayViewModel(game);
        System.out.println(v.isComplete());
        System.out.println(v.getColors());
        System.out.println(v.getColumnClues());
        System.out.println(v.getRowClues());
        v.changeTool(SquareState.COLORED,Color.RED);
        v.makeMove(0,0);
        System.out.println(v.isComplete());
        game.saveToFile("files/level1");

        launch();
    }
}