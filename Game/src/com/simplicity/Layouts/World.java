package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class World extends JPanel {

    public World() {
        this.setLayout(new GridLayout(64, 64, 2, 2));
        this.setBackground(new Color(0x215e07));
        this.setPreferredSize(new Dimension(720, 720));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        World aan = new World();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(720, 720));
        frame.setLayout(new BorderLayout());

        frame.add(aan, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
