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
    SideMenu sideMenu = new SideMenu();
    JPanel currentCenterPanel;
    WorldPanel worldPanel;
    HousePanel housePanel = null;
    SideInfo sideInfo = new SideInfo();
    World world;

    public GamePanel(World world) {
        this.world = world;
        worldPanel = new WorldPanel(64, 64, world);
        worldPanel.setHousePickListener(this);
        currentCenterPanel = worldPanel;
        this.setLayout(new BorderLayout());
        this.add(sideMenu, BorderLayout.WEST);
        this.add(worldPanel, BorderLayout.CENTER);
        this.add(sideInfo, BorderLayout.EAST);
    }

    private class MenuButton extends JButton {
        public MenuButton(String text) {
            this.setPreferredSize(new Dimension(200, 40));
            this.setText(text);
            
        }
    }

    private class SideMenu extends JPanel {
        GridBagConstraints gbc;
        int compNum = 0;

        public SideMenu() {
            this.setPreferredSize(new Dimension(280, 720));
            this.setLayout(new GridBagLayout());
            this.setBackground(new Color(0x9961f2));
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = compNum;
            gbc.gridy = compNum;

            MenuButton actionButton = new MenuButton("ACTION");
            actionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("");
                }
            });
            this.addComponent(actionButton);

            MenuButton simInfoButton = new MenuButton("VIEW SIM INFO");
            this.addComponent(simInfoButton);

            MenuButton currentLocationButton = new MenuButton("VIEW CURRENT LOCATION");
            this.addComponent(currentLocationButton);

            MenuButton inventoryButton = new MenuButton("VIEW INVENTORY");
            this.addComponent(inventoryButton);

            MenuButton upgradeHouseButton = new MenuButton("UPGRADE HOUSE");
            this.addComponent(upgradeHouseButton);

            MenuButton moveRoomButton = new MenuButton("MOVE ROOM");
            this.addComponent(moveRoomButton);

            MenuButton editRoomButton = new MenuButton("EDIT ROOM");
            this.addComponent(editRoomButton);

            MenuButton addSimButton = new MenuButton("ADD SIM");
            this.addComponent(addSimButton);

            MenuButton changeSimButton = new MenuButton("CHANGE SIM");
            this.addComponent(changeSimButton);

            MenuButton listObjectButton = new MenuButton("LIST OBJECT");
            this.addComponent(listObjectButton);

            MenuButton goToObjectButton = new MenuButton("GO TO OBJECT");
            this.addComponent(goToObjectButton);
            
        }

        public void addComponent(JComponent c) {
            gbc.gridy = compNum % 11;
            gbc.gridx = compNum / 11;
            this.add(c, gbc);
            compNum++;
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
        currentCenterPanel = newPanel;
        this.add(currentCenterPanel);
        this.revalidate();
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

    @Override
    public void onHousePick(HousePickEvent e) {
        Point location = e.getPoint();
        housePanel = (HousePanel) world.getHouse(location).getPanel();
        setCurrentCenterPanel(housePanel);
    }
}
