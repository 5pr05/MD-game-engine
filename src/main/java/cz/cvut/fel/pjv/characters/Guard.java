package cz.cvut.fel.pjv.characters;

public class Guard extends Enemies{
    private static final double MOVE_AMOUNT = 0.2;

    public Guard(double xPosition, double yPosition) {
        super(xPosition, yPosition);
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
