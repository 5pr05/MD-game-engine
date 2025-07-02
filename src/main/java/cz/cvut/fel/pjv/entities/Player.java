package cz.cvut.fel.pjv.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected static double hitboxWidth = 40, hitboxHeight = 64;
    protected static double attackRangeWidth = 100, attackRangeHeight = 84;
    private double horizontalMovement = 1.5;
    private int jumpSpeed = 3;
    private static boolean alive = true;
    private boolean canAttack = false;
    private boolean hyperRun = false;
    private int openedChests = 0;
    private boolean keyPicked;

    private int level;

    private double xPosition;
    private double yPosition;

    public Player(double xPosition, double yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // player x position getter
    public double getXPosition() {
        return this.xPosition;
    }

    // player hitbox width getter
    public double getHitboxWidth() {return hitboxWidth;}

    // player hitbox width getter
    public double getHitboxHeight() {return hitboxHeight;}

    // player attack arrange width getter
    public double getAttackRangeWidth() {return attackRangeWidth;}

    // player attack arrange width getter
    public double getAttackRangeHeight() {return attackRangeHeight;}

    // player x position setter
    public void setXPosition(double xPosition) {this.xPosition = xPosition;}

    // player y position getter
    public double getYPosition() {return this.yPosition;}

    // player y position setter
    public void setYPosition(double yPosition) {this.yPosition = yPosition;}

    // player horizontal movement step getter
    public double getHorizontalMovement(){return horizontalMovement;}

    // player horizontal movement step booster
    public void setHorizontalMovement() {
        horizontalMovement = 5;
        hyperRun = true;
    }

    // player jump speed getter
    public int getJumpSpeed(){return jumpSpeed;}

    // can attack setter
    public void setCanAttack(){canAttack = true;}

    // can attack getter
    public boolean getCanAttack(){return canAttack;}

    // hyper run getter
    public boolean getHyperRun(){return hyperRun;}

    // is player alive getter
    public static boolean isAlive() {return alive;}

    // kill player
    public void kill() {alive =false;}

    // open chest
    public void openChest(){openedChests++;}

    // opened chest getter
    public int getOpenedChests(){return openedChests;}

    // opened chest setter
    public void setOpenedChests(int openedChests) {this.openedChests = openedChests;}

    // key picked getter
    public boolean isKeyPicked(){return keyPicked;}

    // key picked setter
    public void pickKey(boolean key) {keyPicked = key;}

    public List<String> getInventory() {
        List<String> inventory = new ArrayList<>();
        if (canAttack) {
            inventory.add("Sword");
        }
        if (hyperRun) {
            inventory.add("Boots");
        }
        if (isKeyPicked()) {
            inventory.add("Key");
        }
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        if (inventory.contains("Sword")) {
            this.canAttack = true;
        }
        if (inventory.contains("Boots")) {
            setHorizontalMovement();
        }
        if (inventory.contains("Key")) {
            pickKey(true);
        }
    }
}