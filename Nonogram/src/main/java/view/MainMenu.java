package view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenu extends ExitableView {
    public MainMenu() throws IOException {
        super(null);
        setAlignment(Pos.CENTER);

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/playButton", () -> {
            try {
                scene.setRoot(new BorderPane(FXMLLoader.load(getClass().getResource("/nonogram/app/guiView.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 300, 200));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/edytorButton", () -> System.out.println("EDYTOR"), 300, 200));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/exitButton", this::exit, 300, 200));
    }
}
