package cz.cvut.fel.pjv.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuRenderer {

    public JFrame setupMenuFrame() {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(1280, 800);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setResizable(false);
        return menuFrame;
    }

    // creating of menu buttons
    public void addButtonsToMenu(JFrame menuFrame, Menu menu) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/platform_sprite.png"));

        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 1280, 800);

        addButtonToPanel(panel, "Start Game", 950, 400, e -> menu.startGame(), "/button.png");
        addButtonToPanel(panel, "Create Level", 950, 460, e -> menu.createLevel(), "/button.png");
        addButtonToPanel(panel, "Quit", 950, 520, e -> menu.quit(), "/button.png");

        panel.add(background);

        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }


    // adding menu button to panel
    private void addButtonToPanel(JPanel panel, String text, int x, int y, ActionListener action, String iconPath) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 50);
        button.addActionListener(action);

        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        button.setIcon(icon);

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        panel.add(button);
    }

    // setup frame
    public JFrame setupLevelSelectionFrame() {
        JFrame levelSelectionFrame = new JFrame("Select Level");
        levelSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelSelectionFrame.setSize(1280, 800);
        levelSelectionFrame.setLocationRelativeTo(null);
        levelSelectionFrame.setResizable(false);
        return levelSelectionFrame;
    }

    // adding level button to panel
    private void addLevelButtonToPanel(JPanel panel, String text, int x, int y, ActionListener action, String iconPath) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 50);
        button.addActionListener(action);

        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        button.setIcon(icon);

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        panel.add(button);
    }

    // creating of level selection buttons
    public void addButtonsToLevelSelectionMenu(JFrame levelSelectionFrame, LevelSelectionMenu menu) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/guard_sprites.png"));

        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 1280, 800);

        addLevelButtonToPanel(panel, "Level 1", 100, 250, e -> menu.startLevel(1), "/button.png");
        addLevelButtonToPanel(panel, "Level 2", 300, 250, e -> menu.startLevel(2), "/button.png");

        panel.add(background);

        levelSelectionFrame.add(panel);
        levelSelectionFrame.setVisible(true);
    }
}
