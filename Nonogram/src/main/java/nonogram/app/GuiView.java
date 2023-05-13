package nonogram.app;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;
import viewmodel.PlayViewModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GuiView implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private HBox tools;
    private PlayViewModel v = new PlayViewModel(new Game(new RandomBoard(5,5),new RandomBoard(5,5)));
    private int numRows = 5;
    private int numColumns = 5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridPane.getColumnConstraints().clear();
        for (int col = 0; col < numColumns; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / numColumns);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        gridPane.getRowConstraints().clear();
        for (int row = 0; row < numRows; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / numColumns);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        updateGridPane();
        for(int i=0;i<v.getColors().size();i++){
            Rectangle rectangle=new Rectangle(30,30,v.getColors().get(i));
            int place=i;
            rectangle.setOnMouseClicked(event ->{
                v.changeTool(SquareState.COLORED,v.getColors().get(place));
            });
            tools.getChildren().add(rectangle);
        }
        Rectangle rectangle=new Rectangle(30,30,Color.GRAY);
        rectangle.setOnMouseClicked(event ->{
            v.changeTool(SquareState.EMPTY,null);
        });
        tools.getChildren().add(rectangle);
    }

    private void updateGridPane(){
        System.out.println(gridPane.getRowCount()+" "+gridPane.getColumnCount());
        for (int row = 0; row < gridPane.getRowCount(); row++) {
            for (int col = 0; col < numColumns; col++) {
                Square x=v.getCurrentColoring().getSquare(row,col);
                Rectangle rectangle = new Rectangle(30, 30, x.getState()== SquareState.COLORED ? x.getColor() : Color.WHITE);
                int finalRow = row;
                int finalCol = col;
                rectangle.setOnMouseClicked(event -> {
                    v.makeMove(finalRow, finalCol);
                    updateGridPane();
                });
                gridPane.add(rectangle, col, row);
            }
        }
    }


}
