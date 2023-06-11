package view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import nonogram.app.CreateGuiView;
import nonogram.app.GuiView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SelectSize extends ExitableView {
    private final double buttonHeight = 150;
    private final double buttonWidth = 225;
    private GridPane gridPane;
    ComboBox<Integer> HnumberComboBox = new ComboBox<>();
    ComboBox<Integer> WnumberComboBox = new ComboBox<>();
    SelectSize() throws FileNotFoundException{
        for (int i = 2; i <= 20; i++) {
            HnumberComboBox.getItems().add(i);
            WnumberComboBox.getItems().add(i);
        }
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        HnumberComboBox.setValue(2);
        WnumberComboBox.setValue(2);
        VBox level = new VBox();
        level.setAlignment(Pos.CENTER);

        Label label = new Label("Create");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        level.getChildren().add(label);

        level.getChildren().add(new GraphicButton("src/main/resources/nonogram/app/playButton", () -> {
            try {
                CreateGuiView guiView = new CreateGuiView(HnumberComboBox.getValue(), WnumberComboBox.getValue());
                BackupableView newGame = new BackupableView(this, guiView);
                newGame.setScene(scene);

                scene.setRoot(newGame);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight));
        gridPane.add(HnumberComboBox,100,100);
        gridPane.add(WnumberComboBox,150,100);
        gridPane.add(level,200,300);
        getChildren().add(gridPane);
    }
}