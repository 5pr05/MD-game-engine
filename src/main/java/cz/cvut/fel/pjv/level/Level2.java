package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.characters.*;

import java.util.HashMap;
import java.util.Map;

public class Level2 implements Level {
    private Map<Integer, Section> sections;
    private int currentSectionIndex;
    private Player player;
    private Enemies[] enemies;
    private Platform[] platforms;

    public Level2(double xPosition, double yPosition) {
        player = new Player(xPosition, yPosition);
        sections = new HashMap<>();
        currentSectionIndex = 0;
        createSections();
        loadSection(currentSectionIndex);
    }

    private void createSections() {
        sections.put(0, new Section(
                new Enemies[]{
                        new Guard(300, 300),
                        new Guard(600, 300),
                        new Lava(700, 350),
                        new Chest(30, 300),
                        new Chest(90, 300),
                        new Chest(130, 300)
                },
                new Platform[]{
                        new Platform(300, 250, 100, 20),
                        new Platform(450, 200, 100, 20)
                }
        ));
        sections.put(1, new Section(
                new Enemies[]{
                        new Lava(700, 350),
                        new Chest(30, 300),
                        new Chest(90, 300),
                        new Chest(130, 300)
                },
                new Platform[]{
                        new Platform(450, 200, 100, 20)
                }
        ));
        sections.put(2, new Section(
                new Enemies[]{
                        new Chest(30, 300),
                        new Chest(90, 300),
                        new Chest(130, 300)
                },
                new Platform[]{
                        new Platform(300, 250, 100, 20),
                        new Platform(450, 200, 100, 20)
                }
        ));
        sections.put(3, new Section(
                new Enemies[]{
                        new Guard(300, 300),
                },
                new Platform[]{
                }
        ));
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Enemies[] getEnemies() {
        return enemies;
    }

    @Override
    public Platform[] getPlatforms() {
        return platforms;
    }

    @Override
    public void nextSection() {
        currentSectionIndex++;
        if (currentSectionIndex >= sections.size()) {
            currentSectionIndex = 0;
        }
        loadSection(currentSectionIndex);
    }

    private void loadSection(int sectionIndex) {
        Section section = sections.get(sectionIndex);
        if (section != null) {
            this.enemies = section.enemies;
            this.platforms = section.platforms;
        }
    }

    private static class Section {
        Enemies[] enemies;
        Platform[] platforms;

        Section(Enemies[] enemies, Platform[] platforms) {
            this.enemies = enemies;
            this.platforms = platforms;
        }
    }
}
