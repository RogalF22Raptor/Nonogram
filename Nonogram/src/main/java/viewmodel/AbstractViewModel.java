package viewmodel;

import javafx.scene.paint.Color;
import model.Board;
import model.Game;
import model.SquareState;
import nonogram.app.IGuiView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractViewModel implements ViewModel{
    protected IGuiView observer;
    protected Game game;
    protected Tool tool;
    protected List<Color> allowedColors;
    protected List<List<Clue>> rowClues;
    protected List<List<Clue>> columnClues;
    public void subscribe(IGuiView obs){
        observer=obs;
    }
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
        observer.notify(row,col,game.getCurrentColoring().getSquare(row,col).getState(),
                game.getCurrentColoring().getSquare(row,col).getColor());
    }
    public void changeTool(SquareState s, Color c){
        if(s==SquareState.COLORED) tool=new ColorTool(c);
        if(s==SquareState.EMPTY) tool=new EmptyTool();
        if(s==SquareState.UNKNOWN) tool=new UnknownTool();
    }
    protected List<Clue> evaluateRow(Board b,int row){
        List<Clue> res=new ArrayList<>();
        for(int i=0;i<b.getWidth();){
            while(i<b.getWidth()&&
                    b.getSquare(row,i).getState()!=SquareState.COLORED) i++;
            if(i==b.getWidth()) break;
            int counter=0;
            Color c= b.getSquare(row,i).getColor();
            while(i<b.getWidth()&&b.getSquare(row,i).getState()==SquareState.COLORED&&
                    b.getSquare(row,i).getColor().equals(c)) {i++;counter++;}
            if(i!=0) res.add(new Clue(counter,c));
        }
        if(res.isEmpty())res.add(new Clue(0,Color.TRANSPARENT));
        return res;
    }
    protected List<Clue> evaluateCol(Board b,int col){
        List<Clue> res=new ArrayList<>();
        for(int i=0;i<b.getHeight();){
            while(i<b.getHeight()&&
                    b.getSquare(i,col).getState()!=SquareState.COLORED) i++;
            if(i==b.getHeight()) break;
            int counter=0;
            Color c= b.getSquare(i,col).getColor();
            while(i<b.getHeight()&&b.getSquare(i,col).getState()==SquareState.COLORED&&
                    b.getSquare(i,col).getColor().equals(c)) {i++;counter++;}
            if(i!=0) res.add(new Clue(counter,c));
        }
        if(res.isEmpty())res.add(new Clue(0,Color.TRANSPARENT));
        return res;
    }
    public void save(String path) throws IOException {
        game.saveToFile(path);//TODO Something xdd
    }

}
