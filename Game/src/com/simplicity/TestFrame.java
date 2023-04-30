package com.simplicity;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TestFrame extends JFrame {
    public TestFrame(JComponent c) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 900);
        this.add(c);
    }

    public static void start(JComponent c) {
        TestFrame frame = new TestFrame(c);
        frame.setVisible(true);
    }
}
