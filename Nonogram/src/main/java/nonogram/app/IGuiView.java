package nonogram.app;

import javafx.scene.paint.Color;
import model.SquareState;

public interface IGuiView {
    public void notify(int x, int y, SquareState s, Color c);
}
