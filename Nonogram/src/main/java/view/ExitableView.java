package view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class ExitableView extends VBox {
    protected ExitableView parentView;
    protected Scene scene;

    public ExitableView() {}

    public ExitableView(ExitableView parentView) {
        this.parentView = parentView;
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
