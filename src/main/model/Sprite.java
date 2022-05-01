package model;

import java.awt.*;

// Represents a sprite
public abstract class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    // EFFECTS: Constructs a Sprite with a width, height, and position (x, y)
    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // MODIFIES: this
    // EFFECTS: makes sure the sprites' positions stays within the boundaries
    public void handleBoundary() {
        if (y > NSG.GAME_HEIGHT) {
            y = NSG.GAME_HEIGHT;
        } else if (y < 0) {
            y = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the sprite
    public void move() {
        handleBoundary();
    }

    // MODIFIES: g
    // EFFECTS: draws the sprite on g
    public abstract void draw(Graphics g);
}
