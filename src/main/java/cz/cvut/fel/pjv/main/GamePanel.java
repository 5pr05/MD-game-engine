package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.characters.*;
import cz.cvut.fel.pjv.inputs.*;
import cz.cvut.fel.pjv.level.*;
import cz.cvut.fel.pjv.menu.*;

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
    private InputHandler inputHandler;

    public GamePanel(Level level) {
        this.player = level.getPlayer();
        this.enemies = level.getEnemies();
        this.platforms = level.getPlatforms();
        this.gameModel = new GameModel(player, Arrays.asList(platforms), Arrays.asList(enemies), level, this);
        this.inputHandler = new InputHandler(this);
        this.playerController = new PlayerController(player, inputHandler, gameRenderer, gameModel, this);
        this.gameRenderer = new GameRenderer(player, enemies, platforms, inputHandler, gameModel);
        this.addKeyListener(inputHandler);
        this.addMouseListener(inputHandler);
        this.setFocusable(true);
        this.requestFocusInWindow();
        initializePanel();
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

    public void backToLevels(){
        new LevelSelectionMenu();
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    // player controller getter
    public PlayerController getPlayerController() {
        return playerController;
    }

    // game renderer getter
    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    // update enemies and platforms
    public void updateGameObjects(Enemies[] newEnemies, Platform[] newPlatforms) {
        this.enemies = newEnemies;
        this.platforms = newPlatforms;
        this.gameRenderer.updateGameObjects(newEnemies, newPlatforms);
    }
}
