package cz.cvut.fel.pjv.entities;

public class Guard extends Entities {

    private static final double MOVE_AMOUNT = 0.2;
    private double initialPosition;
    private double stepsRight = 100;
    private double stepsLeft = 100;

    public Guard(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        this.initialPosition = xPosition;
        this.isEnemy = true;
    }

    // move guard horizontally
    public void moveHorizontally(){
        if (isAlive()) {
            if (entitiesXPosition >= initialPosition + stepsRight) {
                direction = -1;
            } else if (entitiesXPosition <= initialPosition - stepsLeft) {
                direction = 1;
            }
            entitiesXPosition += direction * MOVE_AMOUNT;
        }
    }
}
