package view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import nonogram.app.CreateGuiView;
import nonogram.app.GuiView;

import java.io.IOException;

public class MainMenu extends ExitableView {
    public MainMenu() throws IOException {
        setAlignment(Pos.CENTER);

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/playButton", () -> {
            try {
                GuiView guiView = FXMLLoader.load(getClass().getResource("/nonogram/app/guiView.fxml"));
                ExitableView newGame = new ExitableView(this, guiView);
                newGame.setScene(scene);

                scene.setRoot(newGame);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 300, 200));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/edytorButton", () -> {
            try {
                CreateGuiView createGuiView = FXMLLoader.load(getClass().getResource("/nonogram/app/CreateGuiView.fxml"));
                ExitableView newCreator = new ExitableView(this, createGuiView);
                newCreator.setScene(scene);
                
                scene.setRoot(newCreator);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 300, 200));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/exitButton", this::exit, 300, 200));
    }
}
