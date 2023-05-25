package nonogram.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import view.MainMenu;
import viewmodel.PlayViewModel;
import viewmodel.ViewModel;

import java.io.IOException;

public class TemporaryNameOfTheGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainMenu mainMenu = new MainMenu();
        Scene scene = new Scene(mainMenu, 1280, 720);
        mainMenu.setScene(scene);

        stage.setTitle("Nonogram!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}