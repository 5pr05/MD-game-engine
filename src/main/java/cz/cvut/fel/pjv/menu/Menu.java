package cz.cvut.fel.pjv.menu;

import javax.swing.*;
import cz.cvut.fel.pjv.main.*;
import cz.cvut.fel.pjv.level.LevelLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu extends BaseMenu {
    private JFrame menuFrame;
    private LevelSelectionMenu levelSelectionMenu;
    private GameEngine gameEngine;
    private GameWindow window;

    public Menu() {
        this.menuFrame = setupFrame("Myths And Dreams");
        addButtonToPanel(menuFrame, createMenuPanel(), "/platform_sprite.png");
    }

    public void startGame() {
        this.levelSelectionMenu = new LevelSelectionMenu();
        menuFrame.dispose();
    }

    public void loadSaved() {
        String savedFile = "src/main/resources/saved.txt";
        int savedLevel = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(savedFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Level:")) {
                    savedLevel = Integer.parseInt(br.readLine().trim());
                    System.out.println("Loaded saved level" + savedLevel +"!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String levelFile = "levels/level" + savedLevel + ".txt";
        try {
            this.gameEngine = new GameEngine(savedLevel, levelFile);
            gameEngine.getPanel().setPreferredSize(menuFrame.getSize());
            this.window = new GameWindow(menuFrame, gameEngine.getPanel());

            LevelLoader levelLoader = gameEngine.getLevelLoader();
            levelLoader.loadPlayerData(savedFile);

            menuFrame.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createLevel() {
        new LevelCreatorMenu();
        menuFrame.dispose();
    }

    public void quit() {
        menuFrame.dispose();
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        addButton(panel, "Start Game", 950, 400, 200, 50, e -> startGame(), "/button.png");
        addButton(panel, "Load Saved", 950, 460, 200, 50, e -> loadSaved(), "/button.png");
        addButton(panel, "Create Level", 950, 520, 200, 50, e -> createLevel(), "/button.png");
        addButton(panel, "Quit", 950, 580, 200, 50, e -> quit(), "/button.png");

        return panel;
    }
}
