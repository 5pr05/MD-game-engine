package cz.cvut.fel.pjv.main;

import javax.swing.*;

public class GameWindow {
    private JFrame gameFrame;
    
    public GameWindow(GamePanel gamePanel) {
        gameFrame = new JFrame();
        initializeWindow(gamePanel);
    }

    private void initializeWindow(GamePanel gamePanel) {
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(gamePanel);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}
