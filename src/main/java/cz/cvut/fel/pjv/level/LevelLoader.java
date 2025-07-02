package cz.cvut.fel.pjv.level;

import cz.cvut.fel.pjv.entities.*;
import cz.cvut.fel.pjv.main.GameEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LevelLoader {
    private Map<Integer, Section> sections;
    private int currentSectionIndex;
    private Player player;
    private GameEngine gameEngine;
    private List<Entities> entities;
    private List<Platform> platforms;

    public LevelLoader(double xPosition, double yPosition, String filename) {
        player = new Player(xPosition, yPosition);
        sections = new HashMap<>();
        currentSectionIndex = 0;
        entities = new ArrayList<>();
        platforms = new ArrayList<>();
        if (loadLevelFromFile(filename)) {
            loadSection(currentSectionIndex);
        }
    }

    private boolean loadLevelFromFile(String filename) {
        boolean validLevel = true;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + filename))) {
            String line;
            int sectionIndex = 0;
            List<Entities> entities = new ArrayList<>();
            List<Platform> platforms = new ArrayList<>();
            int row = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals("~~~~~~                    NEW SECTION                    ~~~~~~~")) {
                    if (entities.isEmpty()) {
                        validLevel = false;
                    }
                    sections.put(sectionIndex, new Section(entities, platforms));
                    sectionIndex++;
                    entities = new ArrayList<>();
                    platforms = new ArrayList<>();
                    row = 0;
                    continue;
                }
                if (line.equals(" P - platform, G - guard, L - lava, C - chest, D - door, K - key")) {
                    continue;
                }
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    switch (c) {
                        case 'P':
                            platforms.add(new Platform(i * 20, row * 50));
                            break;
                        case 'G':
                            entities.add(new Guard(i * 20, row * 50));
                            break;
                        case 'L':
                            entities.add(new Lava(i * 20, row * 50));
                            break;
                        case 'C':
                            entities.add(new Chest(i * 20, row * 50));
                            break;
                        case 'K':
                            entities.add(new Key(i * 20, row * 50));
                            break;
                        case 'D':
                            entities.add(new Door(i * 20, row * 50));
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
            validLevel = false;
        }
        if (!validLevel){
            System.out.println("Some sections do not have entities!");
        }
        return validLevel;
    }

    public void loadPlayerData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int level = 0;
            int location = 0, openedChests = 0;
            double xPosition = 0;
            double yPosition = 0;
            List<String> inventory = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Level:")) {
                    level = Integer.parseInt(br.readLine().trim());
                } else if (line.startsWith("Location:")) {
                    location = Integer.parseInt(br.readLine().trim());
                } else if (line.startsWith("Coordinates:")) {
                    xPosition = Double.parseDouble(br.readLine().trim());
                    yPosition = Double.parseDouble(br.readLine().trim());
                } else if (line.startsWith("Inventory:")) {
                    while ((line = br.readLine()) != null && !line.isEmpty()) {
                        inventory.add(line.trim());
                    }
                } else if (line.startsWith("Opened chests:")) {
                    openedChests = Integer.parseInt(br.readLine().trim());
                }
            }

            currentSectionIndex = location;
            player.setXPosition(xPosition);
            player.setYPosition(yPosition);
            player.setInventory(inventory);
            player.setOpenedChests(openedChests);

            gameEngine.setLevelNum(level);
            loadSection(currentSectionIndex);

            System.out.println("Loaded: Level" + level + ", Location: " + location + ", Coordinates: " + xPosition + ", " + yPosition);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePlayerData(String filename) {
        try (FileWriter writer = new FileWriter("src/main/resources/" + filename)) {
            writer.write("Level:\n");
            writer.write(gameEngine.getLevelNum() + "\n\n");
            writer.write("Location:\n");
            writer.write(currentSectionIndex + "\n\n");
            writer.write("Coordinates:\n");
            writer.write(player.getXPosition() + "\n");
            writer.write(player.getYPosition() + "\n\n");
            writer.write("Opened chests:\n");
            writer.write(player.getOpenedChests() + "\n\n");
            writer.write("Inventory:\n");
            for (String item : player.getInventory()) {
                writer.write(item + "\n");
            }
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
