package viewmodel;

import model.Square;

public class UnknownTool implements Tool{
    @Override
    public void apply(Square x) {
        x.setUnknown();
    }
}
