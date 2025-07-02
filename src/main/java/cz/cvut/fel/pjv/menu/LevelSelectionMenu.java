package cz.cvut.fel.pjv.menu;

import javax.swing.*;
import cz.cvut.fel.pjv.main.*;
import cz.cvut.fel.pjv.level.LevelLoader;

public class LevelSelectionMenu extends BaseMenu {
    private JFrame levelSelectionFrame;
    private GameWindow window;
    private GameEngine gameEngine;

    public LevelSelectionMenu() {
        this.levelSelectionFrame = setupFrame("Myths And Dreams");
        addButtonToPanel(levelSelectionFrame, createLevelSelectionPanel(), "/level_selector_bg.png");
    }

    public void startLevel(int levelNum) {
        this.gameEngine = new GameEngine(levelNum, "levels/level" + levelNum + ".txt");
        LevelLoader levelLoader = new LevelLoader(0, 0, "levels/level" + levelNum + ".txt");
        gameEngine.getPanel().setPreferredSize(levelSelectionFrame.getSize());
        this.window = new GameWindow(levelSelectionFrame, gameEngine.getPanel());
    }


    private JPanel createLevelSelectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        addButton(panel, "Level 1", 100, 250, 100, 100, e -> startLevel(1), "/level1.png");
        addButton(panel, "Level 2", 300, 400, 100, 100, e -> startLevel(2), "/level2.png");

        return panel;
    }
}
