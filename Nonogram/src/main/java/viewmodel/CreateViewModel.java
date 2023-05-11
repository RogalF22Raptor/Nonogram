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
    //TODO: all xdd

    public CreateViewModel(int h, int w){
        game=new Game(new Board(h,w),new Board(h,w));
        allowedColors= Arrays.asList(Color.BLACK, Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.ORANGE,Color.PURPLE);
    }
    @Override
    public void makeMove(int row, int col) {
        super.makeMove(row, col);
        rowClues.set(row,evaluateRow(getCurrentColoring(),row));
        columnClues.set(col,evaluateCol(getCurrentColoring(),col));
    }

}
