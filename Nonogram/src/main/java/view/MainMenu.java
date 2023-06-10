package view;

import javafx.geometry.Pos;
import model.RandomBoard;
import nonogram.app.CreateGuiView;
import nonogram.app.GuiView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainMenu extends ExitableView {
    private final double buttonWidth = 225;
    private final double buttonHeight = 150;
    public MainMenu() throws IOException {
        setAlignment(Pos.CENTER);

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/playButton", () -> {
            try {
                SelectLevel selectLevel = new SelectLevel("files/predefinedLevels");
                selectLevel.setScene(scene);
                selectLevel.setParentView(this);

                scene.setRoot(selectLevel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/levels", () -> {
            try {
                SelectLevel selectLevel = new SelectLevel("files/userLevels");
                selectLevel.setScene(scene);
                selectLevel.setParentView(this);

                scene.setRoot(selectLevel);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/edytorButton", () -> {
            try {
                CreateGuiView createGuiView = new CreateGuiView(6,6);
                BackupableView newCreator = new BackupableView(this, createGuiView);
                newCreator.setScene(scene);
                scene.setRoot(newCreator);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight));

        getChildren().add(new GraphicButton("src/main/resources/nonogram/app/exitButton", this::exit, buttonWidth, buttonHeight));
    }
}
