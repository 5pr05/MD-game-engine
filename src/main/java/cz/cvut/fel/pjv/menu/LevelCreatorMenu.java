package cz.cvut.fel.pjv.menu;

import cz.cvut.fel.pjv.main.GameEngine;
import cz.cvut.fel.pjv.main.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LevelCreatorMenu extends BaseMenu {
    private JFrame levelCreatorFrame;
    private GameEngine gameEngine;
    private GameWindow window;

    public LevelCreatorMenu() {
        this.levelCreatorFrame = setupFrame("Myths and Dream");
        addButtonToPanel(levelCreatorFrame, createLevelCreatorPanel(), "/platform_sprite.png");
    }

    public void startLevel(int slotNum) {
        this.gameEngine = new GameEngine(slotNum, "slots/slot"+slotNum+".txt");
        gameEngine.getPanel().setPreferredSize(levelCreatorFrame.getSize());
        this.window = new GameWindow(levelCreatorFrame, gameEngine.getPanel());
    }

    private JPanel createLevelCreatorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        addButton(panel, "Slot 1", 100, 200, 200, 50, e -> startLevel(1), "/button.png");
        addButton(panel, "Slot 2", 100, 270, 200, 50, e -> startLevel(2), "/button.png");
        addButton(panel, "Slot 3", 100, 340, 200, 50, e -> startLevel(3), "/button.png");


        addButton(panel, "Edit 1", 350, 200, 100, 50, e -> openFile("src/main/resources/slots/slot1.txt"), "/button.png");
        addButton(panel, "Edit 2", 350, 270, 100, 50, e -> openFile("src/main/resources/slots/slot2.txt"), "/button.png");
        addButton(panel, "Edit 3", 350, 340, 100, 50, e -> openFile("src/main/resources/slots/slot3.txt"), "/button.png");

        return panel;
    }

    private void openFile(String fileName) {
        try {
            Desktop.getDesktop().open(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
