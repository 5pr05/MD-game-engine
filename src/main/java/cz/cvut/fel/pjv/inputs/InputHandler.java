package cz.cvut.fel.pjv.inputs;

import cz.cvut.fel.pjv.characters.*;
import cz.cvut.fel.pjv.main.GameModel;
import cz.cvut.fel.pjv.main.GamePanel;
import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener {
    private PlayerController playerController;
    private GamePanel gamePanel;
    private GameModel gameModel;

    private boolean left, right, jump, attack, inventory = false;

    private int buttonX = 1280/2-(100/2);
    private int buttonY = 800/2-((40/2)-170);
    private int buttonWidth = 100;
    private int buttonHeight = 40;

    public InputHandler(PlayerController playerController, GamePanel gamePanel, GameModel gameModel){
        this.playerController = playerController;
        this.gamePanel = gamePanel;
        this.gameModel = gameModel;
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
            case KeyEvent.VK_E:
                attack = true;
                break;
            case KeyEvent.VK_I:
                inventory = !inventory;
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
            case KeyEvent.VK_E:
                attack = false;
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
    public boolean isJump() {
        return jump;
    }
    public boolean isAttack() { return attack;}
    public boolean isInventoryOpen() { return inventory;}

    // not used so far
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {
        if (isInventoryOpen()) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
                    mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
                gameModel.createAbility();
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public int getButtonX() {return buttonX;}
    public int getButtonY() {return buttonY;}
    public int getButtonWidth() {return buttonWidth;}
    public int getButtonHeight() {return buttonHeight;}
}
