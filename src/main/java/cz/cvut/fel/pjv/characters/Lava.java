package cz.cvut.fel.pjv.characters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Lava  extends Enemies{
    private BufferedImage img, pose;


    public Lava(double xPosition, double yPosition, Player player) {
        super(xPosition, yPosition, player);
        importImg();
        this.isLava = true;
    }

    // import image for lava
    private void importImg() {
        InputStream stream = getClass().getResourceAsStream("/guard_sprites.png");
        try {
            img = ImageIO.read(stream);
            pose = img.getSubimage(1,1,100,20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // draw lava
    public void drawLava(Graphics graphics){
        graphics.drawImage(pose, (int)xPosition, (int)yPosition, 200, 40, null);
    }
}