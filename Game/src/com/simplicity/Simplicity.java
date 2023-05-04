package com.simplicity;

import com.simplicity.UI.MainMenu;
import com.simplicity.UI.SimplicityFrame;

public class Simplicity {
    public static void main(String[] args) {
        SimplicityManager manager = new SimplicityManager(new World(64, 64));
        manager.setMainMenuPanel(new MainMenu(manager));
        manager.setFrame(new SimplicityFrame(manager.getMainMenuPanel()));
        manager.startWindow();
    }
}
