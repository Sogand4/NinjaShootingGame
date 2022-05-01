package test;

import model.NSG;
import model.ThrowingStar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThrowingStarTest {

    private static final int X_POS = NSG.GAME_WIDTH / 3;
    private static final int Y_POS = NSG.GAME_HEIGHT / 2;
    private ThrowingStar star;

    @BeforeEach
    public void runBefore() {
        star = new ThrowingStar(X_POS, Y_POS);
    }

    @Test
    public void testUpdate() {
        int numUpdates = 10;

        star.move();
        assertEquals(X_POS + ThrowingStar.DX, star.getX());
        assertEquals(Y_POS, star.getY());

        for(int i = 1; i < numUpdates; i++) {
            star.move();
        }

        assertEquals(X_POS + numUpdates * ThrowingStar.DX, star.getX());
        assertEquals(Y_POS, star.getY());
    }
}
