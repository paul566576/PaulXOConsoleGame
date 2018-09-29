package paul.game.xo;

import paul.game.xo.model.Field;
import paul.game.xo.model.Figure;
import paul.game.xo.model.Game;
import paul.game.xo.model.Player;
import paul.game.xo.model.exception.AlreadyOccupiedException;
import paul.game.xo.model.exception.InvalidPointException;
import paul.game.xo.view.ConsoleView;

import java.util.Scanner;

public class XOCLI {

    public static void main(final String[] args) throws AlreadyOccupiedException, InvalidPointException {
        final int FIELD_SIZE = 3;
        final String name1 = playerNameInput(1);
        final String name2 = playerNameInput(2);

        final Player[] players = new Player[2];
        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);
        Field field = null;
        try {
            field = new Field(FIELD_SIZE);
        } catch (Exception e){
            e.printStackTrace();
        }
        final Game gameXO = new Game(players, field, "XO");
        final ConsoleView consoleView = new ConsoleView();
        consoleView.show(gameXO);
        while(consoleView.move(gameXO)) {
            consoleView.show(gameXO);
        }
    }

    static String playerNameInput(final int count) {
        Scanner sc = new Scanner(System.in);
        System.out.format("Enter Player %s  name: ", count);
        String name = sc.nextLine();
        return name;
    }

}
