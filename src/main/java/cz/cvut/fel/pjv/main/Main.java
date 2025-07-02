package cz.cvut.fel.pjv.main;

import cz.cvut.fel.pjv.menu.*;

public class Main {
    public static void main(String[] args) {
        MenuRenderer renderer = new MenuRenderer();
        new Menu(renderer);
        //new GameEngine();
    }
}
