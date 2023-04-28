package com.simplicity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.GUI.MainMenu;

public class SimplicityFrame extends JFrame {
    JPanel MainMenuPanel = new MainMenu();
    SimplicityFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 900);
        this.setResizable(false);



        this.add(MainMenuPanel);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new SimplicityFrame();
    }
}
