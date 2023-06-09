package viewmodel;

import javafx.scene.paint.Color;
import model.Board;
import model.SquareState;
import view.IGuiView;

import java.io.IOException;
import java.util.List;

public interface ViewModel {
    public void makeMove(int row, int col);
    public void changeTool(SquareState s, Color c);
    public Board getCurrentColoring();
    public List<Color> getColors();
    public List<List<Clue>> getRowClues();
    public List<List<Clue>> getColumnClues();
    public void save(String path) throws IOException;
    public void subscribe(IGuiView i);
}
