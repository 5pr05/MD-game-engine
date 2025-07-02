package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.characters.*;
public interface Level {
    Player getPlayer();
    Enemies[] getEnemies();
    Platform[] getPlatforms();
    void nextSection();
}
