package cz.cvut.fel.pjv.entities;

public class Entities {
    protected boolean isImmortal = false;
    protected boolean isChest = false;
    protected boolean isEnemy = false;
    protected boolean isKey = false;
    protected boolean isDoor = false;
    protected int direction = 1;


    protected double entitiesXPosition;
    protected double entitiesYPosition;
    public boolean alive = true;

    public int yPose = 0;

    public Entities(double xPosition, double yPosition) {
        this.entitiesXPosition = xPosition;
        this.entitiesYPosition = yPosition;
    }

    // entities x position getter
    public double getEntitiesXPosition() {
        return entitiesXPosition;
    }

    // entities y position getter
    public double getEntitiesYPosition() {
        return entitiesYPosition;
    }

    // direction getter
    public int getDirection(){
        return direction;
    }

    // kill entities
    public void kill() {
        if (!isImmortal) {
            alive = false;
            if (isEnemy) {
                System.out.println("Enemy killed!");
            } else if (isKey) {
                System.out.println("Key picked!");
            } else if (isDoor) {
                System.out.println("Door opened!");
            }
        }
    }

    // alive getter
    public boolean isAlive() {
        return alive;
    }

    // getters
    public boolean isChest() {return isChest;}
    public boolean isEnemy() {return isEnemy;}
    public boolean isKey() {return isKey;}
    public boolean isDoor() {return isDoor;}

    // update entity
    public void update() {
        if (this instanceof Guard) {
            Guard guard = (Guard) this;
            entitiesXPosition = guard.getEntitiesXPosition();
            entitiesYPosition = guard.getEntitiesYPosition();
            guard.moveHorizontally();
        } else {
            entitiesXPosition = this.getEntitiesXPosition();
            entitiesYPosition = this.getEntitiesYPosition();
        }
    }
}
