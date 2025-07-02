package cz.cvut.fel.pjv.menu;

import javax.swing.*;

public class Menu extends BaseMenu {
    private JFrame menuFrame;
    private LevelSelectionMenu levelSelectionMenu;

    public Menu() {
        this.menuFrame = setupFrame("Menu");
        addButtonToPanel(menuFrame, createMenuPanel(), "/platform_sprite.png");
    }

    public void startGame() {
        this.levelSelectionMenu = new LevelSelectionMenu();
        menuFrame.setVisible(false);
    }

    public void createLevel() {
    }

    public void quit() {
        menuFrame.dispose();
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        addButton(panel, "Start Game", 950, 400, 200, 50, e -> startGame(), "/button.png");
        addButton(panel, "Create Level", 950, 460, 200, 50, e -> createLevel(), "/button.png");
        addButton(panel, "Quit", 950, 520, 200, 50, e -> quit(), "/button.png");

        return panel;
    }
}
