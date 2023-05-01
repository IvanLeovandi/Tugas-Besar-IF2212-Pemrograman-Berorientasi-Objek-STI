package com.simplicity.Layouts;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Point;
import com.simplicity.SimplicityManager;
import com.simplicity.World;
import com.simplicity.Events.HousePickEvent;
import com.simplicity.Interfaces.HousePickListener;

import java.util.*;

public class GamePanel extends JPanel implements HousePickListener {
    SimplicityManager manager = new SimplicityManager();
    JPanel sideMenu = new SideMenu();
    JPanel currentCenterPanel;
    JPanel sideInfo = new SideInfo();
    JPanel loadingPanel = new JPanel();
    WorldPanel worldPanel;
    HousePanel housePanel = null;
    World world;

    public GamePanel(World world, SimplicityManager manager) {
        this.manager = manager;
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
        GridBagConstraints gbc;
        int compNum = 0;
        java.util.List<java.util.List<String>> buttonLists = new ArrayList<>();

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

            buttonLists.add(new ArrayList<>());
            this.addComponent(new MenuButton("ACTION"));
            this.addComponent(new MenuButton("VIEW INVENTORY"));
            this.addComponent(new MenuButton("UPGRADE HOUSE"));
            this.addComponent(new MenuButton("MOVE ROOM"));
            this.addComponent(new MenuButton("EDIT ROOM"));
            this.addComponent(new MenuButton("ADD SIM"));
            this.addComponent(new MenuButton("CHANGE SIM"));
            this.addComponent(new MenuButton("LIST OBJECT"));
            this.addComponent(new MenuButton("GO TO OBJECT"));
            this.addComponent(new MenuButton("BACK TO MAIN MENU"));
        }

        public void addComponent(JComponent c, boolean addToPrev) {
            if (c.getClass().isAssignableFrom(MenuButton.class) && addToPrev) {
                buttonLists.get(buttonLists.size() - 1).add(((MenuButton) c).getText());
            }
            gbc.gridy = compNum;
            gbc.gridx = 0;
            this.add(c, gbc);
            compNum++;
        }

        public void addComponent(JComponent c) {
            addComponent(c, true);
        }

        public void clearButtons(boolean undoable) {
            if (undoable) {
                buttonLists.add(new ArrayList<>());
            }
            this.removeAll();
            compNum = 0;
            this.repaint();
            this.revalidate();
        }

        public void clearButtons() {
            clearButtons(true);
        }

        public void printPrevButtons() {
            buttonLists.remove(buttonLists.size() - 1);
            int x = buttonLists.get(buttonLists.size() - 1).size();

            for (int i = 0; i < x; i++) {
                this.addComponent(new MenuButton(buttonLists.get(buttonLists.size() - 1).get(i)), false);
            }

            this.revalidate();
            this.repaint();
        }

        public void onButton(MenuButton e) {
            switch (e.getText()) {
                case "ACTION":
                    this.clearButtons();
                    this.addComponent(new MenuButton("WORK"));
                    this.addComponent(new MenuButton("WORKOUT"));
                    this.addComponent(new MenuButton("SLEEP"));
                    this.addComponent(new MenuButton("EAT"));
                    this.addComponent(new MenuButton("COOK"));
                    this.addComponent(new MenuButton("VISIT"));
                    this.addComponent(new MenuButton("DEFECATE"));
                    this.addComponent(new MenuButton("BUY"));
                    this.addComponent(new MenuButton("PLACE ITEM"));
                    this.addComponent(new MenuButton("VIEW TIME"));
                    this.addComponent(new MenuButton("BACK"));
                    this.revalidate();
                    this.repaint();
                    break;
                case "BACK TO MAIN MENU":
                    this.clearButtons();
                    String[] options = { "Leave without saving", "Save progress", "Cancel" };
                    int responses = JOptionPane.showOptionDialog(null, "Do you want to leave", "Leaving soon?",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 2);

                    if (responses == 0) {
                        manager.stopPlay();
                    } else if (responses == 2) {
                        this.printPrevButtons();
                    }
                    break;
                case "BACK":
                    this.clearButtons(false);
                    this.printPrevButtons();
                    break;
            }
        }

        private class MenuButton extends JButton {
            public MenuButton(String text) {
                this.setPreferredSize(new Dimension(200, 40));
                this.setText(text);
                this.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                        if (source.getClass().isAssignableFrom(MenuButton.class)) {
                            onButton((MenuButton) source);
                        }
                    }
                });
            }
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

    public JPanel getSideMenu() {
        return sideMenu;
    }

    public JPanel getSideInfo() {
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
        housePanel = (HousePanel) world.getHouse(location).getPanel();
        setCurrentCenterPanel(housePanel);
    }
}
