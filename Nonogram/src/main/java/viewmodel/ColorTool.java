package viewmodel;

import javafx.scene.paint.Color;
import model.Square;

public class ColorTool implements Tool{
    private Color color;
    public ColorTool(Color color){
        this.color=color;
    }
    public void apply(Square x){
        x.setColor(color);
    }
}
