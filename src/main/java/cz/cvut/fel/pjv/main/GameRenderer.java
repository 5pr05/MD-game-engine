package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.entities.*;
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
    private Entities[] entities;
    private Platform[] platforms;
    private BufferedImage playerSprites, guardSprites, lavaSprites, platformSprites, inventoryImage, chestSprites, levelCompleteImage, abilitiesImage, bgImage, keySprites, doorSprites;
    private BufferedImage[] stories;
    private int xPose, yPose, inventoryPose, storyPose;
    private int spriteWidth = 40;
    private int spriteHeight = 64;
    private int numSpritesX = 3;
    private int delay = 20;
    private int currentDelay = 0;

    public GameRenderer(Player player, Entities[] entities, Platform[] platforms, InputHandler inputHandler, GameModel gameModel) {
        this.player = player;
        this.entities = entities;
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
                for (int i = 0; i < entities.length; i++) {
                    if (entities[i] != null) {
                        if (entities[i].isAlive()) {
                            if (entities[i].getDirection() > 0) {
                                entities[i].yPose = 0;
                            } else if (entities[i].getDirection() <= 0) {
                                entities[i].yPose = 1;
                            }
                        } else {
                            entities[i].yPose = 2;
                        }
                    }
                }
            }
            currentDelay = 0;
        } else {
            currentDelay++;
        }
    }

    public void updateGameObjects(Entities[] newEntities, Platform[] newPlatforms) {
        this.entities = newEntities;
        this.platforms = newPlatforms;
    }

    // import images for characters
    private void importImages() {
        playerSprites = importImage("/player_sprites.png");
        guardSprites = importImage("/guard_sprites.png");
        lavaSprites = importImage("/lava_sprites.png");
        platformSprites = importImage("/platform_sprite.png");
        inventoryImage = importImage("/inventory.png");
        chestSprites = importImage("/chest_sprites.png");
        levelCompleteImage = importImage("/level_completed.png");
        abilitiesImage = importImage("/abilities_sprites.png");
        keySprites = importImage("/key_sprites.png");
        doorSprites = importImage("/door_sprites.png");
        bgImage = importImage("/level_bg.png");
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
        graphics.drawImage(bgImage, 0, 0, null);
        graphics.drawImage(playerSprites.getSubimage(xPose * spriteWidth, yPose * spriteHeight, spriteWidth, spriteHeight), (int) player.getXPosition(), (int) player.getYPosition(), null);
        for (Entities entity : entities) {
            if (entity instanceof Guard) {
                graphics.drawImage(guardSprites.getSubimage(xPose * spriteWidth, entity.yPose * spriteHeight, spriteWidth, spriteHeight), (int) entity.getEntitiesXPosition(), (int) entity.getEntitiesYPosition()-14, null);
            } else if (entity instanceof Lava) {
                graphics.drawImage(lavaSprites.getSubimage(xPose * 20, 1, 20, 45), (int) entity.getEntitiesXPosition(), (int) entity.getEntitiesYPosition(), null);
            } else if (entity instanceof Chest) {
                graphics.drawImage(chestSprites.getSubimage(0, entity.yPose * 40, 40, 40), (int) entity.getEntitiesXPosition(), (int) entity.getEntitiesYPosition()+10, null);
            } else if (entity instanceof Key) {
                graphics.drawImage(keySprites.getSubimage(xPose * 40, entity.yPose * 40, 40, 40), (int) entity.getEntitiesXPosition(), (int) entity.getEntitiesYPosition()-10, null);
            } else if (entity instanceof Door) {
                graphics.drawImage(doorSprites.getSubimage(0, entity.yPose * spriteHeight, spriteWidth, spriteHeight), (int) entity.getEntitiesXPosition(), (int) entity.getEntitiesYPosition()-14, null);
            }
            for (Platform platform : platforms) {
                graphics.drawImage(platformSprites.getSubimage(0, 0, 20, 20), platform.getxPosition(), platform.getYPosition(), null);
            }
        }
        if(gameModel.isAbilityCreated() && inputHandler.isInventoryOpen() && inputHandler.isLeftButtonClicked()) {
            graphics.drawImage(stories[GameEngine.getLevelNum() - 1], 1280 / 2 - (700 / 2), 800 / 2 - (450 / 2), null);
        } else if (player.getOpenedChests() <= 3 && inputHandler.isInventoryOpen()) {
            graphics.drawImage(inventoryImage.getSubimage(storyPose, inventoryPose * 450, 700, 450), 1280 / 2 - (700 / 2), 800 / 2 - (450 / 2), null);
        }
        if (gameModel.isLevelComplete()) {
            graphics.drawImage(levelCompleteImage.getSubimage(0, inventoryPose * 450, 700, 450), 1280 / 2 - (700 / 2), 800 / 2 - (450 / 2), null);
        }
        if (player.getCanAttack()){
            graphics.drawImage(abilitiesImage.getSubimage(0, 0, 40, 40), 1280 / 2 - 40, 10, null);
        }
        if (player.isKeyPicked()){
            graphics.drawImage(abilitiesImage.getSubimage(40, 0, 40, 40), 1280 / 2 - 90, 10, null);
        }
        if (player.getHyperRun()){
            graphics.drawImage(abilitiesImage.getSubimage(80, 0, 40, 40), 1280 / 2 + 10, 10, null);
        }
    }
}

