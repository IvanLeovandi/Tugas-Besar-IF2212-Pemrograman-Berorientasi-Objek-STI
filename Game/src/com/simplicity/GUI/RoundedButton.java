package com.simplicity.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class RoundedButton extends JButton {
    private static final long serialVersionUID = 1L;
    private static final int ARC_WIDTH = 10;
    private static final int ARC_HEIGHT = 10;

    public RoundedButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(100, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        }
        else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        g2.dispose();
        super.paintComponent(g);
    }
}
