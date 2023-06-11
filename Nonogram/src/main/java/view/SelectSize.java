package view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

    SelectSize() throws FileNotFoundException{
        ComboBox<Integer> HnumberComboBox = new ComboBox<>();
        ComboBox<Integer> WnumberComboBox = new ComboBox<>();

        for (int i = 2; i <= 20; i++) {
            HnumberComboBox.getItems().add(i);
            WnumberComboBox.getItems().add(i);
        }

        HnumberComboBox.setValue(5);
        WnumberComboBox.setValue(5);

        Label labelH = new Label("Height: ");
        labelH.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        Label labelW = new Label("Width: ");
        labelW.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(new GraphicButton("src/main/resources/nonogram/app/createButton", () -> {
            try {
                CreateGuiView guiView = new CreateGuiView(HnumberComboBox.getValue(), WnumberComboBox.getValue());
                BackupableView newGame = new BackupableView(this, guiView);
                newGame.setScene(scene);

                scene.setRoot(newGame);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, buttonWidth, buttonHeight));
        getChildren().add(gridPane);

        toolBar.getItems().add(0, labelH);
        toolBar.getItems().add(1, HnumberComboBox);

        toolBar.getItems().add(2, labelW);
        toolBar.getItems().add(3, WnumberComboBox);
    }
}