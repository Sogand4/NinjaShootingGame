package ui;

import model.NSG;

import javax.swing.*;
import java.awt.*;

// Panel that renders the game
public class GamePanel extends JPanel {

    private static final String GAME_OVER = "Game Over";
    private static final String WIN = "You win!";
    private static final String LOSE = "You lose!";
    private static final Color BACKGROUND_COLOUR = new Color(79, 79, 79);

    private NSG game;

    // EFFECTS: creates a panel with the game's size and background colour
    public GamePanel(NSG game) {
        setPreferredSize(new Dimension(NSG.GAME_WIDTH, NSG.GAME_HEIGHT));
        setBackground(BACKGROUND_COLOUR);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.draw(g);

        if (game.getGameOver()) {
            displayGameOverText(g);
        }
    }

    // EFFECTS: displays the result of the end of the game (either win or lose)
    private void displayGameOverText(Graphics g) {
        String result;
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));

        if (game.getHit()) {
            result = GAME_OVER + " - " + WIN;
        } else {
            result = GAME_OVER + " - " + LOSE;
        }

        FontMetrics fm = g.getFontMetrics();
        drawCentredText(result, g, fm);
        g.setColor(saved);
    }

    // MODIFIES: g
    // EFFECTS: draws a centred string (str) onto g
    private void drawCentredText(String str, Graphics g, FontMetrics fm) {
        int width = fm.stringWidth(str);
        g.drawString(str, (NSG.GAME_WIDTH - width) / 2, NSG.GAME_HEIGHT / 2);
    }
}