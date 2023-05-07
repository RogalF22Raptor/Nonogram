module nonogram.nonogram {
    requires javafx.controls;
    requires javafx.fxml;


    opens nonogram.app to javafx.fxml;
    exports nonogram.app;
}