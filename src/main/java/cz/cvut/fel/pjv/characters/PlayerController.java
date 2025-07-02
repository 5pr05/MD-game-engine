package cz.cvut.fel.pjv.characters;

import cz.cvut.fel.pjv.inputs.InputHandler;
import cz.cvut.fel.pjv.main.*;

public class PlayerController {
    private static Player player;
    private static InputHandler inputHandler;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private static GameRenderer gameRenderer;
    private GameModel gameModel;

    private boolean onPlatform = false;
    private int jumpCounter = 0;

    private int groundYPosition = 300;
    private double initialYPosition;

    public PlayerController(Player player, InputHandler inputHandler, GameRenderer gameRenderer, GameModel gameModel) {
        this.player = player;
        this.inputHandler = inputHandler;
        this.gameRenderer = gameRenderer;
        this.gameModel = gameModel;
        this.initialYPosition = player.getYPosition();
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void moveHorizontally(double value) {
        double newXPosition = player.getXPosition() + value * player.getHorizontalMovement();
        if (gameModel.canMoveHorizontally(newXPosition)) {
            player.setXPosition(newXPosition);
        }
    }

    public void jump(int value) {
        if (!isJumping && !isFalling) {
            player.setYPosition(player.getYPosition() - value * player.getJumpSpeed());
            isJumping = true;
            isFalling = true;
            jumpCounter = 40;
        }
    }


    public void attack() { gameModel.attackEnemies();}

    public void update() {
        if (player.isAlive()) {
            if (inputHandler.isLeft()) {
                moveHorizontally(-player.getHorizontalMovement());
            }
            if (inputHandler.isRight()) {
                moveHorizontally(player.getHorizontalMovement());
            }
            if (inputHandler.isJump() && !isJumping && !isFalling) {
                jump(player.getJumpSpeed());
            }
            if (inputHandler.isAttack()) {
                attack();
            }
        }

        if (isJumping) {
            player.setYPosition(player.getYPosition() - player.getJumpSpeed());
            jumpCounter--;
            if (jumpCounter <= 0) {
                isJumping = false;
                isFalling = true;
            }
        } else if (isFalling) {
            player.setYPosition(player.getYPosition() + player.getJumpSpeed());
        }

        onPlatform = gameModel.isPlayerOnPlatform();
        if (onPlatform) {
            isFalling = false;
            isJumping = false;
            jumpCounter = 0;
        } else if (player.getYPosition() >= groundYPosition) {
            isFalling = false;
            isJumping = false;
            jumpCounter = 0;
            player.setYPosition(groundYPosition);
        } else {
            isFalling = true;
        }

        if (gameModel.isPlayerCollidingWithEnemy() == 0) {
            player.kill();
        }
    }
}
