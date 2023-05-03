package com.simplicity.Components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.SimplicityManager;
import com.simplicity.Events.SimCreateEvent;
import com.simplicity.Exceptions.InvalidSimName;

public class CreateSimPanel extends SimplicityPanel {
    JLabel titleMessage = new JLabel("Input your sim name");
    JTextField input = new JTextField();
    JButton doneButton = new JButton("Submit!");
    JLabel warningMessage = new JLabel("Invalid name! Name only contains of words only");
    Color invisColor = new Color(0x00000000, true);
    SimplicityManager manager;

    public CreateSimPanel(SimplicityManager manager) {
        super();
        this.manager = manager;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = input.getText();
                try {
                    manager.onCreateSim(new SimCreateEvent(this, name));
                    onValidInput();
                } catch (InvalidSimName e1) {
                    onInvalidInput(e1.getMessage());
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(Box.createVerticalGlue(), gbc);

        gbc.gridy = 1;
        gbc.weighty = 1;
        titleMessage.setBounds(0, 100, 500, 200);
        titleMessage.setPreferredSize(new Dimension(200, 30));
        titleMessage.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleMessage, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1;
        input.setPreferredSize(new Dimension(200, 30));
        this.add(input, gbc);

        gbc.gridy = 3;
        gbc.weighty = 1;
        warningMessage.setBounds(0, 100, 500, 200);
        warningMessage.setPreferredSize(new Dimension(500, 30));
        warningMessage.setForeground(invisColor);
        warningMessage.setHorizontalAlignment(JLabel.CENTER);
        this.add(warningMessage, gbc);

        gbc.gridy = 4;
        gbc.weighty = 2;
        this.add(Box.createVerticalGlue(), gbc);

        gbc.gridy = 5;
        gbc.weighty = 1;
        this.add(doneButton, gbc);

        gbc.gridy = 6;
        gbc.weighty = 5;
        this.add(Box.createVerticalGlue(), gbc);
    }

    public void showWarningMessage() {
        warningMessage.setForeground(Color.RED);
    }

    public void showWarningMessage(String message) {
        warningMessage.setText(message);
        showWarningMessage();
    }

    public void onValidInput() {
        warningMessage.setForeground(invisColor);
    }

    public void onInvalidInput(String message) {
        warningMessage.setText(message);
        warningMessage.setForeground(Color.RED);
    }
}
