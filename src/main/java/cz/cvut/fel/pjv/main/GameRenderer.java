package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.characters.*;
import cz.cvut.fel.pjv.inputs.*;
import cz.cvut.fel.pjv.level.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameRenderer {
    private Player player;
    private InputHandler inputHandler;
    private Enemies[] enemies;
    private Platform[] platforms;
    private BufferedImage playerSprites, guardSprites, lavaSprites, platformSprites;
    private PlayerController playerController;
    private int xPose, yPose, yPoseGuard;
    private int spriteWidth = 40;
    private int spriteHeight = 64;
    private int numSpritesX = 3;
    private int delay = 20;
    private int currentDelay = 0;

    public GameRenderer(Player player, Enemies[] enemies, Platform[] platforms, PlayerController playerController, InputHandler inputHandler) {
        this.player = player;
        this.enemies = enemies;
        this.platforms = platforms;
        this.playerController = playerController;
        this.inputHandler = inputHandler;
        importImages();
    }

    // run animation for characters
    public void runAnimation() {
        if (currentDelay >= delay) {
            if (xPose < numSpritesX - 1) {
                xPose++;
            } else {
                xPose = 0;
                if (inputHandler != null) {
                    if (inputHandler.isAttack()) {
                        yPose = 4;
                    } else if (inputHandler.isRight()) {
                        yPose = 0;
                    } else if (inputHandler.isLeft()) {
                        yPose = 1;
                    } else {
                        yPose = 2;
                    }
                } if (!playerController.isAlive()) {
                    yPose = 3;
                }
                for (Enemies enemy : enemies) {
                    if (enemy != null) {
                        if (enemy.getDirection() > 0) {
                            yPoseGuard = 0;
                        } if (enemy.getDirection() < 0) {
                            yPoseGuard = 1;
                        }
                    }
                }
            }
            currentDelay = 0;
        } else {
            currentDelay++;
        }
    }



    // import images for characters
    private void importImages() {
        playerSprites = importImage("/player_sprites.png");
        guardSprites = importImage("/guard_sprites.png");
        lavaSprites = importImage("/lava_sprites.png");
        platformSprites = importImage("/platform_sprite.png");
    }

    private BufferedImage importImage(String path) {
        InputStream stream = getClass().getResourceAsStream(path);
        try {
            return ImageIO.read(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // render graphics
    public void render(Graphics graphics) {
        graphics.drawImage(playerSprites.getSubimage(xPose * spriteWidth, yPose * spriteHeight, spriteWidth, spriteHeight), (int)player.getXPosition(), (int)player.getYPosition(), null);
        for (Enemies enemy : enemies) {
            if (enemy instanceof Guard) {
                graphics.drawImage(guardSprites.getSubimage(xPose * spriteWidth, yPoseGuard * spriteHeight, spriteWidth, spriteHeight), (int)enemy.getEnemiesXPosition(), (int)enemy.getEnemiesYPosition(), null);
            } else if (enemy instanceof Lava) {
                graphics.drawImage(lavaSprites.getSubimage(xPose * 30, 1, 100, 19), (int)enemy.getEnemiesXPosition(), (int)enemy.getEnemiesYPosition(), null);
            }
        }
        for (Platform platform : platforms) {
            graphics.drawImage(platformSprites.getSubimage(200, 100, 100, 20), platform.getxPosition(), platform.getYPosition(), null);
        }
    }
}
