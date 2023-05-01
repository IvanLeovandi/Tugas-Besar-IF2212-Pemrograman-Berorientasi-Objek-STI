package com.simplicity.Layouts;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class SimplicityPanel extends JPanel {
    public SimplicityPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public SimplicityPanel(LayoutManager layout) {
        super(layout);
    }

    public SimplicityPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public SimplicityPanel() {
        super();
    }

    public void updateDisplay() {
        for (Component c: this.getComponents()) {
            if (c instanceof SimplicityPanel) {
                ((SimplicityPanel)c).updateDisplay();
            }
        }
    }
}
