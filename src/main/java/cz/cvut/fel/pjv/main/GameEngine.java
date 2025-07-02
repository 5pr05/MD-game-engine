package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.characters.Enemies;
import cz.cvut.fel.pjv.inputs.InputHandler;
import cz.cvut.fel.pjv.characters.Player;
import cz.cvut.fel.pjv.characters.PlayerController;
import cz.cvut.fel.pjv.level.*;

public class GameEngine implements Runnable{
    private GameWindow window;
    private GamePanel panel;
    private Thread gameLoopThread;
    private final int TARGET_FPS = 120;
    private boolean running = false;

    public GameEngine() {
        double xPosition = 0; // start x position
        double yPosition = 300; // start y position
        Player player = new Player(xPosition, yPosition);
        panel = new GamePanel(xPosition, yPosition);
        Enemies[] enemies = panel.getEnemies();
        Platform[] platforms = panel.getPlatforms();
        PlayerController playerController = new PlayerController(player, null, enemies, platforms);
        InputHandler inputHandler = new InputHandler(playerController, panel);
        playerController.setInputHandler(inputHandler);
        panel.setInputHandler(inputHandler);
        window = new GameWindow(panel);
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
                panel.repaint();
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
