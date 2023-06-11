package nonogram.app;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;
import viewmodel.PlayViewModel;


public class GuiView extends AbstractGuiView{

    public GuiView(Board b){
        v=new PlayViewModel(new Game(b,new Board(b.getHeight(),b.getWidth())));
        v.subscribe(this);
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


    protected void createGridPane(){
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setAlignment(Pos.CENTER);
        for (int row = 0; row < gridPane.getRowCount(); row++) {
            for (int col = 0; col < numColumns; col++) {
                Rectangle rectangle = new Rectangle(30, 30, Color.WHITE);
                Node image=getImage("/nonogram/app/empty.png");;
                StackPane rec=new StackPane();
                rec.getChildren().addAll(image,rectangle);
                int finalRow = row;
                int finalCol = col;
                rec.setOnMouseClicked(event -> {
                    v.makeMove(finalRow,finalCol);
                    /*Color color=Color.WHITE;
                    v.makeMove(finalRow, finalCol);
                    if(v.getCurrentColoring().getSquare(finalRow,finalCol).getState()==SquareState.COLORED) {
                        color = v.getCurrentColoring().getSquare(finalRow, finalCol).getColor();
                    }else if(v.getCurrentColoring().getSquare(finalRow,finalCol).getState()==SquareState.EMPTY){
                        color=Color.TRANSPARENT;
                    }
                    rectangle.setFill(color);
                    updateRowClues(finalRow);
                    updateColClues(finalCol);*/
                });
                gridPane.add(rec, col, row);
            }
        }
    }
}
