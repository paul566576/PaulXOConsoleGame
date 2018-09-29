package paul.game.xo.controller;

import paul.game.xo.model.Field;
import paul.game.xo.model.Figure;
import paul.game.xo.model.exception.AlreadyOccupiedException;
import paul.game.xo.model.exception.InvalidPointException;

import java.awt.*;

public class MoveController {

    public void applyFigure(final Field field,
                            final Point point,
                            final Figure figure) throws AlreadyOccupiedException, InvalidPointException {
        if(field.getFigure(point) != null) {
            throw new AlreadyOccupiedException();
        }

        field.setFigure(point, figure);
    }
}
