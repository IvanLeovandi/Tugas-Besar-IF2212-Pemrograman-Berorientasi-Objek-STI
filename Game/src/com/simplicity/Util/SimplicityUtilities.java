package com.simplicity.Util;

import java.awt.Component;

import javax.swing.JComponent;

import com.simplicity.Interfaces.Collider;

public class SimplicityUtilities {
    public static void setActiveCollider(JComponent c, boolean active) {
        if (c instanceof Collider) {
            ((Collider)c).setColliderActive(active);
        }
        for (Component c2: c.getComponents()) {
            if (c2 instanceof JComponent) {
                setActiveCollider((JComponent)c2, active);
            }
        }
    }

    public static int normalizeInt(int x, int min, int max) {
        int scale = max - min + 1;
        int res = Integer.valueOf(x);

        if (max < min) {
            return x;
        }

        while (res > max) {
            res -= scale;
        }

        while (res < min) {
            res += scale;
        }

        return res;
    }
}
