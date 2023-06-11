package viewmodel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import model.Board;
import model.Game;
import model.Square;
import model.SquareState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayViewModel extends AbstractViewModel{
    public PlayViewModel(Game game){
        this.game = game;
        this.rowClues=new ArrayList<>();
        for(int i=0;i<game.getCorrectColoring().getHeight();i++){
            this.rowClues.add(evaluateRow(game.getCorrectColoring(),i));
        }
        this.columnClues=new ArrayList<>();
        for(int i=0;i<game.getCorrectColoring().getWidth();i++){
            this.columnClues.add(evaluateCol(game.getCorrectColoring(),i));
        }
        Set<Color> c =new HashSet<>();
        for(int i=0;i<game.getCorrectColoring().getHeight();i++){
            for(int j=0;j<game.getCorrectColoring().getWidth();j++){
                if(game.getCorrectColoring().getSquare(i,j).getState()==SquareState.COLORED) {
                    c.add(game.getCorrectColoring().getSquare(i, j).getColor());
                }
            }
        }
        this.allowedColors=new ArrayList<>(c);
        if(allowedColors.isEmpty()) this.tool=new EmptyTool();
        else this.tool=new ColorTool(allowedColors.get(0));
    }
    public boolean isComplete(){
        for(int i=0;i<getCurrentColoring().getHeight();i++){
            if(!getRowClues().get(i).equals(evaluateRow(getCurrentColoring(),i))){
                return false;
            }
        }
        for(int i=0;i<getCurrentColoring().getWidth();i++){
            if(!getColumnClues().get(i).equals(evaluateCol(getCurrentColoring(),i))){
                return false;
            }
        }
        return true;
    }
}
