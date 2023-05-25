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
        super(null);
        setAlignment(Pos.CENTER);

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/playButton", () -> {
            try {
                GuiView guiView = FXMLLoader.load(getClass().getResource("/nonogram/app/guiView.fxml"));
                guiView.setParentView(this);
                guiView.setScene(scene);
                scene.setRoot(new BorderPane(guiView));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 300, 200));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/edytorButton", () -> {
            try {
                CreateGuiView createGuiView = FXMLLoader.load(getClass().getResource("/nonogram/app/CreateGuiView.fxml"));
                createGuiView.setParentView(this);
                createGuiView.setScene(scene);
                scene.setRoot(createGuiView);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 300, 200));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/exitButton", this::exit, 300, 200));
    }
}
