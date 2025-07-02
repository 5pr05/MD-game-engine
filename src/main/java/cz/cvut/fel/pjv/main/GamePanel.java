package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.inputs.InputHandler;
import cz.cvut.fel.pjv.characters.*;
import cz.cvut.fel.pjv.characters.PlayerController;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final Dimension PANEL_SIZE = new Dimension(1280, 800);
    private Player player;
    private Guard guard1, guard2;
    private PlayerController playerController;
    private Enemies[] enemies;

    public GamePanel(double xPosition, double yPosition){
        this.player = new Player(xPosition, yPosition);
        this.guard1 = new Guard(300, 300, player);
        this.guard2 = new Guard(600, 300, player);
        this.enemies = new Enemies[]{guard1, guard2};
        initializePanel();
    }

    // input handler setter
    public void setInputHandler(InputHandler inputHandler) {
        this.playerController = new PlayerController(player, inputHandler, enemies);
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
        for (Enemies enemy : enemies) {
            enemy.update();
        }
        repaint();
    }

    // draw player
    @Override
    public void paintComponent (Graphics graphics){
        super.paintComponent(graphics);
        player.drawPlayer(graphics);
        for (Enemies enemy : enemies) {
            if (enemy instanceof Guard) {
                ((Guard) enemy).drawGuard(graphics);
            }
        }
    }

    // enemies getter
    public Enemies[] getEnemies() {
        return enemies;
    }
}
