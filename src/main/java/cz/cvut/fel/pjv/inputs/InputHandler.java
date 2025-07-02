package cz.cvut.fel.pjv.inputs;


import cz.cvut.fel.pjv.main.*;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener {
    private GamePanel gamePanel;

    private boolean left, right, jump, attack, inventory = false, buttonClicked = false;

    private int buttonX = 1280/2-(100/2);
    private int buttonY = 800/2-((40/2)-170);
    private int buttonWidth = 100;
    private int buttonHeight = 40;

    public InputHandler(GamePanel gamePanel){
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
    public boolean isLeft() {return left;}
    public boolean isRight() {return right;}
    public boolean isJump() {return jump;}

    public boolean isAttack() { return attack;}
    public boolean isInventoryOpen() { return inventory;}
    public boolean isButtonClicked() { return buttonClicked;}
    public void setButtonClicked(boolean b) { buttonClicked = b; }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        buttonClicked = mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
                mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
    }


    // not used so far
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
