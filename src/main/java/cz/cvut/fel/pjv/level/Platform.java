package cz.cvut.fel.pjv.level;

public class Platform {
    private int xPosition, yPosition;
    private int width = 20, height = 20;

    public Platform(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // x position getter
    public int getxPosition() {
        return xPosition;
    }

    // y position getter
    public int getYPosition() {
        return yPosition;
    }

    // width getter
    public int getWidth() {
        return width;
    }

    // height getter
    public int getHeight() {
        return height;
    }
}
