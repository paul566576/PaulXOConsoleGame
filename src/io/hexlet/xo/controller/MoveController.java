package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exception.AlreadyOccupiedException;
import io.hexlet.xo.model.exception.InvalidPointException;

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
