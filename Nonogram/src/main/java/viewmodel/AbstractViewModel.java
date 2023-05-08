package viewmodel;

import javafx.scene.paint.Color;
import model.Board;
import model.Game;
import model.SquareState;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractViewModel {
    protected Game game;
    protected Tool tool;
    protected List<Color> allowedColors;
    protected List<List<Clue>> rowClues;
    protected List<List<Clue>> columnClues;
    public Board getCurrentColoring(){
        return game.getCurrentColoring();
    }
    public List<Color> getColors(){
        return allowedColors;
    }
    public List<List<Clue>> getRowClues(){
        return rowClues;
    }
    public List<List<Clue>> getColumnClues(){
        return columnClues;
    }
    public void makeMove(int row, int col){
        tool.apply(game.getCurrentColoring().getSquare(row,col));
    }
    public void changeTool(SquareState s, Color c){
        if(s==SquareState.COLORED) tool=new ColorTool(c);
        if(s==SquareState.EMPTY) tool=new EmptyTool();
        if(s==SquareState.UNKNOWN) tool=new UnknownTool();
    }
    protected List<Clue> evaluateRow(int row){
        List<Clue> res=new ArrayList<>();
        for(int i=0;i<getCurrentColoring().getWidth();){
            while(i<getCurrentColoring().getWidth()&&
                    getCurrentColoring().getSquare(row,i).getState()!=SquareState.COLORED) i++;
            int counter=0;
            Color c= getCurrentColoring().getSquare(row,i).getColor();
            while(i<getCurrentColoring().getWidth()&&
                    getCurrentColoring().getSquare(row,i).getColor()==c) {i++;counter++;}
            if(i!=0) res.add(new Clue(counter,c));
        }
        return res;
    }
    private List<Clue> evaluateCol(int col){
        List<Clue> res=new ArrayList<>();
        for(int i=0;i<getCurrentColoring().getHeight();){
            while(i<getCurrentColoring().getHeight()&&
                    getCurrentColoring().getSquare(i,col).getState()!=SquareState.COLORED) i++;
            int counter=0;
            Color c= getCurrentColoring().getSquare(i,col).getColor();
            while(i<getCurrentColoring().getHeight()&&
                    getCurrentColoring().getSquare(i,col).getColor()==c) {i++;counter++;}
            if(i!=0) res.add(new Clue(counter,c));
        }
        return res;
    }
}
