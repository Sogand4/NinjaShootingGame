package model;

import java.awt.*;

// Represents a ninja
public class Ninja extends Sprite {

    public static final int X_POS = NSG.GAME_WIDTH / 5;
    private static final int NINJA_WIDTH = 80;
    private static final int NINJA_HEIGHT = 50;
    public static final int DY = 2;
    private static final Color HEAD = new Color(148, 239, 206);
    private static final Color BODY = new Color(101, 159, 143);

    private boolean direction;           // direction of ninja (true is up, false is down)

    // EFFECTS: constructs a ninja at position (X_POS, y) facing a downwards direction
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

    // MODIFIES: this
    // EFFECTS: reverses the current direction of the ninja
    public void changeDirection() {
        direction = !direction;
    }

    @Override
    public void draw(Graphics g) {
        Color gColor = g.getColor();
        int xPoints[] = {getX(), getX() + 7, getX() - 7};
        int yPoints[] = {getY() - 14, getY() + 7, getY() + 7};

        g.setColor(HEAD);
        g.fillOval(getX() - 7, getY() - 28, 14, 14);
        g.setColor(BODY);
        g.fillPolygon( xPoints, yPoints, 3 );
        g.setColor(gColor);
    }

    public boolean getDirection() {
        return direction;
    }
}
