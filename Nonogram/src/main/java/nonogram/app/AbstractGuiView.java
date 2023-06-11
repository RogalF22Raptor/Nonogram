package nonogram.app;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.SquareState;
import viewmodel.ViewModel;

public class AbstractGuiView extends VBox implements IGuiView {
    protected GridPane fullGridPane;
    protected GridPane gridPane;
    protected HBox tools;
    protected HBox columnClues;
    protected VBox rowClues;
    protected ViewModel v;
    protected int numRows = 5;
    protected int numColumns = 5;
    public AbstractGuiView(){
        fullGridPane = new GridPane();

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
    }

    protected void addColumnClues(){
        columnClues.setAlignment(Pos.BOTTOM_CENTER);
        columnClues.setSpacing(2);
        for (int col = 0; col < numColumns; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            gridPane.getColumnConstraints().add(columnConstraints);
            VBox sp = new VBox();
            sp.setAlignment(Pos.BOTTOM_CENTER);
            for(int i=0;i<v.getColumnClues().get(col).size();i++) {
                Rectangle r = new Rectangle(30, 30, Color.WHITE);
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
    protected void updateRowClues(int row){
        HBox sp = new HBox();
        sp.setAlignment(Pos.CENTER_RIGHT);
        for(int i=0;i<v.getRowClues().get(row).size();i++) {
            Rectangle r = new Rectangle(30, 30,Color.WHITE);
            Text t = new Text(v.getRowClues().get(row).get(i).getNumber().toString());
            t.setFill(v.getRowClues().get(row).get(i).getColor());
            sp.getChildren().add(new StackPane(r,t));
        }
        rowClues.getChildren().set(row,sp);
    }
    protected void updateColClues(int col){
        VBox sp = new VBox();
        sp.setAlignment(Pos.BOTTOM_CENTER);
        for(int i=0;i<v.getColumnClues().get(col).size();i++) {
            Rectangle r = new Rectangle(30, 30,Color.WHITE);
            Text t = new Text(v.getColumnClues().get(col).get(i).getNumber().toString());
            t.setFill(v.getColumnClues().get(col).get(i).getColor());
            sp.getChildren().add(new StackPane(r,t));
        }
        columnClues.getChildren().set(col,sp);
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
    public void notify(int x,int y,SquareState s,Color c){
        Node node = gridPane.getChildren().get(y + x * numColumns+1);
        if (node instanceof StackPane rectangle) {
            Node nn = rectangle.getChildren().get(1);
            if(nn instanceof Rectangle rr){
                if (s == SquareState.COLORED) {
                    rr.setFill(c);
                } else if(s==SquareState.EMPTY){
                    rr.setFill(Color.TRANSPARENT);
                }else{
                    rr.setFill(Color.WHITE);
                }
            }
        }
    }
    public ViewModel getV() {
        return v;
    }
}
