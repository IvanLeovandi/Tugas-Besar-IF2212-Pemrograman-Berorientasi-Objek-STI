package com.simplicity.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends JFrame {
    JButton button;

    public Window(){
        // button = new JButton();
        // button.setBounds(200,100,100,50);
        // button.addActionListener(this);
        // button.setText("Klik aku");
        // button.setFocusable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1200,900);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
