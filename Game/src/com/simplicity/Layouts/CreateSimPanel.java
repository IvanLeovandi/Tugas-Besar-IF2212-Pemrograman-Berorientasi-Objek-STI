package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.TestFrame;

public class CreateSimPanel extends JPanel {
    JLabel titleMessage = new JLabel("Input your first sim name");
    JTextField input = new JTextField();

    public CreateSimPanel() {
        titleMessage.setBounds(0, 100, 500, 200);
        this.add(titleMessage);
        this.add(input);
    }

    public static void main(String[] args) {
        TestFrame.start(new CreateSimPanel());
    }
}
