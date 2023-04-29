package com.simplicity.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.House;
import com.simplicity.Point;
import com.simplicity.World;
import com.simplicity.Interfaces.GameListener;
import com.simplicity.Interfaces.SimplicityManager;
import com.simplicity.Layouts.GamePanel;

public class Simplicity implements GameListener, SimplicityManager {
    SimplicityFrame frame;
    JPanel mainMenuPanel;
    GamePanel game;
    World world;

    public Simplicity() {
        Point p = new Point(32, 32);
        world = new World(64, 64);
        world.setHouse(p, new House(new Point(0, 0), null));
        System.out.println(world.getHouse(32, 32) == null);
        System.out.println(world.getHouse(p) == null);
        mainMenuPanel = new MainMenu(this);
        frame = new SimplicityFrame(mainMenuPanel);
    }

    public void start() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Simplicity game = new Simplicity();
        game.start();
    }

    @Override
    public void onPlay() {
        JPanel loadingPanel = new JPanel();
        JLabel loadingTitle = new JLabel("LOADING...");
        loadingTitle.setPreferredSize(new Dimension(1280, 720));
        loadingPanel.setBackground(Color.BLACK);
        loadingPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        loadingPanel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        loadingTitle.setForeground(Color.WHITE);
        loadingTitle.setVerticalAlignment(JLabel.CENTER);
        loadingTitle.setHorizontalAlignment(JLabel.CENTER);

        loadingPanel.add(loadingTitle);
        frame.setCurrentPanel(loadingPanel);

        System.out.println(world.getHouse(32, 32) == null);
        System.out.println((new Point(32, 32)).equals(new Point(32, 32)));
        game = new GamePanel(world);

        SwingUtilities.invokeLater(() -> {
            frame.setCurrentPanel(game);
        });
    }
}
