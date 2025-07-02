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
    private int xPose, yPose;
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
                    if (inputHandler.isAttack() && player.getCanAttack()) {
                        yPose = 4;
                    } else if (inputHandler.isRight()) {
                        yPose = 0;
                    } else if (inputHandler.isLeft()) {
                        yPose = 1;
                    } else {
                        yPose = 2;
                    }
                }
                if (!player.isAlive()) {
                    yPose = 3;
                }
                for (int i = 0; i <= 1; i++) {
                    if (enemies[i] != null) {
                        if (enemies[i].isAlive()) {
                            if (enemies[i].getDirection() > 0) {
                                enemies[i].yPose = 0;
                            } else if (enemies[i].getDirection() <= 0) {
                                enemies[i].yPose = 1;
                            }
                        } else {
                            enemies[i].yPose = 2;
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
                graphics.drawImage(guardSprites.getSubimage(xPose * spriteWidth, enemy.yPose * spriteHeight, spriteWidth, spriteHeight), (int)enemy.getEnemiesXPosition(), (int)enemy.getEnemiesYPosition(), null);
            } else if (enemy instanceof Lava) {
                graphics.drawImage(lavaSprites.getSubimage(xPose * 100, 1, 100, 20), (int)enemy.getEnemiesXPosition(), (int)enemy.getEnemiesYPosition(), null);
            }
        }
        for (Platform platform : platforms) {
            graphics.drawImage(platformSprites.getSubimage(0, 0, 100,800), platform.getxPosition(), platform.getYPosition(), null);
        }
    }
}
