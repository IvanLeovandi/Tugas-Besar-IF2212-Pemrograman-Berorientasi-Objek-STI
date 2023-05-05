package com.simplicity.Components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.simplicity.CurrentEventHandler;

public class SimPanel extends SimplicityPanel {
    private boolean isSelected = false;
    private int playerSpeed = 2;
    private int x = 0;
    private int y = 0;
    private Container parent;

    public SimPanel() {
        super();
        this.setBackground(Color.red);
        this.setOpaque(true);
        this.setBounds(25, 25, 25, 25);
    }

    private void matchLocToParent() {
        if (parent == null && getParent() != null) {
            System.out.println("aana");
            parent = getParent();
            SimPanel thisPanel = this;
            SwingUtilities.invokeLater(new Runnable() {             // Multiple yields buat mundurin prioritas
                @Override
                public void run() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (SimplicityComponentHandler.simplicityPanelGlobalObjects) {
                                Dimension dimension = thisPanel.getSize();
                                Dimension parentSize = parent.getSize();
                                x = (int)(parentSize.getWidth()/2 - dimension.getWidth()/2 - 1);
                                y = (int)(parentSize.getHeight()/2 - dimension.getHeight()/2 - 1);
                                setLocation(x, y);
                            }
                        }
                    });
                }
            });
        }

        if (parent != null) {
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
}
