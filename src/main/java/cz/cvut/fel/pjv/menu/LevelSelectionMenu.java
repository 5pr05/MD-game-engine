package cz.cvut.fel.pjv.menu;

import javax.swing.*;
import cz.cvut.fel.pjv.main.*;

public class LevelSelectionMenu extends BaseMenu {
    private JFrame levelSelectionFrame;
    private GameWindow window;
    private GameEngine gameEngine;

    public LevelSelectionMenu() {
        this.levelSelectionFrame = setupFrame("Myths And Dreams");
        addButtonToPanel(levelSelectionFrame, createLevelSelectionPanel(), "/lava_sprites.png");
    }

    public void startLevel(int levelNum) {
        this.gameEngine = new GameEngine(levelNum, "levels/level"+levelNum+".txt");
        gameEngine.getPanel().setPreferredSize(levelSelectionFrame.getSize());
        this.window = new GameWindow(levelSelectionFrame, gameEngine.getPanel());
    }


    private JPanel createLevelSelectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        addButton(panel, "Level 1", 100, 250, 200, 50, e -> startLevel(1), "/button.png");
        addButton(panel, "Level 2", 300, 250, 200, 50, e -> startLevel(2), "/button.png");

        return panel;
    }
}
