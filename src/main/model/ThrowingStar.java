package model;

import java.awt.*;

// Represents a throwing star
public class ThrowingStar extends Sprite {

    private static final int INNER_RADIUS = 1;
    public static final int OUTER_RADIUS = 3;
    public static final int THROWING_STAR_WIDTH = 2 * OUTER_RADIUS;
    public static final int THROWING_STAR_HEIGHT = 2 * OUTER_RADIUS;
    public static final int DX = 2;
    private static final Color COLOR = new Color(192, 192, 192);

    // EFFECTS: constructs a throwing star at position (x, y)
    public ThrowingStar(int x, int y) {
        super(x, y, THROWING_STAR_WIDTH, THROWING_STAR_HEIGHT);
    }

    @Override
    public void move() {
        x += DX;
        super.move();
    }

    @Override
    public void draw(Graphics g) {
        int xPoints[] = { getX() + OUTER_RADIUS, getX() + INNER_RADIUS, getX(), getX() - INNER_RADIUS,
                getX() - OUTER_RADIUS, getX() - INNER_RADIUS, getX(), getX() + INNER_RADIUS};
        int yPoints[] = { getY(), getY() + INNER_RADIUS, getY() + OUTER_RADIUS, getY() + INNER_RADIUS,
                getY(), getY() - INNER_RADIUS, getY() - OUTER_RADIUS, getY() - INNER_RADIUS};

        Color gColor = g.getColor();
        g.setColor(COLOR);
        g.fillPolygon( xPoints, yPoints, 8 );
        g.setColor(gColor);
    }
}
