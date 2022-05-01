package test;

import model.NSG;
import model.Ninja;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NinjaTest {

    private static final int Y_POS = NSG.GAME_HEIGHT / 2;
    private Ninja ninja;

    @BeforeEach
    public void runBefore() {
        ninja = new Ninja(Y_POS);
    }

    @Test
    public void testConstructor() {
        assertEquals(NSG.GAME_HEIGHT / 2, ninja.getY());
        assertFalse(ninja.getDirection());
    }

    @Test
    public void testUpdateUp() {
        int numUpdates = 5;

        ninja.move();
        assertEquals(Y_POS + Ninja.DY, ninja.getY());
        assertEquals(ninja.X_POS, ninja.getX());

        for(int i = 1; i < numUpdates; i++) {
            ninja.move();
        }

        assertEquals(Y_POS + numUpdates * Ninja.DY, ninja.getY());
        assertEquals(ninja.X_POS, ninja.getX());
    }

    @Test
    public void testUpdateDown() {
        int numUpdates = 5;
        ninja.changeDirection();

        ninja.move();
        assertEquals(Y_POS - Ninja.DY, ninja.getY());
        assertEquals(ninja.X_POS, ninja.getX());

        for(int i = 1; i < numUpdates; i++) {
            ninja.move();
        }

        assertEquals(Y_POS - numUpdates * Ninja.DY, ninja.getY());
        assertEquals(ninja.X_POS, ninja.getX());
    }

    @Test
    public void testUpdateUpAndDown() {
        int numUpdates = 5;

        for(int i = 1; i < numUpdates; i++) {
            ninja.move();
        }

        ninja.changeDirection();

        for(int i = 1; i < numUpdates; i++) {
            ninja.move();
        }

        assertEquals(Y_POS, ninja.getY());
        assertEquals(ninja.X_POS, ninja.getX());
    }

    @Test
    public void testChangeDirection() {
        ninja.changeDirection();
        assertTrue(ninja.getDirection());
        ninja.changeDirection();
        assertFalse(ninja.getDirection());
    }

    @Test
    public void testBoundaryTop() {
        ninja.changeDirection();

        while (ninja.getY() > 0) {
            ninja.move();
        }
        assertEquals(0, ninja.getY());

        ninja.move();
        assertEquals(0, ninja.getY());
    }

    @Test
    public void testBoundaryBottom() {
        while (ninja.getY() < NSG.GAME_HEIGHT) {
            ninja.move();
        }
        assertEquals(NSG.GAME_HEIGHT, ninja.getY());

        ninja.move();
        assertEquals(NSG.GAME_HEIGHT, ninja.getY());
    }
}
