package com.simplicity.Layouts;

import java.awt.*;

public class VerticalFlowLayout extends FlowLayout {

    public VerticalFlowLayout() {
        super(FlowLayout.CENTER, 0, 0);
        setVgap(5);
        setHgap(5);
    }

    public VerticalFlowLayout(int align, int hgap, int vgap) {
        super(align, hgap, vgap);
    }

    public Dimension preferredLayoutSize(Container target) {
        synchronized (target.getTreeLock()) {
            Dimension dim = super.preferredLayoutSize(target);
            int nMembers = target.getComponentCount();
            Insets insets = target.getInsets();
            dim.width = insets.left + insets.right + dim.width;
            dim.height = insets.top + insets.bottom + nMembers * (getVgap() + dim.height);
            Component[] components = target.getComponents();
            for (int i = 0; i < nMembers; ++i) {
                Dimension d = components[i].getPreferredSize();
                dim.width = Math.max(dim.width, d.width);
            }
            return dim;
        }
    }

    public Dimension minimumLayoutSize(Container target) {
        return preferredLayoutSize(target);
    }

    public void layoutContainer(Container target) {
        synchronized (target.getTreeLock()) {
            Insets insets = target.getInsets();
            int maxWidth = target.getWidth() - (insets.left + insets.right + getHgap() * 2);
            int x = insets.left + getHgap();
            int y = insets.top + getVgap();
            int rowHeight = 0;

            Component[] components = target.getComponents();
            int nMembers = components.length;

            for (int i = 0; i < nMembers; ++i) {
                Component m = components[i];

                if (m.isVisible()) {
                    Dimension d = m.getPreferredSize();
                    m.setSize(d.width, d.height);

                    if (y > insets.top + getVgap()) {
                        y += rowHeight + getVgap();
                    }

                    if (x + d.width > maxWidth) {
                        x = insets.left + getHgap();
                        y += rowHeight + getVgap();
                        rowHeight = 0;
                    }

                    m.setLocation(x, y);
                    x += d.width + getHgap();
                    rowHeight = Math.max(rowHeight, d.height);
                }
            }
        }
    }
}

