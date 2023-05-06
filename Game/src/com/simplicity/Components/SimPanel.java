package com.simplicity.Components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Set;
import javax.swing.Timer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.simplicity.CurrentEventHandler;
import com.simplicity.House;
import com.simplicity.Point;
import com.simplicity.Room;
import com.simplicity.Interfaces.Collider;

public class SimPanel extends SimplicityPanel implements Collider {
    private boolean isSelected = false;
    private int playerSpeed = 4;
    private int x = 0;
    private int y = 0;
    private Container parent;
    private ColliderInfo colliderInfo = new ColliderInfo(this);
    private boolean colliderActive = false;
    private HousePanel housePanel;

    public SimPanel(HousePanel housePanel) {
        super();
        setHousePanel(housePanel);
        this.setBackground(Color.red);
        this.setOpaque(true);
        this.setBounds(25, 25, 25, 25);
        synchronized (SimplicityComponentHandler.colliderGlobalObjects) {
            SimplicityComponentHandler.colliderGlobalObjects.add(this);
        }
    }

    public void setHousePanel(HousePanel housePanel) {
        this.housePanel = housePanel;
    }

    private void matchLocToParent() {
        if (parent == null && getParent() != null&& getParent().getSize().width > 0) {
            parent = getParent();
            SwingUtilities.invokeLater((() -> {
                synchronized (SimplicityComponentHandler.simplicityPanelGlobalObjects) {
                    Dimension dimension = SimPanel.this.getSize();
                    Dimension parentSize = parent.getSize();
                    x = (int)(parentSize.getWidth()/2 - dimension.getWidth()/2 - 1);
                    y = (int)(parentSize.getHeight()/2 - dimension.getHeight()/2 - 1);
                    setLocation(x, y);
                    revalidate();
                    repaint();
                }
            }));

        } else if (parent != null) {
            setLocation(x, y);
            revalidate();
            repaint();
        }
    }

    public void select() {
        isSelected = true;
    }

    public void deselect() {
        isSelected = false;
    }

    public void moveSprite(int x, int y) {
        this.x = getX() - x;
        this.y = getY() - y;
        matchLocToParent();
        revalidate();
        repaint();
    }

    @Override
    public void onUpdate() {
        int deltaX = 0, deltaY = 0;

        if (CurrentEventHandler.upPressed) {
            deltaY += playerSpeed;
        }

        if (CurrentEventHandler.downPressed) {
            deltaY -= playerSpeed;
        }

        if (CurrentEventHandler.leftPressed) {
            deltaX += playerSpeed;
        }

        if (CurrentEventHandler.rightPressed) {
            deltaX -= playerSpeed;
        }

        moveSprite(deltaX, deltaY);
    }

    @Override
    public void onCollisionEnter(Set<Collider> contacts) {
        if (colliderActive) {
            System.out.println("aan masuk");
            for (Collider c: contacts) {
                System.out.println(c.getClass().getName());
            }

            for (Collider c:SimplicityComponentHandler.colliderGlobalObjects) {
                System.out.println(c.getClass().getName());
            }
        }
    }

    @Override
    public void onCollisionExit(Set<Collider> contacts) {
        if (colliderActive) {
            if (housePanel != null) {
                Map<Point, RoomPanel> roomMap = housePanel.getRoomMap();
                RoomPanel centerRoom = roomMap.get(new Point(1, 1));
                if (contacts.contains(centerRoom)) {
                    System.out.println(this.getLocation() + ", " + parent.getSize() + ", " + this.getSize());
                    Set<Collider> currentContacts = colliderInfo.getContacts();
                    int deltaX = 0, deltaY = 0;

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (Math.abs(j - i) == 1 && currentContacts.contains(roomMap.get(new Point(i, j)))) {
                                deltaX += i - 1;
                                deltaY += j - 1;
                            }
                        }
                    }

                    Dimension dimension = centerRoom.getSize();
                    moveSprite((int)(dimension.getWidth() * deltaX), (int)(dimension.getHeight() * deltaY));
                    centerRoom.shiftRoom(deltaX, deltaY);
                }
            }
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
