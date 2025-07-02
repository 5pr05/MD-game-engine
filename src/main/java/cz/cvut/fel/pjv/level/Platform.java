package cz.cvut.fel.pjv.level;

public class Platform {
    private int xPosition, yPosition;
    private int width, height;

    public Platform(int xPosition, int yPosition, int width, int height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
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
