package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.characters.*;

public class GameEngine implements Runnable{
    private GameWindow window;
    private GamePanel panel;
    private Thread gameLoopThread;
    private final int TARGET_FPS = 120;
    private boolean running = false;
    private GameRenderer gameRenderer;

    private PlayerController playerController;

    public GameEngine() {
        double xPosition = 0; // start x position
        double yPosition = 300; // start y position
        panel = new GamePanel(xPosition, yPosition);
        window = new GameWindow(panel);
        playerController = panel.getPlayerController();
        gameRenderer = panel.getGameRenderer();
        panel.requestFocus();
        startGameLoop();
    }

    // start game loop
    private void startGameLoop(){
        running = true;
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    // stop game loop
    public void stopGameLoop(){
        running = false;
    }

    // game loop
    @Override
    public void run() {
        double timePerUpdate = 1000000000.0 / TARGET_FPS;
        long lastUpdateTime = System.nanoTime();
        int frames = 0;
        long fpsTimer = System.currentTimeMillis();

        while (running){
            long currentTime = System.nanoTime();
            if (currentTime - lastUpdateTime >= timePerUpdate){
                panel.update();
                gameRenderer.runAnimation();
                lastUpdateTime = currentTime;
                frames++;
            }
            if(System.currentTimeMillis() - fpsTimer > 1000){
                fpsTimer += 1000;
                frames = 0;
            }
        }
    }
}
