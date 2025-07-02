package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.characters.Player;

public class Platform {
    private int xPosition, yPosition;
    private int width, height;

    public Platform(int xPosition, int yPosition, int width, int height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    // x position getter
    public int getxPosition() {
        return xPosition;
    }

    // y position getter
    public int getYPosition() {
        return yPosition;
    }

    // width getter
    public int getWidth() {
        return width;
    }

    // height getter
    public int getHeight() {
        return height;
    }

    // check if player is on platform
    public boolean isPlayerOnPlatform(Player player) {
        int playerX = (int) player.getXPosition();
        int playerY = (int) player.getYPosition();
        double playerWidth = player.getHitboxWidth();
        double playerHeight = player.getHitboxHeight();

        if (playerY + playerHeight >= yPosition && playerY + playerHeight <= yPosition + height) {
            if (playerX + playerWidth > xPosition && playerX < xPosition + width) {
                return true;
            }
        }

        return false;
    }
}
