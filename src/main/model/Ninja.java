package model;

import java.awt.*;

// Represents a ninja
public class Ninja extends Sprite {

    public static final int X_POS = 5;
    private static final int NINJA_WIDTH = 5;
    private static final int NINJA_HEIGHT = 5;
    public static final int DY = 1;
    private static final Color COLOR = new Color(148, 239, 206);

    private boolean direction;           // direction of ninja (true is down, false is up)

    // EFFECTS: Constructs a ninja at position (X_POS, y) moving in an upwards direction
    public Ninja(int y) {
        super(X_POS, y, NINJA_WIDTH, NINJA_HEIGHT);
        direction = false;
    }

    @Override
    public void move() {
        if (direction) {
            y +=  (-1) * DY;
        } else {
            y += DY;
        }

        super.move();
    }

    public boolean getDirection() {
        return direction;
    }

    // MODIFIES: this
    // EFFECTS: reverses the current direction of the ninja
    public void changeDirection() {
        direction = !direction;
    }

    @Override
    public void draw(Graphics g) {
        Color gColor = g.getColor();
        int xPoints[] = {getX(), getX() + 1, getX() - 1};
        int yPoints[] = {getY() - 2, getY() + 1, getY() + 1};

        g.setColor(COLOR);
        g.fillOval(getX() - 1, getY() - 4, 2, 2);
        g.fillPolygon( xPoints, yPoints, 3 );
        g.setColor(gColor);
    }
}
