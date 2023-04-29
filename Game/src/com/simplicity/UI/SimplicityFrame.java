package com.simplicity.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Interfaces.GameListener;
import com.simplicity.Layouts.GamePanel;

public class SimplicityFrame extends JFrame implements GameListener {
    JPanel MainMenuPanel = new MainMenu(this);
    GamePanel game;
    SimplicityFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);

        this.add(MainMenuPanel);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new SimplicityFrame();
    }

    @Override
    public void onPlay() {
        game = new GamePanel();
        this.remove(MainMenuPanel);
        this.add(game);
        this.revalidate();
    }
}
