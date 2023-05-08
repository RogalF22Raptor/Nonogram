package viewmodel;

import javafx.scene.paint.Color;
import model.Board;
import model.Game;
import model.Square;
import model.SquareState;

import java.util.ArrayList;
import java.util.List;

public class PlayViewModel extends AbstractViewModel{
    public PlayViewModel(Game game){
        this.game = game;
    }

}
