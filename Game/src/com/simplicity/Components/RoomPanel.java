package com.simplicity.Components;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;

import com.simplicity.House;
import com.simplicity.Point;
import com.simplicity.Room;
import com.simplicity.Interfaces.Collider;

public class RoomPanel extends SimplicityPanel implements Collider {
    private Room currentRoom = null;
    private JPanel roomLayout = new JPanel();
    private JLayeredPane layeredPane = new JLayeredPane();
    private Color backgroundColor = Color.BLACK;
    private ColliderInfo colliderInfo = new ColliderInfo(this);
    private boolean colliderActive = false;
    private Map<Point, JPanel> tileMap = new HashMap<>();
    private HousePanel housePanel;

    public RoomPanel(Room firstRoom, HousePanel housePanel) {
        super();
        this.housePanel = housePanel;
        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout());

        roomLayout.setLayout(new GridBagLayout());
        roomLayout.setBackground(backgroundColor);

        setRoom(firstRoom);

        this.add(layeredPane, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> {
            resizeRoomPanel();
        });

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                resizeRoomPanel();
            }
        });
        synchronized (SimplicityComponentHandler.colliderGlobalObjects) {
            SimplicityComponentHandler.colliderGlobalObjects.add(this);
        }
    }

    public RoomPanel() {
        this(null, null);
    }

    public void setHousePanel(HousePanel housePanel) {
        this.housePanel = housePanel;
    }

    public void setRoom(Room newRoom) {
        layeredPane.removeAll();
        this.currentRoom = newRoom;
        roomLayout.removeAll();
        if (currentRoom != null) {
            roomLayout.setBackground(backgroundColor);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    JPanel tilePanel = tileMap.get(new Point(j, i));

                    if (tileMap.get(new Point(j, i)) == null) {
                        tilePanel = new JPanel();
                        tilePanel.setBackground(Color.LIGHT_GRAY);
                    }

                    c.gridx = j;
                    c.gridy = i;
                    c.insets = new Insets((i == 0 ? 2 : 1), (j == 0 ? 2 : 1), (i == 5 ? 2 : 1), (j == 5 ? 2 : 1));
                    roomLayout.add(tilePanel, c);
                }
            }
        } else {
            roomLayout.setBackground(Color.ORANGE);
        }

        layeredPane.add(roomLayout, Integer.valueOf(0));
        this.revalidate();
        this.repaint();
    }



    private void resizeRoomPanel() {
        roomLayout.setBounds(0, 0, getWidth(), getHeight());
        revalidate();
    }

    public void shiftRoom(int deltaX, int deltaY) {
        if (this.housePanel != null) {
            housePanel.shiftRoom(deltaX, deltaY);
        }
    }

    @Override
    public void onCollisionEnter(Set<Collider> contacts) {
        if (colliderActive) {
            System.out.println("aan masuk");
        }
    }

    @Override
    public void onCollisionExit(Set<Collider> contacts) {
        if (colliderActive) {
            System.out.println("aan keluar");
        }
    }

    @Override
    public ColliderInfo getColliderInfo() {
        return colliderInfo;
    }

    @Override
    public void setColliderActive(boolean active) {
        colliderActive = active;
    }
}
