package com.simplicity.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.UI.MainMenu;

import java.io.File;

public class WorldView implements ActionListener{
    Window window;
    RoundedButton backButton;
    public WorldView(){
        window = new Window();
        window.setTitle("Simplicity");

        GamePanel gamePanel = new GamePanel();

        backButton = new RoundedButton("Back");
        backButton.setBounds(10, 10, 100, 50);
        backButton.setBackground(new Color(33, 185, 107));
        backButton.setFocusable(false);
        backButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        backButton.addActionListener(this);

        window.add(gamePanel);
        window.add(backButton);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            window.dispose();
            new MainMenu(null);
        }
    }
}
