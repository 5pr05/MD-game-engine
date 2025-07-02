package cz.cvut.fel.pjv.characters;

import cz.cvut.fel.pjv.inputs.InputHandler;

public class PlayerController {
    private Player player;
    private InputHandler inputHandler;
    private double horizontalMovement = 1.5;
    private int jumpSpeed = 3;
    private boolean isJumping = false;
    private int jumpCounter = 0;
    private double initialYPosition;

    public PlayerController(Player player, InputHandler inputHandler){
        this.player = player;
        this.inputHandler = inputHandler;
        this.initialYPosition = player.getYPosition();
    }

    // move player horizontally
    public void moveHorizontally(double value){
        player.setXPosition(player.getXPosition() + value * horizontalMovement);
    }

    // player jumps
    public void jump(int value){
        if (!isJumping && player.getYPosition() == initialYPosition) {
            player.setYPosition(player.getYPosition() - value * jumpSpeed);
            isJumping = true;
            jumpCounter = 40;
        }
    }

    // update player
    public void update() {
        if (inputHandler.isLeft()) {
            moveHorizontally(-horizontalMovement);
        }
        if (inputHandler.isRight()) {
            moveHorizontally(horizontalMovement);
        }
        if (inputHandler.jump()) {
            jump(jumpSpeed);
        }
        if (isJumping && jumpCounter > 0) {
            player.setYPosition(player.getYPosition() - jumpSpeed);
            jumpCounter--;
        } else {
            isJumping = false;
            if (player.getYPosition() < initialYPosition) {
                player.setYPosition(player.getYPosition() + jumpSpeed);
            } else if (player.getYPosition() > initialYPosition) {
                player.setYPosition(player.getYPosition() - jumpSpeed);
            }
        }
    }

}