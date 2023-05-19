package nonogram.app;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.*;
import viewmodel.PlayViewModel;

import java.net.URL;
import java.util.*;

public class GuiView implements Initializable {
    @FXML
    private GridPane fullGridPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private HBox tools;
    @FXML
    private HBox ColumnClues;
    @FXML
    private VBox RowClues;
    private PlayViewModel v = new PlayViewModel(new Game(new RandomBoard(5,5),new Board(5,5)));
    private int numRows = 5;
    private int numColumns = 5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fullGridPane.setVgap(20);
        fullGridPane.setHgap(20);

        addColumnClues();
        addRowClues();
        updateGridPane();
        addColors();
        addErase();
        addEmpty();
    }
    private void addColumnClues(){
        gridPane.getColumnConstraints().clear();
        for (int col = 0; col < numColumns; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0);
            columnConstraints.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
            VBox sp = new VBox();
            for(int i=0;i<v.getColumnClues().get(col).size();i++) {
                Rectangle r = new Rectangle(30, 30,Color.WHITE);
                Text t = new Text(v.getColumnClues().get(col).get(i).getNumber().toString());
                t.setFill(Color.BLACK);
                sp.getChildren().add(new StackPane(r,t));
            }
            ColumnClues.getChildren().add(sp);
        }
    }
    private void addRowClues(){
        gridPane.getRowConstraints().clear();
        for (int row = 0; row < numRows; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0);
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);
            HBox sp = new HBox();
            for(int i=0;i<v.getRowClues().get(row).size();i++) {
                Rectangle r = new Rectangle(30, 30,Color.WHITE);
                Text t = new Text(v.getRowClues().get(row).get(i).getNumber().toString());
                t.setFill(Color.BLACK);
                sp.getChildren().add(new StackPane(r,t));
            }
            RowClues.getChildren().add(sp);
        }
    }
    private void addColors(){
        for(int i=0;i<v.getColors().size();i++){
            Rectangle rectangle=new Rectangle(30,30,v.getColors().get(i));
            int place=i;
            rectangle.setOnMouseClicked(event ->{
                v.changeTool(SquareState.COLORED,v.getColors().get(place));
            });
            tools.getChildren().add(rectangle);
        }
    }
    private void addErase(){
        Node er=getImage("/nonogram/app/erase.png");
        er.setOnMouseClicked(event ->{
            v.changeTool(SquareState.UNKNOWN,null);
        });
        tools.getChildren().add(er);
    }
    private void addEmpty(){
        Node er=getImage("/nonogram/app/empty.png");
        er.setOnMouseClicked(event ->{
            v.changeTool(SquareState.EMPTY,null);
        });
        tools.getChildren().add(er);
    }
    private Node getImage(String path){
        Image iconImage=new Image(getClass().getResource(path).toExternalForm());
        ImageView iconView=new ImageView(iconImage);
        iconView.setFitWidth(30);
        iconView.setPreserveRatio(true);
        Rectangle rectangle=new Rectangle(30,30,Color.WHITE);
        StackPane er = new StackPane(rectangle,iconView);

        return er;
    }
    private void updateGridPane(){
        for (int row = 0; row < gridPane.getRowCount(); row++) {
            for (int col = 0; col < numColumns; col++) {
                Square x=v.getCurrentColoring().getSquare(row,col);
                Node rectangle = new Rectangle(30, 30, x.getState()== SquareState.COLORED ? x.getColor() : Color.WHITE);
                if(x.getState()==SquareState.EMPTY){
                    rectangle=getImage("/nonogram/app/empty.png");
                }
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
