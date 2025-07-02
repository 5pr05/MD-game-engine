package cz.cvut.fel.pjv.inputs;

import cz.cvut.fel.pjv.characters.Player;
import cz.cvut.fel.pjv.characters.PlayerController;
import cz.cvut.fel.pjv.main.GamePanel;
import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener {
    private PlayerController playerController;
    private GamePanel gamePanel;
    private Player player;

    private boolean left, right, jump;

    public InputHandler(PlayerController playerController, GamePanel gamePanel){
        this.playerController = playerController;
        this.gamePanel = gamePanel;
    }

    // pressed keys
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_SPACE:
                jump = true;
                break;
        }
        gamePanel.repaint();
    }

    // released keys
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_SPACE:
                jump = false;
                break;
        }
    }

    // check if left or right is pressed
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
    public boolean jump() {
        return jump;
    }

    // not used so far
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
