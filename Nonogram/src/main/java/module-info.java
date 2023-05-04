module app.nonogram {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.nonogram to javafx.fxml;
    exports app.nonogram;
}