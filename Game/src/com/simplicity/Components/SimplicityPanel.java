package com.simplicity.Components;

import java.util.ArrayList;
import java.awt.Component;
import java.awt.LayoutManager;

import java.awt.event.*;

import javax.swing.JPanel;

public class SimplicityPanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private static java.util.List<SimplicityPanel> globalObjects = SimplicityComponentHandler.simplicityPanelGlobalObjects;

    public SimplicityPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        synchronized(globalObjects) {
            globalObjects.add(this);
        }
    }

    public SimplicityPanel(LayoutManager layout) {
        super(layout);
        synchronized(globalObjects) {
            globalObjects.add(this);
        }
    }

    public SimplicityPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        synchronized(globalObjects) {
            globalObjects.add(this);
        }
    }

    public SimplicityPanel() {
        super();
        synchronized(globalObjects) {
            globalObjects.add(this);
        }
    }

    public void onUpdate() {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {}
}
