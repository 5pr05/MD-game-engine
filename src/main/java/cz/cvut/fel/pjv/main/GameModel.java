package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.entities.*;
import cz.cvut.fel.pjv.level.*;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private Player player;
    private List<Platform> platforms;
    private List<Entities> entities;
    private LevelLoader level;
    private GamePanel gamePanel;
    private boolean levelComplete = false;
    private int passedSectionsCount = 0;

    public GameModel(Player player, List<Platform> platforms, List<Entities> entities, LevelLoader level, GamePanel gamePanel) {
        this.player = player;
        this.platforms = new ArrayList<>(platforms);
        this.entities = new ArrayList<>(entities);
        this.level = level;
        this.gamePanel = gamePanel;
    }

    public void loadNextSection() {
        level.nextSection();
        entities.clear();
        platforms.clear();
        entities.addAll(List.of(level.getEntities()));
        platforms.addAll(List.of(level.getPlatforms()));
        System.out.println("New section!");
        gamePanel.updateGameObjects(level.getEntities(), level.getPlatforms());

        passedSectionsCount++;
        if (passedSectionsCount == 4) {
            levelComplete = true;
        }
    }

    public boolean isPlayerOnPlatform() {
        int playerX = (int) player.getXPosition();
        int playerY = (int) player.getYPosition();
        double playerWidth = player.getHitboxWidth();
        double playerHeight = player.getHitboxHeight();

        for (Platform platform : platforms) {
            if (playerY + playerHeight >= platform.getYPosition() &&
                    playerY + playerHeight <= platform.getYPosition() + platform.getHeight() &&
                    playerX + playerWidth > platform.getxPosition() &&
                    playerX < platform.getxPosition() + platform.getWidth()) {
                player.setYPosition(platform.getYPosition() - playerHeight);
                return true;
            }
        }
        return false;
    }

    public boolean canMoveHorizontally(double newXPosition) {
        double playerWidth = player.getHitboxWidth();
        double playerY = player.getYPosition();
        double playerHeight = player.getHitboxHeight();

        for (Platform platform : platforms) {
            if (newXPosition < platform.getxPosition() + platform.getWidth() &&
                    newXPosition + playerWidth > platform.getxPosition() &&
                    playerY < platform.getYPosition() + platform.getHeight() &&
                    playerY + playerHeight > platform.getYPosition()) {
                return false;
            }
        }
        return true;
    }

    public void attackEntities() {
        for (Entities entity : entities) {
            if (entity != null && entity.isAlive()) {
                if ((Math.abs(entity.getEntitiesXPosition() - player.getXPosition()) <= player.getAttackRangeWidth()) &&
                        (Math.abs(entity.getEntitiesYPosition() - player.getYPosition()) <= player.getAttackRangeHeight())) {
                    if (entity.isChest()) {
                        player.openChest();
                        System.out.println(player.getOpenedChests());
                    }
                    if (entity.isChest()) {
                        entity.kill();
                    } else if (player.getCanAttack()) {
                        entity.kill();
                    }
                }
            }
        }
    }

    public int isPlayerCollidingWithEntity() {
        for (Entities entity : entities) {
            if (entity != null && entity.isAlive()) {
                double playerXPosition = player.getXPosition();
                double playerYPosition = player.getYPosition();

                double entityXPosition = entity.getEntitiesXPosition();
                double entityYPosition = entity.getEntitiesYPosition();

                if ((Math.abs(entityXPosition - playerXPosition) <= player.getHitboxWidth()) &&
                        (Math.abs(entityYPosition - playerYPosition) <= player.getHitboxHeight())) {
                    if (entity.isChest() || entity.isKey() || entity.isDoor()) {
                        return 1;
                    } else if (entity.isEnemy()){
                        return 0;
                    }
                }
            }
        }
        return 2;
    }

    public boolean isLevelComplete() {
        return levelComplete;
    }


    public void createAbility() {
        if (player.getOpenedChests() == 3) {
            switch (GameEngine.getLevelNum()) {
                case 1:
                    player.setCanAttack();
                    System.out.println("*Story*");
                    break;
                case 2:
                    player.setHorizontalMovement();
                    System.out.println("*Story*");
                    break;
            }
        } else {
            System.out.println("All pieces of story are not collected yet!");
        }
    }
}
