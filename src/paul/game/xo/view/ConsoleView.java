package paul.game.xo.view;

import paul.game.xo.controller.CurrentMoveController;
import paul.game.xo.controller.MoveController;
import paul.game.xo.controller.WinnerController;
import paul.game.xo.model.Field;
import paul.game.xo.model.Figure;
import paul.game.xo.model.Game;
import paul.game.xo.model.exception.AlreadyOccupiedException;
import paul.game.xo.model.exception.InvalidPointException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    private CurrentMoveController currentMoveController = new CurrentMoveController();

    private WinnerController winnerController = new WinnerController();

    private MoveController moveController = new MoveController();

    public void show(final Game game) {
        System.out.format("Game name: %s", game.getName());
        System.out.println();
        final Field field = game.getField();
        for (int i = 0; i < field.getSize(); i++) {
            if (i != 0)  printSeparator();
            printLine(field, i);
        }

    }

    public boolean move(final Game game) throws InvalidPointException, AlreadyOccupiedException {
        final Field field = game.getField();
        final Figure winner = winnerController.getWinner(field);
        if (winner != null) {
            System.out.format("Winner is %s\n", winner);
            return false;
        }

        final Figure currentFigure = currentMoveController.currentMove(field);
        if (currentFigure == null) {
                System.out.println("No winner and no moves left!");
                return false;
        }
        System.out.format("Please enter move point for: %s\n", currentFigure);
        final Point point = askPoint();
        try {
            moveController.applyFigure(field, point, currentFigure);
        }catch(InvalidPointException | AlreadyOccupiedException e) {
            System.out.println("Point is invalid !!!");
        }

        return true;
    }

    private Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("O") - 1);
    }

    private int askCoordinate(final String coordinateName) {
        System.out.format("Please input %s", coordinateName);
        final Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch(final InputMismatchException e) {
            return askCoordinate(coordinateName);
        }
    }

    private void printLine(final Field field, final int i) {
        for (int j = 0; j < field.getSize(); j++) {
            if (j != 0) System.out.print("|");
            final Figure figure;
            try {
                figure = field.getFigure(new Point(i, j));
            }catch(final InvalidPointException e) {
                throw new RuntimeException();
            }
            System.out.print(figure != null ? figure : " ");
        }
        System.out.println();
    }

    private void printSeparator() {
        System.out.println("~~~~~~~");
    }
}
