package cz.cvut.fel.pjv.characters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Guard extends Enemies{
    private int direction = 1;
    private static final double MOVE_AMOUNT = 0.2;
    private BufferedImage img, pose;

    public Guard(double xPosition, double yPosition, Player player) {
        super(xPosition, yPosition, player);
        importImg();
    }

    // import image for guard
    private void importImg() {
        InputStream stream = getClass().getResourceAsStream("/guard_sprites.png");
        try {
            img = ImageIO.read(stream);
            pose = img.getSubimage(1,1,40,64);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // draw guard
    public void drawGuard(Graphics graphics){
        graphics.drawImage(pose, (int)xPosition, (int)yPosition, 80, 128, null);
    }

    // move guard horizontally
    public void moveHorizontally(){
        if (isAlive()) {
            if (xPosition > 1000 || xPosition < 222) {
                direction *= -1;
            }
            xPosition += direction * MOVE_AMOUNT;
        }
    }
}
