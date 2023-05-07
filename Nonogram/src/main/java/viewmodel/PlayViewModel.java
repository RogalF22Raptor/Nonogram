package viewmodel;

import javafx.scene.paint.Color;
import model.Board;
import model.Game;

public class PlayViewModel implements ViewModel{
    private final Game game;
    boolean complete=false;
    public PlayViewModel(Game game){
        this.game=game;
    }
    public boolean isComplete(){
        // TODO: check
        return false;
    }
    public void makeMove(int row, int col){
        //TODO: logic. should we do unknown->colors->empty? what exactly should click do?
    }
    private void markColor(int row, int col, Color color){
        game.getCurrentColoring().getSquare(row,col).setColor(color);
        if(isComplete()) complete=true;
    }
    private void MarkEmpty(int row,int col){
        game.getCurrentColoring().getSquare(row,col).setEmpty();
        if(isComplete()) complete=true;
    }
    private void MarkUnknown(int row,int col){
        game.getCurrentColoring().getSquare(row,col).setUnknown();
    }
}
