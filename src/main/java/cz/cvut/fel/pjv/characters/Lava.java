package cz.cvut.fel.pjv.characters;

public class Lava  extends Enemies{

    public Lava(double xPosition, double yPosition, Player player) {
        super(xPosition, yPosition, player);
        this.isLava = true;
    }
}
