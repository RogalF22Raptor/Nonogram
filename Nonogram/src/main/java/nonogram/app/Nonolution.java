package nonogram.app;

import javafx.application.Application;
import javafx.scene.Scene;
<<<<<<< Updated upstream:Nonogram/src/main/java/nonogram/app/TemporaryNameOfTheGame.java
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import viewmodel.PlayViewModel;
import viewmodel.ViewModel;
=======
import javafx.stage.Stage;
import view.MainMenu;
>>>>>>> Stashed changes:Nonogram/src/main/java/nonogram/app/Nonolution.java

import java.io.IOException;

public class Nonolution extends Application {
    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< Updated upstream:Nonogram/src/main/java/nonogram/app/TemporaryNameOfTheGame.java
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nonogram/app/guiView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nonogram/app/CreateGuiView.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 600,600);
        stage.setTitle("Nonogram!");
=======
        MainMenu mainMenu = new MainMenu();
        Scene scene = new Scene(mainMenu, 1280, 720);
        mainMenu.setScene(scene);

        stage.setTitle("Nonolution!");
>>>>>>> Stashed changes:Nonogram/src/main/java/nonogram/app/Nonolution.java
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        launch();
    }
}