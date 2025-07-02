package cz.cvut.fel.pjv.menu;

import cz.cvut.fel.pjv.main.*;
import javax.swing.*;

public class Menu {
    private JFrame menuFrame;
    private LevelSelectionMenu levelSelectionMenu;

    public Menu(MenuRenderer renderer) {
        this.menuFrame = renderer.setupMenuFrame();
        renderer.addButtonsToMenu(menuFrame, this);
    }

    // start game button
    public void startGame() {
        this.levelSelectionMenu = new LevelSelectionMenu(new MenuRenderer());
        menuFrame.setVisible(false);
    }

    // create level button
    public void createLevel() {
    }

    // quit button
    public void quit() {
        menuFrame.dispose();
    }
}
