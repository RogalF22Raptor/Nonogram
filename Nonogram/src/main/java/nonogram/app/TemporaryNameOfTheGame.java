package nonogram.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import viewmodel.PlayViewModel;
import viewmodel.ViewModel;

import java.io.IOException;

public class TemporaryNameOfTheGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nonogram/app/guiView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nonogram/app/CreateGuiView.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 600,600);
        stage.setTitle("Nonogram!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        launch();
    }
}