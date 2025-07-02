package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.characters.*;

public class Level2 extends Level {

    public Level2(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        createEnemies();
        createPlatforms();
    }

    @Override
    public void createEnemies() {
        this.enemies = new Enemies[]{
                new Guard(300, 300),
                new Guard(600, 300),
                new Lava(700, 350),
                new Chest(30, 300),
                new Chest(90, 300),
                new Chest(130, 300)
        };
    }

    @Override
    public void createPlatforms() {
        this.platforms = new Platform[]{new Platform(0, 0, 100,800),new Platform(110, 600, 100,800),new Platform(300, 500, 100,800), new Platform(450, 200, 100, 20)};
    }
}
