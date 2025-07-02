package cz.cvut.fel.pjv.entities;

public class Key extends Entities {

    public Key(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        this.isKey = true;
    }
}
