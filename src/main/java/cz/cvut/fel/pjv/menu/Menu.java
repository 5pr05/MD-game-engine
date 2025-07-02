package cz.cvut.fel.pjv.menu;

import cz.cvut.fel.pjv.main.*;
import javax.swing.*;

public class Menu {
    private JFrame menuFrame;
    private GameWindow window;
    private GameEngine gameEngine;

    public Menu(MenuRenderer renderer) {
        this.menuFrame = renderer.setupMenuFrame();
        renderer.addButtonsToMenu(menuFrame, this);
    }

    // start game button
    public void startGame() {
        this.gameEngine = new GameEngine();
        gameEngine.getPanel().setPreferredSize(menuFrame.getSize());
        this.window = new GameWindow(menuFrame, gameEngine.getPanel());
    }

    // create level button
    public void createLevel() {
    }

    // quit button
    public void quit() {
        menuFrame.dispose();
    }
}
