package com.simplicity.GUI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class MainMenu implements ActionListener{
    Window window;
    RoundedButton playButton;
    RoundedButton loadButton;
    RoundedButton quitButton;
    public MainMenu(){
        window = new Window();
        window.setTitle("Welcome to Simplicity");
        window.getContentPane().setBackground(new Color(166, 227, 41));
        
        ImageIcon simplicityIcon = new ImageIcon("Tugas-Besar-IF2212-Pemrograman-Berorientasi-Objek-STI/Game/src/com/simplicity/Images/sims_diamond.png");
        Image newSimIcon = simplicityIcon.getImage().getScaledInstance(30, 60, java.awt.Image.SCALE_SMOOTH);
        simplicityIcon = new ImageIcon(newSimIcon);
    
        JLabel menuTitle = new JLabel();
        menuTitle.setText("Simplicity");
        menuTitle.setIcon(simplicityIcon);
        menuTitle.setHorizontalTextPosition(JLabel.CENTER);
        menuTitle.setVerticalTextPosition(JLabel.TOP);
        menuTitle.setBounds(500, 100, 300, 200);
        menuTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
    
        playButton = new RoundedButton("Play Game");
        playButton.setBounds(515, 300, 200, 100);
        playButton.setBackground(new Color(33, 185, 107));
        playButton.setFocusable(false);
        playButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        playButton.addActionListener(this);

        loadButton = new RoundedButton("Load Game");
        loadButton.setBounds(515, 450, 200, 100);
        loadButton.setBackground(new Color(33, 185, 107));
        loadButton.setFocusable(false);
        loadButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        loadButton.addActionListener(this);

        quitButton = new RoundedButton("Quit Game");
        quitButton.setBounds(515, 600, 200, 100);
        quitButton.setBackground(new Color(33, 185, 107));
        quitButton.setFocusable(false);
        quitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        quitButton.addActionListener(this);

        window.add(menuTitle);
        window.add(playButton);
        window.add(loadButton);
        window.add(quitButton);
    
        window.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton){
            window.dispose();
            new WorldView();
        }
        else if (e.getSource() == loadButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("Tugas-Besar-IF2212-Pemrograman-Berorientasi-Objek-STI/Game/src/com/simplicity"));

            int response = fileChooser.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
        }
        else if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }
}

