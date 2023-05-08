package viewmodel;

import model.Square;

public class EmptyTool implements Tool{

    @Override
    public void apply(Square x) {
        x.setEmpty();
    }
}
