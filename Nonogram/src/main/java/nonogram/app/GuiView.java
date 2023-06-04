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
import viewmodel.CreateViewModel;
import viewmodel.PlayViewModel;
import viewmodel.ViewModel;

import java.net.URL;
import java.util.*;

public class GuiView extends VBox{
    protected GridPane fullGridPane;
    protected GridPane gridPane;
    protected HBox tools;
    protected HBox columnClues;
    protected VBox rowClues;
    protected ViewModel v;
    protected int numRows;
    protected int numColumns;

    public GuiView(Board b){
        fullGridPane = new GridPane();
        v=new PlayViewModel(new Game(b,new Board(b.getHeight(),b.getWidth())));
        fullGridPane.setMaxHeight(Double.POSITIVE_INFINITY);
        fullGridPane.setMaxWidth(Double.POSITIVE_INFINITY);
        fullGridPane.setAlignment(Pos.CENTER);
        fullGridPane.setVgap(20);
        fullGridPane.setHgap(20);

        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        fullGridPane.getColumnConstraints().addAll(column1, column2);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        fullGridPane.getRowConstraints().addAll(row1, row2, row3);

        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        fullGridPane.add(gridPane, 1, 1);

        columnClues = new HBox();
        fullGridPane.add(columnClues, 1, 0);

        rowClues = new VBox();
        fullGridPane.add(rowClues, 0, 1);

        tools = new HBox();
        fullGridPane.add(tools, 1, 2);

        getChildren().add(fullGridPane);

        numRows=v.getCurrentColoring().getHeight();
        numColumns=v.getCurrentColoring().getWidth();
        addColumnClues();
        addRowClues();
        createGridPane();
        addColors();
        addErase();
        addEmpty();
    }

    protected void addColumnClues(){
        gridPane.getColumnConstraints().clear();
        columnClues.setAlignment(Pos.BOTTOM_CENTER);
        columnClues.setSpacing(2);
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
            columnClues.getChildren().add(sp);
        }
    }
    protected void addRowClues(){
        rowClues.setAlignment(Pos.CENTER_RIGHT);
        rowClues.setSpacing(2);
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
            rowClues.getChildren().add(sp);
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
