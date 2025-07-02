package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.characters.*;
import cz.cvut.fel.pjv.inputs.*;
import cz.cvut.fel.pjv.level.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final Dimension PANEL_SIZE = new Dimension(1280, 800);
    private Player player;
    private Enemies[] enemies;
    private Platform[] platforms;
    private PlayerController playerController;
    private GameRenderer gameRenderer;

    public GamePanel(double xPosition, double yPosition){
        this.player = new Player(xPosition, yPosition);
        this.enemies = new Enemies[]{new Guard(300, 300, player), new Guard(600, 300, player), new Lava(700, 350, player)};
        this.platforms = new Platform[]{new Platform(200, 250, 100,20), new Platform(450, 200, 100, 20)};
        this.playerController = new PlayerController(player, null, enemies, platforms, gameRenderer);
        InputHandler inputHandler = new InputHandler(playerController, this);
        playerController.setInputHandler(inputHandler);
        this.gameRenderer = new GameRenderer(player, enemies, platforms, playerController, inputHandler);
        //this.playerController.setGameRenderer(gameRenderer);
        this.addKeyListener(inputHandler);
        this.addMouseListener(inputHandler);
        this.setFocusable(true);
        this.requestFocusInWindow();
        initializePanel();
    }


    // input handler setter
    public void setInputHandler(InputHandler inputHandler) {
        playerController.setInputHandler(inputHandler);
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

    // draw components
    @Override
    public void paintComponent (Graphics graphics){
        super.paintComponent(graphics);
        gameRenderer.render(graphics);
    }

    // player controller getter
    public PlayerController getPlayerController() {
        return playerController;
    }

    // game renderer getter
    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }
}
