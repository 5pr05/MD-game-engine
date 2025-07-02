package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.characters.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Platform {
    private int xPosition, yPosition;
    private int width, height;

    public Platform(int xPosition, int yPosition, int width, int height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        importImg();
    }

    private BufferedImage img, pose;

    // import image for platform
    private void importImg() {
        InputStream stream = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(stream);
            pose = img.getSubimage(1,1,100,20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    // draw platform
    public void drawPlatform(Graphics graphics) {
        graphics.drawImage(pose, xPosition, yPosition, 200, 40, null);
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

