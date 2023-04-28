package com.simplicity.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
            String[] options = {"Leave without saving", "Save progress"};
            int responses = JOptionPane.showOptionDialog(null, "Do you want to leave", "Leaving soon?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 1);
            if(responses == 0) {
                window.dispose();
                new MainMenu();
            } else if (responses == 1){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("Tugas-Besar-IF2212-Pemrograman-Berorientasi-Objek-STI/Game/src/com/simplicity"));

                int response = fileChooser.showSaveDialog(null);
                if(response == JFileChooser.APPROVE_OPTION){
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    System.out.println(file);
                }
            }
        }
    }
}
