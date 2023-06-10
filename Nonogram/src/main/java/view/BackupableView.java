package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import model.FileIO;
import nonogram.app.CreateGuiView;

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 120, 60);
        saveButton.setAlignment(Pos.CENTER);
        toolBar.getItems().add(0, saveButton);
    }
}
