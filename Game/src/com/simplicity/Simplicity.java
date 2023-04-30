package com.simplicity;

import java.util.Random;

import com.simplicity.UI.MainMenu;
import com.simplicity.UI.SimplicityFrame;

public class Simplicity {
    public static void main(String[] args) {
        Random rand = new Random();

        SimplicityManager manager = new SimplicityManager();
        Point p = new Point(rand.nextInt(64), rand.nextInt(64));
        manager.setWorld(new World(64, 64));
        manager.getWorld().setHouse(p, new House(p));
        manager.setMainMenuPanel(new MainMenu(manager));
        manager.setFrame(new SimplicityFrame(manager.getMainMenuPanel()));
        manager.startWindow();
    }
}
