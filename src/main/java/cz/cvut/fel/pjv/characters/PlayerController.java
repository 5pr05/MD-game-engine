package cz.cvut.fel.pjv.characters;

import cz.cvut.fel.pjv.inputs.InputHandler;

public class PlayerController {
    private Player player;
    private Enemies[] enemies;
    private static InputHandler inputHandler;
    private double horizontalMovement = 1.5;
    private int jumpSpeed = 3;
    private boolean isJumping = false;
    private int jumpCounter = 0;
    private double initialYPosition;
    protected static boolean alive = true;
    protected boolean canAttack = true;

    public PlayerController(Player player, InputHandler inputHandler, Enemies[] enemies){
        this.player = player;
        this.inputHandler = inputHandler;
        this.initialYPosition = player.getYPosition();
        this.enemies = enemies;
    }

    // input handler setter
    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
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

    // kill player
    public static void kill(){
        alive = false;
        System.out.println("Player killed!");
        inputHandler = null;
    }
    public static boolean isAlive() {
        return alive;
    }

    // attack
    public void attack() {
        if (canAttack){
            for (Enemies enemy : enemies) {
                if (enemy != null) {
                    if ((Math.abs(enemy.getEnemiesXPosition() - player.getXPosition()) <= Player.attackRangeWeight) &&
                            (Math.abs(enemy.getEnemiesYPosition() - player.getYPosition()) <= Player.attackRangeHeight)) {
                        if (enemy.isAlive()) {
                            enemy.kill();
                        }
                    }
                }
            }
        }
    }

    // update player
    public void update() {
        if (inputHandler != null) {
            if (inputHandler.isLeft()) {
                moveHorizontally(-horizontalMovement);
            }
            if (inputHandler.isRight()) {
                moveHorizontally(horizontalMovement);
            }
            if (inputHandler.isJump()) {
                jump(jumpSpeed);
            }
            if (inputHandler.isAttack()) {
                attack();
            }
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
