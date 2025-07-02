package cz.cvut.fel.pjv.characters;

public class Enemies extends Characters {
    protected boolean isLava = false;
    protected boolean isChest = false;
    protected int direction = 1;

    protected double enemiesXPosition = this.xPosition;
    protected double enemiesYPosition = this.yPosition;
    public boolean alive = true;

    public int yPose = 0;

    public Enemies(double xPosition, double yPosition) {
        super(xPosition, yPosition);
    }

    // enemies x position getter
    public double getEnemiesXPosition() {
        return enemiesXPosition;
    }

    // enemies y position getter
    public double getEnemiesYPosition() {
        return enemiesYPosition;
    }

    // direction getter
    public int getDirection(){
        return direction;
    }

    // kill enemy
    public void kill() {
        if (!isLava) {
            alive = false;
            if (isChest){
                System.out.println("Chest opened!");
            } else {
                System.out.println("Enemy killed!");
            }
        }
    }

    // alive getter
    public boolean isAlive() {
        return alive;
    }

    // is chest getter
    public boolean isChest() {return isChest;}

    // update enemy
    public void update() {
        if (this instanceof Guard) {
            Guard guard = (Guard) this;
            enemiesXPosition = guard.xPosition;
            enemiesYPosition = guard.yPosition;
            guard.moveHorizontally();
        } else {
            enemiesXPosition = this.xPosition;
            enemiesYPosition = this.yPosition;
        }
    }

}
