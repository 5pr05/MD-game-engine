package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.characters.*;
import cz.cvut.fel.pjv.level.*;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private Player player;
    private List<Platform> platforms;
    private List<Enemies> enemies;
    private Level level;
    private GamePanel gamePanel;
    private boolean levelComplete = false;
    private int passedSectionsCount = 0;

    public GameModel(Player player, List<Platform> platforms, List<Enemies> enemies, Level level, GamePanel gamePanel) {
        this.player = player;
        this.platforms = new ArrayList<>(platforms);
        this.enemies = new ArrayList<>(enemies);
        this.level = level;
        this.gamePanel = gamePanel;
    }

    public void loadNextSection() {
        level.nextSection();
        enemies.clear();
        platforms.clear();
        enemies.addAll(List.of(level.getEnemies()));
        platforms.addAll(List.of(level.getPlatforms()));
        System.out.println("New section!");
        gamePanel.updateGameObjects(level.getEnemies(), level.getPlatforms());

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

    public void attackEnemies() {
        for (Enemies enemy : enemies) {
            if (enemy != null && enemy.isAlive()) {
                if ((Math.abs(enemy.getEnemiesXPosition() - player.getXPosition()) <= player.getAttackRangeWidth()) &&
                        (Math.abs(enemy.getEnemiesYPosition() - player.getYPosition()) <= player.getAttackRangeHeight())) {
                    if (enemy.isChest()) {
                        player.openChest();
                        System.out.println(player.getOpenedChests());
                    }
                    if (enemy.isChest()) {
                        enemy.kill();
                    } else if (player.getCanAttack()) {
                        enemy.kill();
                    }
                }
            }
        }
    }

    public int isPlayerCollidingWithEnemy() {
        for (Enemies enemy : enemies) {
            if (enemy != null && enemy.isAlive()) {
                double playerXPosition = player.getXPosition();
                double playerYPosition = player.getYPosition();

                double enemyXPosition = enemy.getEnemiesXPosition();
                double enemyYPosition = enemy.getEnemiesYPosition();

                if ((Math.abs(enemyXPosition - playerXPosition) <= player.getHitboxWidth()) &&
                        (Math.abs(enemyYPosition - playerYPosition) <= player.getHitboxHeight())) {
                    if (enemy.isChest()) {
                        return 1;
                    } else {
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
