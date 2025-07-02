package cz.cvut.fel.pjv.characters;

public class Enemies extends Characters {
    private Player player;
    protected boolean isLava = false;
    protected static int direction = 1;

    protected double enemiesXPosition = this.xPosition;
    protected double enemiesYPosition = this.yPosition;
    private boolean alive = true;

    public Enemies(double xPosition, double yPosition, Player player) {
        super(xPosition, yPosition);
        this.player = player;
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
            System.out.println("Enemy killed!");
        }
    }

    // alive getter
    public boolean isAlive() {
        return alive;
    }

    // update enemy
    public void update() {
        double playerXPosition = player.getXPosition();
        double playerYPosition = player.getYPosition();

        if (this instanceof Guard) {
            Guard guard = (Guard) this;
            enemiesXPosition = guard.xPosition;
            enemiesYPosition = guard.yPosition;
            guard.moveHorizontally();
        } else {
            enemiesXPosition = this.xPosition;
            enemiesYPosition = this.yPosition;
        }

        // hitbox logic
        if (isAlive() && (Math.abs(enemiesXPosition - playerXPosition) <= Player.hitboxWidth) && (Math.abs(enemiesYPosition - playerYPosition) <= Player.hitboxHeight)) {
            if (PlayerController.isAlive()) {
                PlayerController.kill();
            }
        }
    }

}
