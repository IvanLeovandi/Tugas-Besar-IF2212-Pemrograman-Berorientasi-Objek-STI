package com.simplicity.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Interfaces.GameListener;
import com.simplicity.Layouts.GamePanel;

public class SimplicityFrame extends JFrame {
    private JPanel currentPanel;
    public SimplicityFrame(JPanel firstPanel) {
        currentPanel = firstPanel;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);

        this.add(currentPanel);
    }

    public void setCurrentPanel(JPanel newPanel) {
        this.remove(currentPanel);
        currentPanel = newPanel;
        this.add(currentPanel);
        this.revalidate();
    }
}
