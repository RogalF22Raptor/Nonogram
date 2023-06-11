package nonogram.app;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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

    @Override
    public void notify(int x, int y, SquareState s, Color c) {
        super.notify(x, y, s, c);
        if(((PlayViewModel)v).isComplete()){
            Label won=new Label("YOU WON!!!");
            won.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            fullGridPane.add(won,1,3);
        }
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
                });
                gridPane.add(rec, col, row);
            }
        }
    }
}
