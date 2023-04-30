package com.simplicity.UI;

import com.simplicity.House;
import com.simplicity.Point;
import com.simplicity.SimplicityManager;
import com.simplicity.World;

public class Simplicity {
    public static void main(String[] args) {
        SimplicityManager manager = new SimplicityManager();
        Point p = new Point(32, 32);
        manager.setWorld(new World(64, 64));
        manager.getWorld().setHouse(p, new House(p));
        manager.setMainMenuPanel(new MainMenu(manager));
        manager.setFrame(new SimplicityFrame(manager.getMainMenuPanel()));
        manager.startWindow();
    }
}
