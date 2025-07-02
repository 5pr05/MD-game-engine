package cz.cvut.fel.pjv.characters;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Characters{
    protected static double hitboxWidth = 80, hitboxHeight = 128;
    protected static double attackRangeWeight = 100, attackRangeHeight = 170;
    private BufferedImage img, pose;

    public Player(double xPosition, double yPosition){
        super(xPosition, yPosition);
        importImg();
    }

    // import image for player
    private void importImg() {
        InputStream stream = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(stream);
            pose = img.getSubimage(1,1,40,64);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // draw player
    public void drawPlayer(Graphics graphics){
        graphics.drawImage(pose, (int)xPosition, (int)yPosition, 80, 128, null);
    }

    // player x position getter
    public double getXPosition() {
        return this.xPosition;
    }

    // player hitbox width getter
    public double getHitboxWidth() {return hitboxWidth;}
    // player hitbox width getter
    public double getHitboxHeight() {return hitboxHeight;}


    // player x position setter
    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    // player y position getter
    public double getYPosition() {
        return this.yPosition;
    }

    // player y position setter
    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}