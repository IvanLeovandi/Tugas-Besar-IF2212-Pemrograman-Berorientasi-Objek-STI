package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Game extends JPanel {
    SideMenu sideMenu = new SideMenu();
    World world = new World();
    SideInfo sideInfo = new SideInfo();
    public Game() {
        this.setLayout(new BorderLayout());
        this.add(sideMenu, BorderLayout.WEST);
        this.add(world, BorderLayout.CENTER);
        this.add(sideInfo, BorderLayout.EAST);
    }

    private class SideMenu extends JPanel {
        public SideMenu() {
            this.setPreferredSize(new Dimension(280, 720));
            this.setLayout(new GridBagLayout());
            this.setBackground(new Color(0x9961f2));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;

            for (int i = 0; i < 10; i++) {
                JButton button = new JButton("aan " + (i+1));
                button.setPreferredSize(new Dimension(200, 40));
                gbc.gridy = i % 10;
                gbc.gridx = i / 10;
                this.add(button, gbc);
            }

            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }

    private class SideInfo extends JPanel {
        public SideInfo() {
            this.setLayout(new GridLayout(0, 1));
            this.setPreferredSize(new Dimension(280, 720));
            this.setBackground(new Color(0x9961f2));
        }
    }

    public SideMenu getSideMenu() {
        return sideMenu;
    }

    public SideInfo getSideInfo() {
        return sideInfo;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());
        frame.setBackground(Color.BLACK);

        Game gamePanel = new Game();

        frame.add(gamePanel);
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }
}
