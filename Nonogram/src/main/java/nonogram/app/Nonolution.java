package nonogram.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainMenu;

import java.io.IOException;

public class Nonolution extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainMenu mainMenu = new MainMenu();
        Scene scene = new Scene(mainMenu, 1280, 720);
        mainMenu.setScene(scene);

        stage.setTitle("Nonogram!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}