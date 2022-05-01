package test;

import model.Target;
import model.ThrowingStar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TargetTest {

    private static final int RADIUS_EASY = 5;
    private static final int RADIUS_MEDIUM = 4;
    private static final int RADIUS_HARD = 3;
    private Target easyTarget;

    @BeforeEach
    public void runBefore() {
        easyTarget = new Target(RADIUS_EASY, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, easyTarget.getType());
        assertEquals(2 * RADIUS_EASY, easyTarget.getHeight());
        assertEquals(2 * RADIUS_EASY, easyTarget.getWidth());
        assertEquals(2, easyTarget.getDx());
        assertEquals(2, easyTarget.getDy());
        assertEquals(Target.LEFT_EDGE + (Target.RIGHT_EDGE - Target.LEFT_EDGE) / 2,
                easyTarget.getX());
    }

    @Test
    public void testUpdateEasyTargetBottom() {
        while (easyTarget.getY() < Target.BOTTOM) {
            easyTarget.move();
        }

        assertEquals(Target.BOTTOM, easyTarget.getY());
        assertEquals(Target.LEFT_EDGE + (Target.RIGHT_EDGE - Target.LEFT_EDGE) / 2,
                easyTarget.getX());
        assertEquals(2,easyTarget.getDy());

        easyTarget.move();

        assertEquals(Target.BOTTOM, easyTarget.getY());
        assertEquals(Target.LEFT_EDGE + (Target.RIGHT_EDGE - Target.LEFT_EDGE) / 2,
                easyTarget.getX());
        assertEquals(-2,easyTarget.getDy());
    }

    @Test
    public void testUpdateEasyTargetTop() {
        easyTarget.changeVerticalDirection();
        while (easyTarget.getY() > Target.TOP) {
            easyTarget.move();
        }

        assertEquals(Target.TOP, easyTarget.getY());
        assertEquals(Target.LEFT_EDGE + (Target.RIGHT_EDGE - Target.LEFT_EDGE) / 2,
                easyTarget.getX());
        assertEquals(-2,easyTarget.getDy());

        easyTarget.move();

        assertEquals(Target.TOP, easyTarget.getY());
        assertEquals(Target.LEFT_EDGE + (Target.RIGHT_EDGE - Target.LEFT_EDGE) / 2,
                easyTarget.getX());
        assertEquals(2,easyTarget.getDy());
    }

    @Test
    public void testUpdateMediumTarget() {
        Target mediumTarget = new Target(RADIUS_MEDIUM, 2);

        while (mediumTarget.getX() < Target.RIGHT_EDGE) {
            mediumTarget.move();
        }

        assertEquals(Target.RIGHT_EDGE, mediumTarget.getX());
        assertEquals(Target.TOP, mediumTarget.getY());
        assertEquals(2, mediumTarget.getDx());
        assertEquals(0, mediumTarget.getDy());

        while (mediumTarget.getY() < Target.BOTTOM) {
            mediumTarget.move();
        }

        assertEquals(Target.RIGHT_EDGE, mediumTarget.getX());
        assertEquals(Target.BOTTOM, mediumTarget.getY());
        assertEquals(0, mediumTarget.getDx());
        assertEquals(2, mediumTarget.getDy());

        while (mediumTarget.getX() > Target.LEFT_EDGE) {
            mediumTarget.move();
        }

        assertEquals(Target.LEFT_EDGE, mediumTarget.getX());
        assertEquals(Target.BOTTOM, mediumTarget.getY());
        assertEquals(-2, mediumTarget.getDx());
        assertEquals(0, mediumTarget.getDy());


        while (mediumTarget.getY() > Target.TOP) {
            mediumTarget.move();
        }

        assertEquals(Target.LEFT_EDGE, mediumTarget.getX());
        assertEquals(Target.TOP, mediumTarget.getY());
        assertEquals(0, mediumTarget.getDx());
        assertEquals(-2, mediumTarget.getDy());
    }

    @Test
    public void testUpdateHardTarget() {
        Target hardTarget = new Target(RADIUS_HARD, 3);

        while (hardTarget.getX() < Target.RIGHT_EDGE) {
            hardTarget.move();
        }

        assertEquals(Target.RIGHT_EDGE, hardTarget.getX());
        assertEquals(Target.TOP, hardTarget.getY());
        assertEquals(1, hardTarget.getDx());
        assertEquals(0, hardTarget.getDy());

        while (hardTarget.getY() < Target.Y_MID) {
            hardTarget.move();
        }

        assertEquals(Target.RIGHT_EDGE, hardTarget.getX());
        assertEquals(Target.Y_MID, hardTarget.getY());
        assertEquals(0, hardTarget.getDx());
        assertEquals(1, hardTarget.getDy());

        while (hardTarget.getX() > Target.X_MID) {
            hardTarget.move();
        }

        assertEquals(Target.X_MID, hardTarget.getX());
        assertEquals(Target.Y_MID, hardTarget.getY());
        assertEquals(-1, hardTarget.getDx());
        assertEquals(0, hardTarget.getDy());

        while (hardTarget.getY() < Target.BOTTOM) {
            hardTarget.move();
        }

        assertEquals(Target.X_MID, hardTarget.getX());
        assertEquals(Target.BOTTOM, hardTarget.getY());
        assertEquals(0, hardTarget.getDx());
        assertEquals(1, hardTarget.getDy());

        while (hardTarget.getX() > Target.LEFT_EDGE) {
            hardTarget.move();
        }

        assertEquals(Target.LEFT_EDGE, hardTarget.getX());
        assertEquals(Target.BOTTOM, hardTarget.getY());
        assertEquals(-1, hardTarget.getDx());
        assertEquals(0, hardTarget.getDy());

        while (hardTarget.getY() > Target.Y_MID) {
            hardTarget.move();
        }

        assertEquals(Target.LEFT_EDGE, hardTarget.getX());
        assertEquals(Target.Y_MID, hardTarget.getY());
        assertEquals(0, hardTarget.getDx());
        assertEquals(-1, hardTarget.getDy());

        while (hardTarget.getX() < Target.X_MID) {
            hardTarget.move();
        }

        assertEquals(Target.X_MID, hardTarget.getX());
        assertEquals(Target.Y_MID, hardTarget.getY());
        assertEquals(1, hardTarget.getDx());
        assertEquals(0, hardTarget.getDy());

        while (hardTarget.getY() > Target.TOP) {
            hardTarget.move();
        }

        assertEquals(Target.X_MID, hardTarget.getX());
        assertEquals(Target.TOP, hardTarget.getY());
        assertEquals(0, hardTarget.getDx());
        assertEquals(-1, hardTarget.getDy());
    }

    @Test
    public void testHit() {
        ThrowingStar star = new ThrowingStar(0, 0);
        assertFalse(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX(), easyTarget.getY());
        assertTrue(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX() - 5 * Target.RADIUS - ThrowingStar.OUTER_RADIUS + 1,
                easyTarget.getY());
        assertTrue(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX() + 5 * Target.RADIUS + ThrowingStar.OUTER_RADIUS - 1,
                easyTarget.getY());
        assertTrue(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX() - 5 * Target.RADIUS - ThrowingStar.OUTER_RADIUS,
                easyTarget.getY());
        assertFalse(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX() + 5 * Target.RADIUS + ThrowingStar.OUTER_RADIUS,
                easyTarget.getY());
        assertFalse(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX(), easyTarget.getY() - 5 * Target.RADIUS
                - ThrowingStar.OUTER_RADIUS + 1);
        assertTrue(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX(), easyTarget.getY() + 5 * Target.RADIUS
                + ThrowingStar.OUTER_RADIUS - 1);
        assertTrue(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX(), easyTarget.getY() - 5 * Target.RADIUS
                - ThrowingStar.OUTER_RADIUS);
        assertFalse(easyTarget.hit(star));

        star = new ThrowingStar(easyTarget.getX(), easyTarget.getY() + 5 * Target.RADIUS
                + ThrowingStar.OUTER_RADIUS);
        assertFalse(easyTarget.hit(star));
    }
}
