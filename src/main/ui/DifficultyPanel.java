package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the starting panel where user selects difficulty
public class DifficultyPanel extends JFrame {

    private static final String START_TEXT = "Start Game";
    private static final int START_WIDTH = 400;
    private static final int START_HEIGHT = 120;

    private JComboBox<Difficulty> difficultySettings;
    private int difficulty;

    public DifficultyPanel() {
        super("Ninja Shooting Game - Start Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        difficulty = 0;

        difficultySettings = new JComboBox<>();
        for (Difficulty value : Difficulty.values()) {
            difficultySettings.addItem(value);
        }

        JPanel startPanel = new JPanel();
        JButton startGame = new JButton(START_TEXT);
        startGame.addActionListener(new DifficultyListener());

        add(startPanel);
        startPanel.add(difficultySettings, BorderLayout.WEST);
        startPanel.add(startGame, BorderLayout.EAST);
        pack();
        setSize(new Dimension(START_WIDTH, START_HEIGHT));
        setLocationRelativeTo(null);
    }

    public int getDifficulty() {
        return difficulty;
    }

    // Represents the different difficulty settings for the game
    private enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private class DifficultyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Difficulty diff = (Difficulty)difficultySettings.getSelectedItem();

            if (diff != null) {
                switch (diff) {
                    case MEDIUM:
                        difficulty = 2;
                        break;
                    case HARD:
                        difficulty = 3;
                        break;
                    default:
                        difficulty = 1;
                        break;
                }
            } else {
                difficulty = 1;
            }

            dispose();
        }
    }
}
