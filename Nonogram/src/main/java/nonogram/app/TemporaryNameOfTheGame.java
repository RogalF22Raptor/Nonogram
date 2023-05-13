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
        FXMLLoader fxmlLoader = new FXMLLoader(TemporaryNameOfTheGame.class.getResource("guiView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Nonogram!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        launch();
    }
}