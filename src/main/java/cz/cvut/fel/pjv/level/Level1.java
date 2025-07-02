package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.characters.*;

public class Level1 extends Level {

    public Level1(double xPosition, double yPosition) {
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
        this.platforms = new Platform[]{new Platform(300, 250, 100,800), new Platform(450, 200, 100, 20)};
    }
}
