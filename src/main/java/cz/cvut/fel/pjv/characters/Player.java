package cz.cvut.fel.pjv.characters;

import cz.cvut.fel.pjv.inputs.*;

public class Player extends Characters{
    protected static double hitboxWidth = 40, hitboxHeight = 64;
    protected static double attackRangeWidth = 50, attackRangeHeight = 84;
    private static InputHandler inputHandler;

    public Player(double xPosition, double yPosition){
        super(xPosition, yPosition);
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
