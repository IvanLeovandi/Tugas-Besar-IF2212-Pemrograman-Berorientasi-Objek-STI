package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Interfaces.HousePickListener;
import com.simplicity.House;
import com.simplicity.Point;
import com.simplicity.World;
import com.simplicity.Events.HousePickEvent;
import com.simplicity.Events.WorldEvent;

import java.util.*;

public class WorldPanel extends JPanel {
    private Color backgroundColor;
    private World world;
    private HousePickListener housePickListener;

    public WorldPanel(int x, int y, World world) {
        backgroundColor = new Color(0x215e07);
        this.world = world;

        this.setLayout(new GridLayout(x, y, 2, 2));
        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(720, 720));
        this.setBorder(BorderFactory.createLineBorder(backgroundColor, 10));
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                JPanel housePanel;
                if (world.getHouse(i, j) != null) {
                    housePanel = new HouseButton(new Point(i, j), true);
                } else {

                    housePanel = new HouseButton(new Point(i, j));
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

        public HouseButton(Point location) {
            this.setBackground(disabledColor);
            this.location = location;
            this.addMouseListener(this);
        }

        public HouseButton(Point location, boolean isEnabled) {
            this(location);
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

        public void setListener(HousePickListener listener) {
            this.listener = listener;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isEnabled && listener != null) {
                listener.onHousePick(new HousePickEvent(this, world, location));
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

    public void setHousePickListener(HousePickListener listener) {
        housePickListener = listener;
        for (Component button : this.getComponents()) {
            ((HouseButton)button).setListener(listener);
        }
    }
}
