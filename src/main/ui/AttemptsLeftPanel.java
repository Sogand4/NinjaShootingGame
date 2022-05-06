package ui;

import model.NSG;

import javax.swing.*;
import java.awt.*;

// Panel that displays how many throwing stars the player has left
public class AttemptsLeftPanel extends JPanel {
    private static final String THROWING_STARS_TEXT = "Throwing stars remaining: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private static final Color BACKGROUND_COLOUR = new Color(250, 238, 238);
    private NSG game;
    private JLabel throwingStarsLabel;

    // EFFECTS: constructs a panel displaying the number of throwing stars the player has
    public AttemptsLeftPanel(NSG game) {
        this.game = game;
        setBackground(BACKGROUND_COLOUR);

        throwingStarsLabel = new JLabel(THROWING_STARS_TEXT + game.getMaxThrowingStars());
        throwingStarsLabel.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(throwingStarsLabel);
    }

    // MODIFIES: this
    // EFFECTS: updates the current number of throwing stars available to the player
    public void update() {
        throwingStarsLabel.setText(THROWING_STARS_TEXT + (game.getMaxThrowingStars() - game.getNumThrowingStarsUsed()));
        repaint();
    }
}
