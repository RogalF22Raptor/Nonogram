package view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public class ExitableView extends VBox {
    protected ExitableView parentView;
    protected Scene scene;
    protected ToolBar toolBar;

    public ExitableView() throws FileNotFoundException {
        toolBar = new ToolBar();
        toolBar.setBackground(Background.fill(Color.DARKTURQUOISE));
        getChildren().add(toolBar);

        Pane toolbarFilling = new Pane();
        HBox.setHgrow(toolbarFilling, Priority.ALWAYS);
        toolBar.getItems().add(toolbarFilling);

        GraphicButton exitButton = new GraphicButton("src/main/resources/nonogram/app/exitButton", this::exit, 120, 60);
        exitButton.setAlignment(Pos.CENTER);
        toolBar.getItems().add(exitButton);

        setBackground(Background.fill(Color.LIGHTCYAN));
    }

    public ExitableView(ExitableView parentView, Node content) throws FileNotFoundException {
        this();
        this.parentView = parentView;
        getChildren().add(content);
    }

    protected void exit() {
        if(parentView == null) {
            Platform.exit();
            return;
        }
        scene.setRoot(parentView);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setParentView(ExitableView parentView) {
        this.parentView = parentView;
    }
}
