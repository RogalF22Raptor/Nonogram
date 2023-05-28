package nonogram.app;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.*;
import view.ExitableView;
import viewmodel.PlayViewModel;
import viewmodel.ViewModel;

import java.net.URL;
import java.util.*;

public class GuiView extends VBox implements Initializable {
    @FXML
    protected GridPane fullGridPane;
    @FXML
    protected GridPane gridPane;
    @FXML
    protected HBox tools;
    @FXML
    protected HBox ColumnClues;
    @FXML
    protected VBox RowClues;
    protected ViewModel v = new PlayViewModel(new Game(new RandomBoard(10,5),new Board(10,5)));
    protected int numRows = 10;
    protected int numColumns = 5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fullGridPane.setVgap(20);
        fullGridPane.setHgap(20);

        addColumnClues();
        addRowClues();
        createGridPane();
        addColors();
        addErase();
        addEmpty();
    }
    protected void addColumnClues(){
        gridPane.getColumnConstraints().clear();
        ColumnClues.setAlignment(Pos.BOTTOM_CENTER);
        ColumnClues.setSpacing(2);
        for (int col = 0; col < numColumns; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            gridPane.getColumnConstraints().add(columnConstraints);
            VBox sp = new VBox();
            sp.setAlignment(Pos.BOTTOM_CENTER);
            for(int i=0;i<v.getColumnClues().get(col).size();i++) {
                Rectangle r = new Rectangle(30, 30,Color.WHITE);
                Text t = new Text(v.getColumnClues().get(col).get(i).getNumber().toString());
                t.setFill(v.getColumnClues().get(col).get(i).getColor());
                sp.getChildren().add(new StackPane(r,t));
            }
            ColumnClues.getChildren().add(sp);
        }
    }
    protected void addRowClues(){
        RowClues.setAlignment(Pos.CENTER_RIGHT);
        RowClues.setSpacing(2);
        gridPane.getRowConstraints().clear();
        for (int row = 0; row < numRows; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            gridPane.getRowConstraints().add(rowConstraints);
            HBox sp = new HBox();
            sp.setAlignment(Pos.CENTER_RIGHT);
            for(int i=0;i<v.getRowClues().get(row).size();i++) {
                Rectangle r = new Rectangle(30, 30,Color.WHITE);
                Text t = new Text(v.getRowClues().get(row).get(i).getNumber().toString());
                t.setFill(v.getRowClues().get(row).get(i).getColor());
                sp.getChildren().add(new StackPane(r,t));
            }
            RowClues.getChildren().add(sp);
        }
    }
    protected void addColors(){
        for(int i=0;i<v.getColors().size();i++){
            Rectangle rectangle=new Rectangle(30,30,v.getColors().get(i));
            int place=i;
            rectangle.setOnMouseClicked(event ->{
                v.changeTool(SquareState.COLORED,v.getColors().get(place));
            });
            tools.getChildren().add(rectangle);
        }
    }
    protected void addErase(){
        Node er=getImage("/nonogram/app/erase.png");
        er.setOnMouseClicked(event ->{
            v.changeTool(SquareState.UNKNOWN,null);
        });
        tools.getChildren().add(er);
    }
    protected void addEmpty(){
        Node er=getImage("/nonogram/app/empty.png");
        er.setOnMouseClicked(event ->{
            v.changeTool(SquareState.EMPTY,null);
        });
        tools.getChildren().add(er);
    }
    protected Node getImage(String path){
        Image iconImage=new Image(getClass().getResource(path).toExternalForm());
        ImageView iconView=new ImageView(iconImage);
        iconView.setFitWidth(30);
        iconView.setPreserveRatio(true);
        Rectangle rectangle=new Rectangle(30,30,Color.WHITE);
        StackPane er = new StackPane(rectangle,iconView);

        return er;
    }
    protected void createGridPane(){
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setAlignment(Pos.CENTER);
        for (int row = 0; row < gridPane.getRowCount(); row++) {
            for (int col = 0; col < numColumns; col++) {
                Square x=v.getCurrentColoring().getSquare(row,col);
                Rectangle rectangle = new Rectangle(30, 30, x.getState()== SquareState.COLORED ? x.getColor() : Color.WHITE);
                Node image=getImage("/nonogram/app/empty.png");;
                StackPane rec=new StackPane();
                rec.getChildren().addAll(image,rectangle);
                int finalRow = row;
                int finalCol = col;
                rec.setOnMouseClicked(event -> {
                    Color color=Color.WHITE;
                    v.makeMove(finalRow, finalCol);
                    if(v.getCurrentColoring().getSquare(finalRow,finalCol).getState()==SquareState.COLORED) {
                        color = v.getCurrentColoring().getSquare(finalRow, finalCol).getColor();
                    }else if(v.getCurrentColoring().getSquare(finalRow,finalCol).getState()==SquareState.EMPTY){
                        color=Color.TRANSPARENT;
                    }
                    rectangle.setFill(color);
                });
                gridPane.add(rec, col, row);
            }
        }
    }
}
