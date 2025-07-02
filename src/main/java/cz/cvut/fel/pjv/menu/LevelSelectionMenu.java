package cz.cvut.fel.pjv.menu;

import cz.cvut.fel.pjv.main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LevelSelectionMenu {

    private JFrame levelSelectionFrame;
    private GameWindow window;
    private GameEngine gameEngine;

    public LevelSelectionMenu(MenuRenderer renderer) {
        this.levelSelectionFrame = renderer.setupLevelSelectionFrame();
        renderer.addButtonsToLevelSelectionMenu(levelSelectionFrame, this);
    }

    // start level button
    public void startLevel(int level) {
        this.gameEngine = new GameEngine(level);
        gameEngine.getPanel().setPreferredSize(levelSelectionFrame.getSize());
        this.window = new GameWindow(levelSelectionFrame, gameEngine.getPanel());
    }

    // back button
    public void back() {
        levelSelectionFrame.dispose();
    }
}