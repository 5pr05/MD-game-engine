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
        addButtonToPanel(levelCreatorFrame, createLevelCreatorPanel(), "/wall_background.png");
    }

    public void startLevel(int slotNum) {
        this.gameEngine = new GameEngine(slotNum, "slots/slot"+slotNum+".txt");
        gameEngine.getPanel().setPreferredSize(levelCreatorFrame.getSize());
        this.window = new GameWindow(levelCreatorFrame, gameEngine.getPanel());
    }

    private JPanel createLevelCreatorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        addButton(panel, "Slot 1", 100, 200, 220, 50, e -> startLevel(1), "/slot1.png");
        addButton(panel, "Slot 2", 100, 270, 220, 50, e -> startLevel(2), "/slot2.png");
        addButton(panel, "Slot 3", 100, 340, 220, 50, e -> startLevel(3), "/slot3.png");


        addButton(panel, "Edit 1", 350, 200, 120, 50, e -> openFile("src/main/resources/slots/slot1.txt"), "/edit1.png");
        addButton(panel, "Edit 2", 350, 270, 120, 50, e -> openFile("src/main/resources/slots/slot2.txt"), "/edit2.png");
        addButton(panel, "Edit 3", 350, 340, 120, 50, e -> openFile("src/main/resources/slots/slot3.txt"), "/edit3.png");

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
