package cz.cvut.fel.pjv.entities;

public class Lava  extends Entities {

    public Lava(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        this.isImmortal = true;
        this.isEnemy = true;
    }
}
