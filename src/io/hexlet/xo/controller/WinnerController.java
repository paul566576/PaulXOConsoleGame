package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exception.InvalidPointException;

import java.awt.*;

public class WinnerController {

    public Figure getWinner(final Field field) {
        try {
            for (int i = 0; i < 3; i++) {
                if (check(field, new Point(i,0), p -> new Point(p.x, p.y + 1))) {
                    return field.getFigure(new Point(i,0));
                }
            }

            for (int i = 0; i < 3; i++) {
                if (check(field, new Point(0,i), p -> new Point(p.x + 1, p.y))) {
                    return field.getFigure(new Point(0, i));
                }
            }

            for (int i = 0; i < 3; i++) {
                if (check(field, new Point(0,0), p -> new Point(p.x + 1, p.y + 1))) {
                    return field.getFigure(new Point(0,0));
                }
            }

            for (int i = 0; i < 3; i++) {
                if (check(field, new Point(0,2), p -> new Point(p.x + 1, p.y - 1))) {
                    return field.getFigure(new Point(1, 1));
                }
            }
        } catch(InvalidPointException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean check(final Field field,
                         final Point currentPoint,
                         final IPointGenerator pointGenerator) {
        final Figure currenFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);
        try {
            currenFigure = field.getFigure(currentPoint);

            if (currenFigure == null) return false;

            nextFigure = field.getFigure(nextPoint);
        }catch (InvalidPointException e) {
            return true;
        }

        if (currenFigure != nextFigure) return false;

        return check(field, nextPoint, pointGenerator);
    }

    private interface IPointGenerator {

        Point next(final Point point);
    }
}
