package com.simplicity;

import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;

public class ZoomableJPanel extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener {
    private JPanel innerPanel;
    private Point2D zoomPoint;
    private double zoomFactor = 1.0;
    private boolean isDragging;
    private java.awt.Point dragStart;

    public ZoomableJPanel() {
        this.setBackground(Color.BLACK);
        innerPanel = new JPanel();
        innerPanel.setLayout(null);
        innerPanel.setBounds(0, 0, 400, 400);
        innerPanel.setPreferredSize(new Dimension(100, 100));
        this.add(innerPanel);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public void zoomIn() {
        zoomFactor *= 1.1;
        updateZoom();
    }

    public void zoomOut() {
        zoomFactor /= 1.1;
        updateZoom();
    }

    private void updateZoom() {
        java.awt.Point panelOrigin = innerPanel.getLocation();
        Point2D scaledZoomPoint = new Point2D.Double(zoomPoint.getX() / zoomFactor, zoomPoint.getY() / zoomFactor);
        Point2D zoomOffset = new Point2D.Double(panelOrigin.x - scaledZoomPoint.getX(), panelOrigin.y - scaledZoomPoint.getY());
        int innerWidth = (int) (getWidth() * zoomFactor);
        int innerHeight = (int) (getHeight() * zoomFactor);
        innerPanel.setBounds((int) zoomOffset.getX(), (int) zoomOffset.getY(), innerWidth, innerHeight);
        Component[] components = innerPanel.getComponents();
        for (Component c : components) {
            int x = (int) (c.getX() * zoomFactor);
            int y = (int) (c.getY() * zoomFactor);
            int width = (int) (c.getWidth() * zoomFactor);
            int height = (int) (c.getHeight() * zoomFactor);
            c.setBounds(x, y, width, height);
        }
        innerPanel.revalidate();
        innerPanel.repaint();
    }

    public JPanel getInnerPanel() {
        return innerPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            dragStart = e.getPoint();
            isDragging = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            isDragging = false;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomPoint = e.getPoint();
        if (e.getWheelRotation() < 0) {
            zoomIn();
        } else {
            zoomOut();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging) {
            java.awt.Point dragEnd = e.getPoint();
            int deltaX = dragEnd.x - dragStart.x;
            int deltaY = dragEnd.y - dragStart.y;
            Rectangle bounds = innerPanel.getBounds();
            bounds.translate(deltaX, deltaY);
            innerPanel.setBounds(bounds);
            dragStart = dragEnd;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ZoomableJPanel zoomableJPanel = new ZoomableJPanel();
        JPanel innerPanel = zoomableJPanel.getInnerPanel();

        JPanel testPanel = new JPanel();
        testPanel.setBackground(Color.RED);
        testPanel.setSize(100, 100);
        innerPanel.add(testPanel);
        zoomableJPanel.revalidate();;

        frame.add(zoomableJPanel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
