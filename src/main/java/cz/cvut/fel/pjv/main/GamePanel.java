package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.inputs.InputHandler;
import cz.cvut.fel.pjv.characters.Player;
import cz.cvut.fel.pjv.characters.PlayerController;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final Dimension PANEL_SIZE = new Dimension(1280, 800);
    private Player player;
    private PlayerController playerController;

    public GamePanel(double xPosition, double yPosition){
        this.player = new Player(xPosition, yPosition);
        initializePanel();
    }

    // set input handler
    public void setInputHandler(InputHandler inputHandler) {
        this.playerController = new PlayerController(player, inputHandler);
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);
    }

    // panel settings
    private void initializePanel() {
        setMinimumSize(PANEL_SIZE);
        setMaximumSize(PANEL_SIZE);
        setPreferredSize(PANEL_SIZE);
        setFocusable(true);
        requestFocusInWindow();
    }

    // update player
    public void update() {
        playerController.update();
        repaint();
    }

    // draw player
    @Override
    public void paintComponent (Graphics graphics){
        super.paintComponent(graphics);
        player.drawPlayer(graphics);
    }
}
