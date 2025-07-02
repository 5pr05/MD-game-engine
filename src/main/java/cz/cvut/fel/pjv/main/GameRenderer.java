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
    private GameModel gameModel;
    private InputHandler inputHandler;
    private Enemies[] enemies;
    private Platform[] platforms;
    private BufferedImage playerSprites, guardSprites, lavaSprites, platformSprites, inventoryImage, chestSprites, levelCompleteImage;
    private BufferedImage[] stories;
    private int xPose, yPose, inventoryPose, storyPose;
    private int spriteWidth = 40;
    private int spriteHeight = 64;
    private int numSpritesX = 3;
    private int delay = 20;
    private int currentDelay = 0;

    public GameRenderer(Player player, Enemies[] enemies, Platform[] platforms, InputHandler inputHandler, GameModel gameModel) {
        this.player = player;
        this.enemies = enemies;
        this.platforms = platforms;
        this.inputHandler = inputHandler;
        this.gameModel = gameModel;
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
                switch (player.getOpenedChests()){
                    case 0:
                        inventoryPose = 0;
                        break;
                    case 1:
                        inventoryPose = 1;
                        break;
                    case 2:
                        inventoryPose = 2;
                        break;
                    case 3:
                        inventoryPose = 3;
                        break;
                }
                for (int i = 0; i < enemies.length; i++) {
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

    public void updateGameObjects(Enemies[] newEnemies, Platform[] newPlatforms) {
        this.enemies = newEnemies;
        this.platforms = newPlatforms;
    }

    // import images for characters
    private void importImages() {
        playerSprites = importImage("/player_sprites.png");
        guardSprites = importImage("/guard_sprites.png");
        lavaSprites = importImage("/lava_sprites.png");
        platformSprites = importImage("/platform_sprite.png");
        inventoryImage = importImage("/inventory.png");
        chestSprites = importImage("/guard_sprites.png");
        levelCompleteImage = importImage("/inventory.png");
        stories = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            stories[i] = inventoryImage.getSubimage(700, i * 450, 700, 450);
        }
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
        graphics.drawImage(playerSprites.getSubimage(xPose * spriteWidth, yPose * spriteHeight, spriteWidth, spriteHeight), (int) player.getXPosition(), (int) player.getYPosition(), null);
        for (Enemies enemy : enemies) {
            if (enemy instanceof Guard) {
                graphics.drawImage(guardSprites.getSubimage(xPose * spriteWidth, enemy.yPose * spriteHeight, spriteWidth, spriteHeight), (int) enemy.getEnemiesXPosition(), (int) enemy.getEnemiesYPosition(), null);
            } else if (enemy instanceof Lava) {
                graphics.drawImage(lavaSprites.getSubimage(xPose * 100, 1, 100, 20), (int) enemy.getEnemiesXPosition(), (int) enemy.getEnemiesYPosition(), null);
            } else if (enemy instanceof Chest) {
                graphics.drawImage(chestSprites.getSubimage(xPose * 40, enemy.yPose * 40, 40, 40), (int) enemy.getEnemiesXPosition(), (int) enemy.getEnemiesYPosition(), null);
            }
            for (Platform platform : platforms) {
                graphics.drawImage(platformSprites.getSubimage(0, 0, 100, 800), platform.getxPosition(), platform.getYPosition(), null);
            }
        }
        if(player.getOpenedChests() == 3 && inputHandler.isInventoryOpen() && inputHandler.isButtonClicked()) {
            graphics.drawImage(stories[GameEngine.getLevelNum() - 1], 1280 / 2 - (700 / 2), 800 / 2 - (450 / 2), null);
        } else if (player.getOpenedChests() <= 3 && inputHandler.isInventoryOpen()) {
            graphics.drawImage(inventoryImage.getSubimage(storyPose, inventoryPose * 450, 700, 450), 1280 / 2 - (700 / 2), 800 / 2 - (450 / 2), null);
        }
        if (gameModel.isLevelComplete()) {
            graphics.drawImage(levelCompleteImage.getSubimage(0, inventoryPose * 450, 700, 450), 1280 / 2 - (700 / 2), 800 / 2 - (450 / 2), null);
        }
    }
}

