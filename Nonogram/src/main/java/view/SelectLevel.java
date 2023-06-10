package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.Board;
import nonogram.app.GuiView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SelectLevel extends ExitableView {
    private final double buttonHeight = 150;
    private final double buttonWidth = 225;
    private final int maxCntOfLevels = 15;

    private final List<String> listOfLevels;
    private GridPane gridPane;

    private GraphicButton createLevelPlayButton(String name_of_level) throws FileNotFoundException {
        return new GraphicButton("src/main/resources/nonogram/app/playButton", () -> {
            try {
                GuiView guiView = new GuiView(new Board(name_of_level));
                ExitableView newGame = new ExitableView(this, guiView);
                newGame.setScene(scene);

                scene.setRoot(newGame);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight);
    }

    public SelectLevel(String directory) throws FileNotFoundException {
        File file = new File(directory);
        String[] directories = file.list();
        listOfLevels = new LinkedList<>(List.of(directories));
        listOfLevels.sort(String::compareTo);

        int it = 0;
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        for(String name : listOfLevels) {
            VBox level = new VBox();
            level.setAlignment(Pos.CENTER);

            Label label = new Label(name);
            label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            level.getChildren().add(label);
            level.getChildren().add(createLevelPlayButton(directory + "/" + name));

            int sizeOfRow = 5;
            gridPane.add(level, it % sizeOfRow, it / sizeOfRow);
            it++;
        }
        getChildren().add(gridPane);
    }

    public List<String> getListOfLevels() {
        return listOfLevels;
    }
}
