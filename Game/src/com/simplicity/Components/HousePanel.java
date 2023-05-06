package com.simplicity.Components;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;

import com.simplicity.House;
import com.simplicity.Point;
import com.simplicity.Util.SimplicityUtilities;

public class HousePanel extends SimplicityPanel {
    private House currentHouse = null;
    private Color backgroundColor = Color.ORANGE;
    private JPanel houseLayout = new JPanel(new GridBagLayout());
    private JLayeredPane layeredPane = new JLayeredPane();
    private JScrollPane houseScrollPane = new JScrollPane(houseLayout);
    private Map<Point, RoomPanel> roomMap = new HashMap<>();

    public HousePanel(House firstHouse) {
        super();
        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout());

        houseLayout.setBackground(backgroundColor);
        houseScrollPane.setViewportView(layeredPane);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                RoomPanel roomPanel = new RoomPanel();
                roomMap.put(new Point(j, i), roomPanel);
                c.gridx = j;
                c.gridy = i;
                c.insets = new Insets((i == 0 ? 6 : 3), (j == 0 ? 6 : 3), (i == 2 ? 6 : 3), (j == 2 ? 6 : 5));
                houseLayout.add(roomPanel, c);
            }
        }

        layeredPane.add(houseLayout, Integer.valueOf(0));

        setHouse(firstHouse);

        this.add(houseScrollPane, BorderLayout.CENTER);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                resizeHousePanel();
            }
        });

        SwingUtilities.invokeLater(() -> {
            resizeHousePanel();
        });
    }

    public void setHouse(House newHouse) {
        this.currentHouse = newHouse;

        for (Component c: layeredPane.getComponentsInLayer(6)) {
            layeredPane.remove(c);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Point location = new Point(j - 1, i - 1);
                Point roomPanelPoint = new Point(j, i);
                RoomPanel roomPanel = roomMap.get(roomPanelPoint);
                if (currentHouse != null) {
                    roomPanel.setRoom(currentHouse.getRoomList().get(location));
                    roomPanel.setHousePanel(this);
                } else {
                    roomPanel.setRoom(null);
                }
            }
        }


        if (currentHouse != null) {
            SimPanel simShape = currentHouse.getHouseOwner().getShape();        // to be deleted
            simShape.setHousePanel(this);                                       // to be deleted
            layeredPane.add(simShape, Integer.valueOf(6));                      // to be deleted
        }

        this.revalidate();
        this.repaint();
    }

    private void resizeHousePanel() {
        houseLayout.setBounds(0, 0, getWidth(), getHeight());
        revalidate();
        repaint();
    }

    public void shiftRoom(int deltaX, int deltaY) {
        GridBagConstraints gbc = new GridBagConstraints();
        houseLayout.removeAll();
        Set<Point> keySet = roomMap.keySet();
        int x = 0, y = 0;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets((x == 0 ? 6 : 3), (y == 0 ? 6 : 3), (x == 2 ? 6 : 3), (y == 2 ? 6 : 5));
        houseLayout.add(keySet.toArray()[0], gbc);
        // for (Point p: keySet) {
        //     RoomPanel roomPanel = roomMap.get(p);
        //     int x = SimplicityUtilities.normalizeInt(p.getX() + deltaX, 0, 2);
        //     int y = SimplicityUtilities.normalizeInt(p.getY() + deltaY, 0, 2);
        //     System.out.println(roomPanel == null);
        //     p.setPoint(x, y);
        //     gbc.gridx = x;
        //     gbc.gridy = y;
        //     gbc.insets = new Insets((x == 0 ? 6 : 3), (y == 0 ? 6 : 3), (x == 2 ? 6 : 3), (y == 2 ? 6 : 5));
        //     houseLayout.add(roomPanel, gbc);
        //     System.out.println(x + ", " + y);
        // }

        revalidate();
        repaint();
    }

    public Map<Point, RoomPanel> getRoomMap() {
        return roomMap;
    }
}

