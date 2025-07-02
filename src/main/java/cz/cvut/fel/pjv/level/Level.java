package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.characters.*;

public abstract class Level {
    protected Player player;
    protected Enemies[] enemies;
    protected Platform[] platforms;

    public Level(double xPosition, double yPosition) {
        this.player = new Player(xPosition, yPosition);
    }

    // player getter
    public Player getPlayer() {
        return player;
    }

    // enemies getter
    public Enemies[] getEnemies() {
        return enemies;
    }

    // platforms getter
    public Platform[] getPlatforms() {
        return platforms;
    }

    // abstract methods
    public abstract void createEnemies();
    public abstract void createPlatforms();
}
