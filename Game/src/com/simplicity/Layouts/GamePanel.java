package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Point;
import com.simplicity.World;
import com.simplicity.Events.HousePickEvent;
import com.simplicity.Interfaces.HousePickListener;

import java.util.*;

public class GamePanel extends JPanel implements HousePickListener {
    JPanel sideMenu = new SideMenu();
    JPanel currentCenterPanel;
    JPanel sideInfo = new SideInfo();
    JPanel loadingPanel = new JPanel();
    WorldPanel worldPanel;
    HousePanel housePanel = null;
    World world;

    public GamePanel(World world) {
        this.world = world;
        loadingPanel.setBackground(Color.BLACK);

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setForeground(Color.WHITE);
        loadingPanel.add(loadingLabel);

        worldPanel = new WorldPanel(64, 64, world);
        worldPanel.setHousePickListener(this);
        currentCenterPanel = worldPanel;
        this.setLayout(new BorderLayout());
        this.add(sideMenu, BorderLayout.WEST);
        this.add(worldPanel, BorderLayout.CENTER);
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

            for (int i = 0; i < 5; i++) {
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
        JLabel context = new JLabel();
        public SideInfo() {
            context.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            context.setAlignmentY(TOP_ALIGNMENT);
            this.setLayout(new GridLayout(0, 1));
            this.setPreferredSize(new Dimension(280, 720));
            this.setBackground(new Color(0x9961f2));
        }
    }

    public void setCurrentCenterPanel(JPanel newPanel) {
        this.remove(currentCenterPanel);
        currentCenterPanel = loadingPanel;
        this.add(currentCenterPanel);
        this.revalidate();

        SwingUtilities.invokeLater(() -> {
            this.remove(currentCenterPanel);
            currentCenterPanel = newPanel;
            this.add(currentCenterPanel);
            this.revalidate();
        });
    }

    public SideMenu getSideMenu() {
        return sideMenu;
    }

    public SideInfo getSideInfo() {
        return sideInfo;
    }

    public void getSidePanelText() {
        // TODO: isi
    }

    public void setSideMenu(JPanel newPanel) {
        sideMenu = newPanel;
    }

    @Override
    public void onHousePick(HousePickEvent e) {
        Point location = e.getPoint();
        housePanel = (HousePanel)world.getHouse(location).getPanel();
        setCurrentCenterPanel(housePanel);
    }
}
