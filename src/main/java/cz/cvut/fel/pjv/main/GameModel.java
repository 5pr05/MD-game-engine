package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.characters.*;
import cz.cvut.fel.pjv.level.Platform;
import java.util.List;

public class GameModel {
    private Player player;
    private List<Platform> platforms;
    private List<Enemies> enemies;

    public GameModel(Player player, List<Platform> platforms, List<Enemies> enemies) {
        this.player = player;
        this.platforms = platforms;
        this.enemies = enemies;
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
                player.setYPosition(platform.getYPosition() - playerHeight);  // Correctly adjust player's position
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
                    enemy.kill();
                }
            }
        }
    }

    public boolean isPlayerCollidingWithEnemy() {
        for (Enemies enemy : enemies) {
            if (enemy != null && enemy.isAlive()) {
                double playerXPosition = player.getXPosition();
                double playerYPosition = player.getYPosition();

                double enemyXPosition = enemy.getEnemiesXPosition();
                double enemyYPosition = enemy.getEnemiesYPosition();

                if ((Math.abs(enemyXPosition - playerXPosition) <= player.getHitboxWidth()) &&
                        (Math.abs(enemyYPosition - playerYPosition) <= player.getHitboxHeight())) {
                    return true;
                }
            }
        }
        return false;
    }
}
