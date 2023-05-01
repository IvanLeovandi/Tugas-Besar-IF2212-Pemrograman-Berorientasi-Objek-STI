package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.TestFrame;

public class CreateSimPanel extends JPanel {
    JLabel titleMessage = new JLabel("Input your sim name");
    JTextField input = new JTextField();

    public CreateSimPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        titleMessage.setBounds(0, 100, 500, 200);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(Box.createVerticalGlue(), gbc);

        gbc.gridy = 1;
        titleMessage.setPreferredSize(new Dimension(200, 30));
        this.add(titleMessage, gbc);

        gbc.gridy = 2;
        input.setPreferredSize(new Dimension(200, 30));
        this.add(input, gbc);

        gbc.gridy = 3;
        this.add(Box.createVerticalGlue(), gbc);
    }

    public static void main(String[] args) {
        TestFrame.start(new CreateSimPanel());
    }
}
