package test;

import model.NSG;
import model.Ninja;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NSGTest {

    private NSG gameEasy;

    @BeforeEach
    public void runBefore() {
        gameEasy = new NSG(1);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, gameEasy.getDifficulty());
        assertFalse(gameEasy.getGameOver());
        assertEquals(2, gameEasy.getSprites().size());
        assertEquals(NSG.GAME_HEIGHT / 2, gameEasy.getNinja().getY());
    }

    @Test
    public void testUpdate() {
        Ninja n = gameEasy.getNinja();
        assertEquals(NSG.GAME_HEIGHT / 2, n.getY());
        gameEasy.update();
        assertEquals(NSG.GAME_HEIGHT / 2 + Ninja.DY, n.getY());
        gameEasy.update();
        assertEquals(NSG.GAME_HEIGHT / 2 + 2 * Ninja.DY, n.getY());
    }

    @Test
    public void testKeyEventUpAndDown() {
        Ninja n = gameEasy.getNinja();
        gameEasy.keyPressed(KeyEvent.VK_UP);
        gameEasy.update();
        assertEquals(NSG.GAME_HEIGHT / 2 + Ninja.DY, n.getY());
        gameEasy.update();
        assertEquals(NSG.GAME_HEIGHT / 2 + 2 * Ninja.DY, n.getY());
        gameEasy.keyPressed(KeyEvent.VK_DOWN);
        gameEasy.update();
        assertEquals(NSG.GAME_HEIGHT / 2 + Ninja.DY, n.getY());
        gameEasy.update();
        assertEquals(NSG.GAME_HEIGHT / 2, n.getY());
    }

    @Test
    public void testSpaceKeyEventForEasyGame() {
        gameEasy.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(gameEasy.getSprites().size() == 3);

        for (int i = 1; i < NSG.MAX_THROWING_STARS_EASY; i++) {
            gameEasy.keyPressed(KeyEvent.VK_SPACE);
        }

        assertTrue(gameEasy.getSprites().size() == 2 + NSG.MAX_THROWING_STARS_EASY);
        assertEquals(NSG.MAX_THROWING_STARS_EASY, gameEasy.getNumThrowingStarsUsed());

        gameEasy.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(gameEasy.getSprites().size() == 2 + NSG.MAX_THROWING_STARS_EASY);
        assertEquals(NSG.MAX_THROWING_STARS_EASY, gameEasy.getNumThrowingStarsUsed());
    }

    @Test
    public void testSpaceKeyEventForHardGame() {
        NSG gameHard = new NSG(3);

        gameHard.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(gameHard.getSprites().size() == 3);

        for (int i = 1; i < NSG.MAX_THROWING_STARS_HARD; i++) {
            gameHard.keyPressed(KeyEvent.VK_SPACE);
        }

        assertTrue(gameHard.getSprites().size() == 2 + NSG.MAX_THROWING_STARS_HARD);
        assertEquals(NSG.MAX_THROWING_STARS_HARD, gameHard.getNumThrowingStarsUsed());

        gameHard.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(gameHard.getSprites().size() == 2 + NSG.MAX_THROWING_STARS_HARD);
        assertEquals(NSG.MAX_THROWING_STARS_HARD, gameHard.getNumThrowingStarsUsed());
    }
}