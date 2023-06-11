package viewmodel;

import javafx.scene.paint.Color;
import model.Board;
import model.Game;
import model.SquareState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateViewModel extends AbstractViewModel{

    public CreateViewModel(int h, int w){
        game=new Game(new Board(h,w),new Board(h,w));
        allowedColors= Arrays.asList(Color.BLACK, Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.ORANGE,Color.PURPLE);
        this.rowClues=new ArrayList<>();
        for(int i=0;i<game.getCorrectColoring().getHeight();i++){
            this.rowClues.add(evaluateRow(game.getCorrectColoring(),i));
        }
        this.columnClues=new ArrayList<>();
        for(int i=0;i<game.getCorrectColoring().getWidth();i++){
            this.columnClues.add(evaluateCol(game.getCorrectColoring(),i));
        }
        this.tool=new ColorTool(allowedColors.get(0));
    }
    @Override
    public void makeMove(int row, int col) {
        super.makeMove(row, col);
        rowClues.set(row,evaluateRow(getCurrentColoring(),row));
        columnClues.set(col,evaluateCol(getCurrentColoring(),col));
        observer.notify(row,col,game.getCurrentColoring().getSquare(row,col).getState(),
                game.getCurrentColoring().getSquare(row,col).getColor());
    }

}
