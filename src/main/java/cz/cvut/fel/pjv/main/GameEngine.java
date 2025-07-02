package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.entities.*;
import cz.cvut.fel.pjv.level.*;


public class GameEngine implements Runnable {
    private GamePanel panel;
    private Thread gameLoopThread;
    private final int TARGET_FPS = 120;
    private boolean running = false;
    private static int levelNum;
    private GameRenderer gameRenderer;

    private PlayerController playerController;

    public GameEngine(int levelNum, String filename) {
        this.levelNum = levelNum;
        double xPosition = 30; // start x position
        double yPosition = 300; // start y position

        LevelLoader level = new LevelLoader(xPosition, yPosition, filename);

        panel = new GamePanel(level);
        playerController = panel.getPlayerController();
        gameRenderer = panel.getGameRenderer();
        panel.requestFocus();
        startGameLoop();
    }


    // start game loop
    private void startGameLoop() {
        running = true;
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    // stop game loop
    public void stopGameLoop() {
        running = false;
    }

    // game loop
    @Override
    public void run() {
        double timePerUpdate = 1000000000.0 / TARGET_FPS;
        long lastUpdateTime = System.nanoTime();
        int frames = 0;
        long fpsTimer = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            if (currentTime - lastUpdateTime >= timePerUpdate) {
                panel.update();
                gameRenderer.runAnimation();
                lastUpdateTime = currentTime;
                frames++;
            }
            if (System.currentTimeMillis() - fpsTimer > 1000) {
                fpsTimer += 1000;
                frames = 0;
            }
        }
    }

    // level number getter
    public static int getLevelNum() {
        return levelNum;
    }

    // game panel getter
    public GamePanel getPanel() {
        return panel;
    }
}

