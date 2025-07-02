package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.characters.*;
import cz.cvut.fel.pjv.inputs.*;
import cz.cvut.fel.pjv.level.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GamePanel extends JPanel {
    private Player player;
    private Enemies[] enemies;
    private Platform[] platforms;
    private PlayerController playerController;
    private GameRenderer gameRenderer;
    private GameModel gameModel;

    public GamePanel(Level level) {
        this.player = level.getPlayer();
        this.enemies = level.getEnemies();
        this.platforms = level.getPlatforms();
        this.gameModel = new GameModel(player, Arrays.asList(platforms), Arrays.asList(enemies));
        this.playerController = new PlayerController(player, null, gameRenderer, gameModel);
        InputHandler inputHandler = new InputHandler(playerController, this, gameModel);
        playerController.setInputHandler(inputHandler);
        this.gameRenderer = new GameRenderer(player, enemies, platforms, inputHandler);
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
        setFocusable(true);
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
    public void paintComponent(Graphics graphics) {
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
