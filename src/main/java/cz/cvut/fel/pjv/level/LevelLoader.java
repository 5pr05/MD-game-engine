package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.entities.*;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private Map<Integer, Section> sections;
    private int currentSectionIndex;
    private Player player;
    private List<Entities> entities;
    private List<Platform> platforms;

    public LevelLoader(double xPosition, double yPosition, String filename) {
        player = new Player(xPosition, yPosition);
        sections = new HashMap<>();
        currentSectionIndex = 0;
        entities = new ArrayList<>();
        platforms = new ArrayList<>();
        loadSectionsFromFile(filename);
        loadSection(currentSectionIndex);
    }


    private void loadSectionsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" +filename))) {
            String line;
            int sectionIndex = 0;
            List<Entities> entities = new ArrayList<>();
            List<Platform> platforms = new ArrayList<>();
            int row = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals("~~~~~~                    NEW SECTION                    ~~~~~~~")) {
                    sections.put(sectionIndex, new Section(entities, platforms));
                    sectionIndex++;
                    entities = new ArrayList<>();
                    platforms = new ArrayList<>();
                    row = 0;
                    continue;
                }
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    switch (c) {
                        case 'P':
                            platforms.add(new Platform(i*20, row*50));
                            break;
                        case 'G':
                            entities.add(new Guard(i*20, row*50));
                            break;
                        case 'L':
                            entities.add(new Lava(i*20, row*50));
                            break;
                        case 'C':
                            entities.add(new Chest(i*20, row*50));
                            break;
                        case 'K':
                            break;
                        case 'D':
                            break;
                        default:
                            break;
                    }
                }
                row++;
            }
            sections.put(sectionIndex, new Section(entities, platforms));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Entities[] getEntities() {
        return entities.toArray(new Entities[entities.size()]);
    }

    public Platform[] getPlatforms() {
        return platforms.toArray(new Platform[platforms.size()]);
    }

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
            this.entities = section.entities;
            this.platforms = section.platforms;
        }
    }

    private static class Section {
        List<Entities> entities;
        List<Platform> platforms;

        Section(List<Entities> entities, List<Platform> platforms) {
            this.entities = entities;
            this.platforms = platforms;
        }
    }
}
