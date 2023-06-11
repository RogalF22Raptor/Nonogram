package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BackupableView extends ExitableView {
    public BackupableView(ExitableView parentView, CreateGuiView content) throws FileNotFoundException {
        super(parentView, content);

        TextField textField = new TextField();
        textField.setPromptText("Name of file");
        toolBar.getItems().add(0, textField);

        GraphicButton saveButton = new GraphicButton("src/main/resources/nonogram/app/saveButton", () -> {
            try {
                content.getV().getCurrentColoring().saveToFile("files/userLevels/" + textField.getText());

                Label savedMessage = new Label("Saved!");
                savedMessage.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                toolBar.getItems().add(2, savedMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 120, 60);
        saveButton.setAlignment(Pos.CENTER);
        toolBar.getItems().add(0, saveButton);
    }
}
