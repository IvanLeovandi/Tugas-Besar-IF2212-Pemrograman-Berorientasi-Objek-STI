package com.simplicity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.*;

import com.simplicity.Exceptions.OverlapingWorldObjectException;
import com.simplicity.Exceptions.PlacementOutOfBoundException;
import com.simplicity.Interfaces.WorldObject;

public class World extends JPanel {
    private ActualContainer actualContainer = new ActualContainer();
    private class ActualContainer extends JPanel implements MouseWheelListener {


        ActualContainer() {
            this.setLayout(new GridLayout(64, 64, 5, 5));
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getScrollAmount() > 0) {

            }
        }
    }
}
