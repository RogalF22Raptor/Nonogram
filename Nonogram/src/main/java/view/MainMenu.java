package view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainMenu extends ExitableView {
    private final double buttonWidth = 225;
    private final double buttonHeight = 150;
    public MainMenu() throws IOException {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        getChildren().add(vBox);

        vBox.getChildren().add(new GraphicButton("src/main/resources/nonogram/app/playButton", () -> {
            try {
                SelectLevel selectLevel = new SelectLevel("files/predefinedLevels");
                selectLevel.setScene(scene);
                selectLevel.setParentView(this);

                scene.setRoot(selectLevel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight));

        vBox.getChildren().add(new GraphicButton("src/main/resources/nonogram/app/levels", () -> {
            try {
                SelectLevel selectLevel = new SelectLevel("files/userLevels");
                selectLevel.setScene(scene);
                selectLevel.setParentView(this);

                scene.setRoot(selectLevel);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight));

        vBox.getChildren().add(new GraphicButton("src/main/resources/nonogram/app/creatorButton", () -> {
            try {
                SelectSize selectSize=new SelectSize();
                selectSize.setScene(scene);
                selectSize.setParentView(this);
                scene.setRoot(selectSize);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight));

        vBox.getChildren().add(new GraphicButton("src/main/resources/nonogram/app/exitButton", this::exit, buttonWidth, buttonHeight));
    }
}
