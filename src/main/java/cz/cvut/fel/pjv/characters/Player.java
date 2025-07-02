package cz.cvut.fel.pjv.characters;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Characters{
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

    // get player's x position
    public double getXPosition() {
        return this.xPosition;
    }

    // set player's x position
    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    // get player's y position
    public double getYPosition() {
        return this.yPosition;
    }

    // set player's y position
    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}

