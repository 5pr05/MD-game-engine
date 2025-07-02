package cz.cvut.fel.pjv.menu;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class BaseMenu {

    protected JFrame setupFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        return frame;
    }

    protected void addButtonToPanel(JFrame frame, JPanel panel, String backgroundPath) {
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource(backgroundPath));
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 1280, 800);
        panel.add(background);

        frame.add(panel);
        frame.setVisible(true);
    }

    protected void addButton(JPanel panel, String text, int x, int y, int width, int height, ActionListener action, String iconPath) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(action);

        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        button.setIcon(icon);

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        panel.add(button);
    }
}
