package cz.cvut.fel.pjv.characters;

public class Guard extends Enemies{
    private static final double MOVE_AMOUNT = 0.2;

    public Guard(double xPosition, double yPosition, Player player) {
        super(xPosition, yPosition, player);
    }

    // move guard horizontally
    public void moveHorizontally(){
        if (isAlive()) {
            if (xPosition > 1000 || xPosition < 222) {
                Enemies.direction *= -1;
            }
            xPosition += Enemies.direction * MOVE_AMOUNT;
        }
    }
}
