package com.simplicity.Components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Dimension2D;
import com.simplicity.Point;
import com.simplicity.World;
import com.simplicity.Events.HousePickEvent;
import com.simplicity.Interfaces.Listeners.HousePickListener;

public class WorldPanel extends SimplicityPanel {
    private Color backgroundColor;
    private World world;

    public WorldPanel(World world) {
        super();
        backgroundColor = new Color(0x215e07);
        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(720, 720));
        this.setBorder(BorderFactory.createLineBorder(backgroundColor, 10));

        setWorld(world);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.removeAll();
        this.world = world;

        if (world != null) {
            Dimension2D worldDimension = world.getDimension();
            int x = worldDimension.getWidth();
            int y = worldDimension.getHeight();
            this.setLayout(new GridLayout(y, x, 2, 2));
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    JPanel housePanel;
                    housePanel = new HouseButton(new Point(i, j));
                    this.add(housePanel);
                }
            }
        } else {
            this.setLayout(new GridLayout(0, 0));
        }

        onUpdate();
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

        public void setButtonEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            if (isEnabled) {
                this.setBackground(enabledColor);
            } else {
                this.setBackground(disabledColor);
            }
            this.repaint();
        }

        public Point getPoint() {
            return location;
        }

        public boolean getButtonEnabled() {
            return isEnabled;
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
        for (Component button : this.getComponents()) {
            ((HouseButton)button).setListener(listener);
        }
    }

    @Override
    public void onUpdate() {
        for (Component c: this.getComponents()) {
            if (c instanceof HouseButton) {
                HouseButton button = (HouseButton)c;
                if (world.getSim(button.getPoint()) != null) {
                    if (!button.getButtonEnabled()) {
                        button.setButtonEnabled(true);
                    }
                } else if (button.getButtonEnabled()) {
                    button.setButtonEnabled(false);
                }
            }
        }

        this.revalidate();
        this.repaint();
    }
}
