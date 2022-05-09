package model;

import java.awt.*;

// Represents a target
public class Target extends Sprite {

    public static final int RADIUS = 4;
    public static final int TOP = NSG.GAME_HEIGHT / 6;
    public static final int BOTTOM = NSG.GAME_HEIGHT - NSG.GAME_HEIGHT / 6;
    public static final int RIGHT_EDGE = NSG.GAME_WIDTH - NSG.GAME_WIDTH / 6;
    public static final int LEFT_EDGE = NSG.GAME_WIDTH / 2 + NSG.GAME_WIDTH / 6;
    private static final Color COLOR1 = new Color(246, 90, 61);
    private static final Color COLOR2 = new Color(255, 255, 255);
    public static final int Y_MID = (BOTTOM - TOP) / 2 + TOP;
    public static final int X_MID = LEFT_EDGE + (RIGHT_EDGE - LEFT_EDGE) / 2;
    private static final int SPEED_EASY = 2;
    private static final int SPEED_HARD = 3;

    private int dx;                 // Horizontal velocity of target
    private int dy;                 // Vertical velocity of target
    private int type;               // Type of target chosen (based on the player's choice of difficulty,
                                    // an integer from [1, 3]

    private boolean yHitMid;        // Has the target reached Y_MID? (used for path 3)
    private boolean xHitMid;        // Has the target reached X_MID? (used for path 3)
    private boolean flipDirection;  // Direction of movement for path 3 - true means target goes down when
                                    // it reaches the middle

    // EFFECTS: constructs a Target with a radius and type, at a position (X_MID, TOP), with horizontal and vertical
    //          speeds of 2, and facing down
    public Target(int type) {
        super(X_MID, TOP, 2 * RADIUS, 2 * RADIUS);
        this.type = type;
        dx = SPEED_EASY;
        dy = SPEED_EASY;
        flipDirection = true;
        yHitMid = false;
        xHitMid = false;
    }

    // MODIFIES: this
    // EFFECTS: reverses the vertical direction of the target
    public void changeVerticalDirection() {
        dy *= -1;
    }

    // MODIFIES: this
    // EFFECTS: moves the target, following a set path based on the type of target
    @Override
    public void move() {
        switch (type) {
            case 1:
                moveWithPath1();
                break;
            case 2:
                moveWithPath2();
                break;
            case 3:
                moveWithPath3();
                break;
            default:
                break;
        }

        handleBoundary();
    }

    // MODIFIES: this
    // EFFECTS: moves the target up and down along one vertical line
    public void moveWithPath1() {
        y += dy;
    }

    // MODIFIES: this
    // EFFECTS: moves the target following a square path
    public void moveWithPath2() {
        if (y == TOP && x != RIGHT_EDGE) {
            dx = SPEED_EASY;
            dy = 0;
        } else if (y == BOTTOM && x != LEFT_EDGE) {
            dx = -SPEED_EASY;
            dy = 0;
        } else if (x == RIGHT_EDGE) {
            dx = 0;
            dy = SPEED_EASY;
        } else if (x == LEFT_EDGE) {
            dx = 0;
            dy = -SPEED_EASY;
        }

        y += dy;
        x += dx;
    }

    // MODIFIES: this
    // EFFECTS: moves the target following a path of two squares that intersect at the top right corner of one square
    //          and the bottom left corner of another
    public void moveWithPath3() {
        if (y == TOP && x == RIGHT_EDGE) {
            dx = 0;
            dy = SPEED_HARD;
        } else if ((y == TOP && x == X_MID) || (y == Y_MID && x == LEFT_EDGE)) {
            dx = SPEED_HARD;
            dy = 0;
        } else if ((y == Y_MID && x == RIGHT_EDGE) || (y == BOTTOM && x == X_MID)) {
            dx = -SPEED_HARD;
            dy = 0;
        } else if (y == Y_MID && x == X_MID) {
            dx = 0;
            if (flipDirection) {
                dy = SPEED_HARD;
            } else {
                dy = -SPEED_HARD;
            }
            flipDirection = !flipDirection;
        } else if (y == BOTTOM && x == LEFT_EDGE) {
            dx = 0;
            dy = -SPEED_HARD;
        }

        x += dx;
        y += dy;
    }

    @Override
    public void handleBoundary() {
        if (y < TOP) {
            y = TOP;
            if (type == 1) {
                dy *= -1;
            }
        } else if (y > BOTTOM) {
            y = BOTTOM;
            if (type == 1) {
                dy *= -1;
            }
        }

        if (x > RIGHT_EDGE) {
            x = RIGHT_EDGE;
        } else if (x < LEFT_EDGE) {
            x = LEFT_EDGE;
        }

        if (type == 3) {
            if (y > Y_MID && !yHitMid) {
                y = Y_MID;
                yHitMid = true;
            } else if (y < Y_MID && yHitMid) {
                y = Y_MID;
                yHitMid = false;
            }

            if (x > X_MID && xHitMid) {
                x = X_MID;
                xHitMid = false;
            } else if (x < X_MID && !xHitMid) {
                x = X_MID;
                xHitMid = true;
            }
        }
    }

    // EFFECTS: returns true if a throwing star collided with this target
    public boolean hit(ThrowingStar star) {
        Rectangle targetBoundingRect = new Rectangle(getX() - 5 * RADIUS, getY() - 5 * RADIUS,
                5 * (2 * RADIUS), 5 * (2 * RADIUS));
        Rectangle starBoundingRect = new Rectangle(star.getX() - ThrowingStar.OUTER_RADIUS,
                star.getY() - ThrowingStar.OUTER_RADIUS, ThrowingStar.THROWING_STAR_WIDTH,
                ThrowingStar.THROWING_STAR_HEIGHT);
        return targetBoundingRect.intersects(starBoundingRect);
    }

    @Override
    public void draw(Graphics g) {
        Color gColor = g.getColor();

        g.setColor(COLOR1);
        g.fillOval(getX() - 5 * RADIUS, getY() - 5 * RADIUS, 5 * (2 * RADIUS), 5 * (2 *  RADIUS));
        g.setColor(COLOR2);
        g.fillOval(getX() - 4 * RADIUS, getY() - 4 * RADIUS, 4 * (2 * RADIUS), 4 * (2 * RADIUS));
        g.setColor(COLOR1);
        g.fillOval(getX() - 3 * RADIUS, getY() - 3 * RADIUS, 3 * (2 * RADIUS), 3 * (2 * RADIUS));
        g.setColor(COLOR2);
        g.fillOval(getX() - 2 * RADIUS, getY() - 2 * RADIUS, 2 * (2 * RADIUS), 2 * (2 * RADIUS));
        g.setColor(COLOR1);
        g.fillOval(getX() - RADIUS, getY() - RADIUS, (2 * RADIUS), (2 * RADIUS));

        g.setColor(gColor);
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getType() {
        return type;
    }
}
