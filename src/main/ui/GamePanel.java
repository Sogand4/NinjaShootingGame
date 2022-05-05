package ui;

import model.NSG;

import javax.swing.*;
import java.awt.*;

// Panel that renders the game
public class GamePanel extends JPanel {

    private static final String GAME_OVER = "Game Over";
    private static final String WIN = "You win!";
    private static final String LOSE = "You lose!";
    private NSG game;

    // EFFECTS: creates a panel with the game's size and background colour
    public GamePanel(NSG game) {
        setPreferredSize(new Dimension(NSG.GAME_WIDTH, NSG.GAME_HEIGHT));
        setBackground(Color.DARK_GRAY);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.draw(g);

        if (game.isGameOver()) {
            displayGameOverText(g);
        }
    }

    // EFFECTS: displays the result of the end of the game (either win or lose)
    private void displayGameOverText(Graphics g) {
        String result;
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));

        if (game.getHit()) {
            result = GAME_OVER + "\n" + WIN;
        } else {
            result = GAME_OVER + "\n" + LOSE;
        }

        g.drawString(result, NSG.GAME_WIDTH / 2, NSG.GAME_HEIGHT / 2);
        g.setColor(saved);
    }
}
