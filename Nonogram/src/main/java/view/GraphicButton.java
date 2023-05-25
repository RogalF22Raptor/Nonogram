package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GraphicButton extends Button {
    private double width, height;
    private ImageView getImageView(String source) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(source);
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    GraphicButton(String source, Runnable action, double width, double height) throws FileNotFoundException {
        this.width = width;
        this.height = height;

        ImageView outImage = getImageView(source + "/out.png");
        ImageView inImage = getImageView(source + "/in.png");

        setBackground(null);
        setGraphic(outImage);

        addEventHandler(MouseEvent.MOUSE_ENTERED, e -> setGraphic(inImage));
        addEventHandler(MouseEvent.MOUSE_EXITED, e -> setGraphic(outImage));

        setOnMouseClicked(e -> action.run());
    }
}
