package ui;

import model.NSG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Represents the main panel of the game
public class NinjaShooter extends JFrame {

    private static final int INTERVAL = 20;

    private NSG game;
    private DifficultyPanel dp;
    private GamePanel gp;
    private AttemptsLeftPanel alp;
    private Timer t;

    // EFFECTS: constructs the main panel of the game
    public NinjaShooter() {
        super("Ninja Shooting Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dp = new DifficultyPanel();

        while (dp.getDifficulty() == 0) {
            setVisible(false);
        }

        setVisible(true);
        game = new NSG(dp.getDifficulty());
        alp = new AttemptsLeftPanel(game);
        gp = new GamePanel(game);

        add(alp, BorderLayout.NORTH);
        add(gp, BorderLayout.CENTER);

        addKeyListener(new KeyHandler());
        pack();
        setVisible(true);
        addTimer();
        t.start();
    }

    // MODIFIES: this
    // EFFECTS: updates the game every INTERVAL milliseconds
    public void addTimer() {
        t = new Timer(INTERVAL, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                gp.repaint();
                alp.update();
            }
        });
    }

    // Represents a key handler to respond to key events
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }

    // EFFECTS: play the game
    public static void main(String[] args) {
        new NinjaShooter();
    }
}
