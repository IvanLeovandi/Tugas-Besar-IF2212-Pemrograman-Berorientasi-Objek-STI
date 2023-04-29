package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Interfaces.HousePickListener;
import com.simplicity.Point;
import com.simplicity.World;

import java.util.*;

public class WorldPanel extends JPanel {
    Color backgroundColor;
    World world;

    public WorldPanel(int x, int y, World world, HousePickListener listener) {
        this.world = world;
        System.out.println(world.getHouse(32, 32) == null);
        backgroundColor = new Color(0x215e07);

        this.setLayout(new GridLayout(x, y, 2, 2));
        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(720, 720));
        this.setBorder(BorderFactory.createLineBorder(backgroundColor, 10));
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                JPanel housePanel;
                if (world.getHouse(i, j) != null) {
                    housePanel = new HouseButton(listener, new Point(x, y), true);
                } else {

                    housePanel = new HouseButton(listener, new Point(x, y));
                }
                this.add(housePanel);
            }
        }
    }

    private class HouseButton extends JPanel implements MouseListener {
        private static Color disabledColor = new Color(0x7a6a00);
        private static Color enabledColor = new Color(0x32b80d);
        private static Color focusedColor = new Color(0x77cf5f);
        private boolean isEnabled = false;
        private HousePickListener listener;
        private Point location;

        public HouseButton(HousePickListener listener, Point location) {
            this.listener = listener;
            this.setBackground(disabledColor);
            this.location = location;
            this.addMouseListener(this);
        }

        public HouseButton(HousePickListener listener, Point location, boolean isEnabled) {
            this(listener, location);
            setEnabled(isEnabled);
        }

        public void setEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            if (isEnabled) {
                this.setBackground(enabledColor);
            } else {
                this.setBackground(disabledColor);
            }
            this.repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isEnabled) {
                listener.onHousePick(location);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (isEnabled) {
                this.setBackground(focusedColor);
                this.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (isEnabled) {
                this.setBackground(enabledColor);
            } else {
                this.setBackground(disabledColor);
            }
            this.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}
    }
}
