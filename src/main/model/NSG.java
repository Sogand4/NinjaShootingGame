package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

// Represents a ninja shooting game
public class NSG {

    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 500;

    public static final int MAX_THROWING_STARS_EASY = 8;
    public static final int MAX_THROWING_STARS_HARD = 5;

    private List<Sprite> sprites;
    private Ninja ninja;
    private Target target;
    private int numThrowingStarsUsed;
    private int difficulty;
    private boolean gameOver;
    private boolean hit;

    // REQUIRES: difficulty is an integer from [1,3]
    // EFFECTS: constructs a ninja shooting game with the given difficulty, 2 sprites (ninja and target), gameOver set
    //          to false, and no throwing stars used
    public NSG(int difficulty) {
        sprites = new ArrayList<>();
        ninja = new Ninja(GAME_HEIGHT / 2);
        numThrowingStarsUsed = 0;
        this.difficulty = difficulty;
        target = new Target(difficulty);
        gameOver = false;
        hit = false;

        sprites.add(ninja);
        sprites.add(target);
    }

    // MODIFIES: this
    // EFFECTS: updates the game by moving all sprites, removing sprites that are off-screen, and checking if a
    //          throwing star hit the target
    public void update() {
        moveSprites();
        removeTSOutOfBounds();
        checkHit();
    }

    // MODIFIES: g
    // EFFECTS: draws all the sprites on g
    public void draw(Graphics g) {
        for (Sprite s : sprites)
            s.draw(g);
    }

    // MODIFIES: this
    // EFFECTS: changes the direction of the ninja, throws a star, and exits the game based on player keyboard input
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            if (!ninja.getDirection()) {
                ninja.changeDirection();
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (ninja.getDirection()) {
                ninja.changeDirection();
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            throwStar();
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: throws a throwing star if the maximum of throwing stars (based on difficulty) has not been exceeded
    public void throwStar() {
        if (difficulty == 1 || difficulty == 2) {
            if (numThrowingStarsUsed < MAX_THROWING_STARS_EASY) {
                numThrowingStarsUsed++;
                ThrowingStar ts = new ThrowingStar(ninja.getX(), ninja.getY());
                sprites.add(ts);
            }
        } else if (difficulty == 3) {
            if (numThrowingStarsUsed < MAX_THROWING_STARS_HARD) {
                numThrowingStarsUsed++;
                ThrowingStar ts = new ThrowingStar(ninja.getX(), ninja.getY());
                sprites.add(ts);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: moves all sprites
    public void moveSprites() {
        for(Sprite s : sprites) {
            s.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes all throwing stars that have travelled off-screen
    public void removeTSOutOfBounds() {
        List<ThrowingStar> spritesToBeRemoved = new ArrayList<>();
        for(Sprite s : sprites) {
            if (s.getX() > GAME_WIDTH) {
                spritesToBeRemoved.add((ThrowingStar) s);
            }
        }

        sprites.removeAll(spritesToBeRemoved);
        isGameOver();
    }

    // MODIFIES: this
    // EFFECTS: sets game over to true if all available throwing stars were used and all missed the target, otherwise
    //          silently returns
    private void isGameOver() {
        if (sprites.size() == 2) {
            if (difficulty == 1 || difficulty == 2) {
                if (numThrowingStarsUsed >= MAX_THROWING_STARS_EASY) {
                    gameOver = true;
                }
            } else if (difficulty == 3) {
                if (numThrowingStarsUsed >= MAX_THROWING_STARS_HARD) {
                    gameOver = true;
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if a throwing star has hit the target, set gameOver to true and clear the board
    private void checkHit() {
        for (Sprite s : sprites) {
            if (!(s instanceof Ninja) && !(s instanceof Target)) {
                if (target.hit((ThrowingStar) s)) {
                    gameOver = true;
                    hit = true;
                }
            }
        }

        if (gameOver) {
            sprites.clear();
        }
    }

    // EFFECTS: return the maximum number of throwing stars for this game, based on difficulty
    public int getMaxThrowingStars() {
        if (difficulty == 3) {
            return MAX_THROWING_STARS_HARD;
        } else {
            return MAX_THROWING_STARS_EASY;
        }
    }

    // getters
    public List<Sprite> getSprites() {
        return sprites;
    }

    public Ninja getNinja() {
        return ninja;
    }

    public int getNumThrowingStarsUsed() {
        return numThrowingStarsUsed;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean getHit() {
        return hit;
    }
}
