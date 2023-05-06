package com.simplicity.UI;

import javax.swing.*;

import com.simplicity.CurrentEventHandler;

public class SimplicityFrame extends JFrame {
    private JPanel currentPanel;

    public SimplicityFrame(JPanel firstPanel) {
        currentPanel = firstPanel;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.addKeyListener(CurrentEventHandler.getInstance());
        this.addFocusListener(CurrentEventHandler.getInstance());
        this.setFocusable(true);
        this.setResizable(false);

        this.add(currentPanel);
    }

    public void setCurrentPanel(JPanel newPanel) {
        this.remove(currentPanel);
        currentPanel = newPanel;
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
    }
}
