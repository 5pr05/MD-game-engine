package cz.cvut.fel.pjv.main;

import javax.swing.*;

public class GameWindow {
    private JFrame gameFrame;

    public GameWindow(JFrame frame, GamePanel gamePanel) {
        this.gameFrame = frame;
        initializeWindow(gamePanel);
    }

    private void initializeWindow(GamePanel gamePanel) {
        SwingUtilities.invokeLater(() -> {
            gameFrame.getContentPane().removeAll();
            gameFrame.add(gamePanel);
            gameFrame.setResizable(false);
            gameFrame.pack();
            gameFrame.setLocationRelativeTo(null);
            gameFrame.repaint();
            gameFrame.setVisible(true);
            gamePanel.requestFocusInWindow();
        });
    }
}
