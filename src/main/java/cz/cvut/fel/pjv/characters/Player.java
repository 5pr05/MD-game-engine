package cz.cvut.fel.pjv.characters;

public class Player extends Characters{
    protected static double hitboxWidth = 40, hitboxHeight = 64;
    protected static double attackRangeWidth = 100, attackRangeHeight = 84;
    private double horizontalMovement = 1.5;
    private int jumpSpeed = 3;
    private int fallSpeed = 5;
    private static boolean alive = true;
    private boolean canAttack = true;

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

    // player attack arrange width getter
    public double getAttackRangeWidth() {return attackRangeWidth;}

    // player attack arrange width getter
    public double getAttackRangeHeight() {return attackRangeHeight;}

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

    // player horizontal movement step getter
    public double getHorizontalMovement(){
        return horizontalMovement;
    }

    // player jump speed getter
    public int getJumpSpeed(){
        return jumpSpeed;
    }

    // can attack setter
    public void setCanAttack(){
        canAttack = true;
    }

    // can attack getter
    public boolean getCanAttack(){
        return canAttack;
    }

    // is player alive getter
    public static boolean isAlive() {
        return alive;
    }

    // kill player
    public void kill() {alive =false;}
}
