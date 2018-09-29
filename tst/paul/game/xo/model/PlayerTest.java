package paul.game.xo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testGetName() throws Exception {
        final String testValue = "Slava";
        final String ExpectedValue = testValue;

        final Player player = new Player(testValue, null);

        final String actualValue = player.getName();

        assertEquals(ExpectedValue, actualValue);
    }

    @Test
    public void testGetFigure() throws Exception {
        final Figure testValue = Figure.X;
        final Figure ExpectedValue = testValue;

        final Player player = new Player(null, testValue);

        final Figure actualValue = player.getFigure();

        assertEquals(ExpectedValue, actualValue);
    }
}