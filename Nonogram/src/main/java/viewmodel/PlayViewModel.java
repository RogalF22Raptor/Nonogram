package viewmodel;

import javafx.scene.paint.Color;
import model.Board;
import model.Game;
import model.Square;
import model.SquareState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayViewModel extends AbstractViewModel{
    public PlayViewModel(Game game){//TODO add loading from file
        this.game = game;

        this.tool=new UnknownTool();
        this.rowClues=new ArrayList<>();
        for(int i=0;i<game.getCorrectColoring().getHeight();i++){
            this.rowClues.add(evaluateRow(game.getCorrectColoring(),i));
        }
        this.columnClues=new ArrayList<>();
        for(int i=0;i<game.getCorrectColoring().getWidth();i++){
            this.columnClues.add(evaluateRow(game.getCorrectColoring(),i));
        }
    }
    public boolean isComplete(){
        for(int i=0;i<getCurrentColoring().getHeight();i++){
            if(!getRowClues().get(i).equals(evaluateRow(getCurrentColoring(),i))){
                return false;
            }
        }
        for(int i=0;i<getCurrentColoring().getHeight();i++){
            if(!getColumnClues().get(i).equals(evaluateCol(getCurrentColoring(),i))){
                return false;
            }
        }
        return true;
    }
}
