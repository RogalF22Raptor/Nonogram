package nonogram.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Board;
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

    public static void main(String[] args) {
        Board board = new Board(5, 5);
        if(board.getSquare(0, 0).getState()== SquareState.UNKNOWN) {
            board.getSquare(0, 0).setColor(Color.TEAL);
        }
        System.out.println(board.getSquare(0, 0).getColor());
        launch();
    }
}