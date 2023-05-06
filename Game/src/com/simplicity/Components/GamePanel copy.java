package com.simplicity.Components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Point;
import com.simplicity.SimplicityManager;
import com.simplicity.Events.HousePickEvent;
import com.simplicity.Exceptions.UndefinedHousePanelException;
import com.simplicity.Interfaces.Listeners.HousePickListener;

import java.util.*;

public class GamePanel extends SimplicityPanel implements HousePickListener {
    SimplicityManager manager;
    JPanel sideMenu = new SideMenu();
    JPanel currentCenterPanel;
    JPanel sideInfo = new SideInfo();
    JPanel loadingPanel = new SimplicityPanel();
    WorldPanel worldPanel;
    HousePanel housePanel = null;
    CreateSimPanel createSimPanel;

    public GamePanel(SimplicityManager manager) {
        super();
        this.manager = manager;
        createSimPanel = new CreateSimPanel(manager);
        loadingPanel.setBackground(Color.BLACK);

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setForeground(Color.WHITE);
        loadingPanel.add(loadingLabel);

        worldPanel = manager.getWorld().getPanel();
        worldPanel.setHousePickListener(this);
        currentCenterPanel = createSimPanel;
        this.setLayout(new BorderLayout());
        this.add(sideMenu, BorderLayout.WEST);
        this.add(currentCenterPanel, BorderLayout.CENTER);
        this.add(sideInfo, BorderLayout.EAST);
    }

    private class SideMenu extends SimplicityPanel {
        GridBagConstraints gbc;
        int compNum = 0;
        boolean currentButtonVisible = true;
        java.util.List<java.util.List<String>> buttonList = new ArrayList<>();
        int currentButtonLayer = 0;
        boolean canWork, canWorkout, canSleep, canEat, canCook, canVisit, canDefecate, canNubes, canListenToMusic, canWatchTV, canTakeABath, canMeetup, canMissYou, canSayHello, canBuy, canPlaceItem, canViewItem, canBack, canViewInventory, canUpgradeHouse, canMoveRoom, canEditRoom, canAddSim, canChangeSim, canViewObjectList, canGoToObject, canBackToMainMenu;

        public SideMenu() {
            super();
            this.setPreferredSize(new Dimension(280, 720));
            this.setLayout(new GridBagLayout());
            this.setBackground(new Color(0x9961f2));
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = compNum;
            gbc.gridy = compNum;


            addButton("ACTION", 0);
            addButton("VIEW INVENTORY", 0);
            addButton("UPGRADE HOUSE", 0);
            addButton("MOVE ROOM", 0);
            addButton("EDIT ROOM", 0);
            addButton("ADD SIM", 0);
            addButton("CHANGE SIM", 0);
            addButton("LIST OBJECT", 0);
            addButton("GO TO OBJECT", 0);
            addButton("BACK TO MAIN MENU", 0);
        }

        public void addComponent(JComponent c) {
            gbc.weightx = 1;
            gbc.gridy = compNum % 11;
            gbc.gridx = compNum / 11;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            this.add(c, gbc);
            compNum++;
        }

        public void clearButtons() {
            this.removeAll();
            compNum = 0;
            this.repaint();
            this.revalidate();
        }

        public void setButtonsVisibility(boolean isVisible) {
            this.currentButtonVisible = isVisible;
            for (Component c : this.getComponents()) {
                c.setVisible(isVisible);
                this.repaint();
                this.revalidate();
            }
        }

        public boolean addButton(String text, int layer) {
            if (buttonList.size() - 1 < layer) {
                for (int i = buttonList.size(); i < layer + 1; i++) {
                    buttonList.add(new ArrayList<String>());
                }
            }

            java.util.List<String> currentButtonList = buttonList.get(currentButtonLayer);

            if (!currentButtonList.contains(text)) {
                MenuButton button = new MenuButton(text);
                if (!currentButtonVisible) {
                    button.setVisible(false);
                }
                this.addComponent(button);
                currentButtonList.add(text);
                return true;
            } else {
                return false;
            }
        }

        public boolean removeButton(String text) {
            java.util.List<String> currentButtonList = buttonList.get(currentButtonLayer);

            if (currentButtonList.contains(text)) {
                currentButtonList.remove(text);
                reprintButtons();
                return true;
            } else {
                return false;
            }
        }

        public void reprintButtons() {
            clearButtons();
            for (String text: buttonList.get(currentButtonLayer)) {
                this.addComponent(new MenuButton(text));
            }
        }

        public void onButton(MenuButton e) {
            switch (e.getText()) {
                case "ACTION":
                    this.clearButtons();
                    addButton("WORK", 1);
                    addButton("WORKOUT", 1);
                    addButton("SLEEP", 1);
                    addButton("EAT", 1);
                    addButton("COOK", 1);
                    addButton("VISIT", 1);
                    addButton("DEFECATE", 1);
                    addButton("NUBES", 1);
                    addButton("LISTEN TO MUSIC", 1);
                    addButton("WATCH TV", 1);
                    addButton("BATH", 1);
                    addButton("MEETUP", 1);
                    addButton("MISS YOU", 1);
                    addButton("SAY HELLO", 1);
                    addButton("BUY", 1);
                    addButton("PLACE ITEM", 1);
                    addButton("VIEW TIME", 1);
                    addButton("BACK", 1);
                    this.revalidate();
                    this.repaint();
                    break;

                case "WORK":

                    break;

                case "VIEW INVENTORY":
                    break;

                case "UPGRADE HOUSE":
                    break;

                case "MOVE ROOM":
                    break;

                case "EDIT ROOM":
                    break;

                case "ADD SIM":
                    break;

                case "CHANGE SIM":
                    break;

                case "LIST OBJECT":
                    break;

                case "GO TO OBJECT":
                    break;

                case "BACK TO MAIN MENU":
                    this.setButtonsVisibility(false);
                    String[] options = { "Leave without saving", "Save progress", "Cancel" };
                    int responses = JOptionPane.showOptionDialog(null, "Do you want to leave", "Leaving soon?",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 2);

                    if (responses == 0) {
                        manager.stopPlay();
                    } else if (responses == 2) {}
                    this.setButtonsVisibility(true);
                    break;
                case "BACK":
                    this.clearButtons();
                    break;
            }
        }

        private class MenuButton extends JButton {
            public MenuButton(String text) {
                this.setPreferredSize(new Dimension(200, 40));
                this.setText(text);
                this.setFocusable(false);
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

    private class SideInfo extends SimplicityPanel {
        JLabel context = new JLabel();
        GridBagConstraints gbc;
        JButton backToWorldButton = new JButton("BACK TO WORLD");

        public SideInfo() {
            super();
            context.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            context.setAlignmentY(TOP_ALIGNMENT);
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            this.setPreferredSize(new Dimension(280, 720));
            this.setBackground(new Color(0x9961f2));
            backToWorldButton.setPreferredSize(new Dimension(200, 40));
            backToWorldButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();
                    if (source.getClass().isAssignableFrom(JButton.class)) {
                        onButton((JButton) source);
                    }
                }
            });
        }

        public boolean isBackToWorld() {
            if (!currentCenterPanel.equals(worldPanel)) {
                return true;
            }
            return false;
        }

        public void showBackButton() {
            if (isBackToWorld()) {
                this.add(backToWorldButton, gbc);
            } else {
                this.remove(backToWorldButton);
                this.revalidate();
                this.repaint();
            }
        }

        public void onButton(JButton e) {
            switch (e.getText()) {
                case "BACK TO WORLD":
                    setCurrentCenterPanel(worldPanel);
            }
        }

    }

    public void setCurrentCenterPanel(JPanel newPanel) {
        this.remove(currentCenterPanel);
        currentCenterPanel = loadingPanel;
        this.add(currentCenterPanel);
        this.revalidate();
        this.repaint();

        SwingUtilities.invokeLater(() -> {
            this.remove(currentCenterPanel);
            currentCenterPanel = newPanel;
            this.add(currentCenterPanel);
            this.revalidate();
            this.repaint();
        });
    }

    public void displayHouse() throws UndefinedHousePanelException {
        if (housePanel == null) {
            throw new UndefinedHousePanelException();
        } else {
            setCurrentCenterPanel(housePanel);
        }
    }

    public void displayWorld() {
        setCurrentCenterPanel(worldPanel);
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

    public void setSideMenu(SimplicityPanel newPanel) {
        sideMenu = newPanel;
    }

    @Override
    public void onHousePick(HousePickEvent e) {
        Point location = e.getPoint();
        housePanel = (HousePanel) manager.getWorld().getSim(location).getHouse().getPanel();
        setCurrentCenterPanel(housePanel);
        ((SideInfo) sideInfo).showBackButton();
    }
}
