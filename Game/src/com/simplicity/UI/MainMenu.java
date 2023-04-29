package com.simplicity.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.GUI.RoundedButton;
import com.simplicity.Interfaces.GameListener;
import com.simplicity.Layouts.GamePanel;

import java.io.File;
import java.util.*;

public class MainMenu extends JPanel {
    JPanel mainPanel = new MainPanel();
    JPanel helpPanel = new HelpPanel();
    GameListener gameListener;

    public MainMenu(GameListener gameListener) {
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.gameListener = gameListener;
    }

    public void showHelp() {
        this.remove(mainPanel);
        this.add(helpPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    };

    public void showMain() {
        this.remove(helpPanel);
        this.add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private class MainPanel extends JPanel implements ActionListener {
        private ImageIcon simplicityIcon = new ImageIcon(new ImageIcon("Tugas-Besar-IF2212-Pemrograman-Berorientasi-Objek-STI/Game/src/com/simplicity/Images/sims_diamond.png").getImage().getScaledInstance(30, 60, Image.SCALE_SMOOTH));
        private JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 20));
        private JPanel titlePanel = new JPanel();
        private JLabel titleLabel = new JLabel("SIMPLICITY");
        private JButton playButton = new RoundedButton("play game");
        private JButton loadButton = new RoundedButton("load");
        private JButton helpButton = new RoundedButton("help");
        private JButton quitButton = new RoundedButton("quit");
        private Color buttonBackgroundColor = new Color(0x21b96b);

        public MainPanel() {
            this.setLayout(new BorderLayout(10, 10));
            this.setBackground(Color.BLUE);

            titlePanel.setOpaque(false);
            buttonPanel.setOpaque(false);

            titleLabel.setIcon(simplicityIcon);
            titleLabel.setHorizontalTextPosition(JLabel.CENTER);
            titleLabel.setVerticalTextPosition(JLabel.TOP);
            titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));

            playButton.setBackground(buttonBackgroundColor);
            playButton.setFocusable(false);
            playButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            playButton.addActionListener(this);

            loadButton.setBackground(buttonBackgroundColor);
            loadButton.setFocusable(false);
            loadButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            loadButton.addActionListener(this);

            helpButton.setBackground(buttonBackgroundColor);
            helpButton.setFocusable(false);
            helpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            helpButton.addActionListener(this);

            quitButton.setBackground(buttonBackgroundColor);
            quitButton.setFocusable(false);
            quitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            quitButton.addActionListener(this);

            JPanel playButtonPanel = new JPanel();
            playButtonPanel.add(playButton);
            playButtonPanel.setOpaque(false);

            JPanel loadButtonPanel = new JPanel();
            loadButtonPanel.add(loadButton);
            loadButtonPanel.setOpaque(false);

            JPanel helpButtonPanel = new JPanel();
            helpButtonPanel.add(helpButton);
            helpButtonPanel.setOpaque(false);

            JPanel quitButtonPanel = new JPanel();
            quitButtonPanel.add(quitButton);
            quitButtonPanel.setOpaque(false);

            buttonPanel.add(playButtonPanel);
            buttonPanel.add(loadButtonPanel);
            buttonPanel.add(helpButtonPanel);
            buttonPanel.add(quitButtonPanel);
            titlePanel.add(titleLabel);
            this.add(buttonPanel, BorderLayout.CENTER);
            this.add(titlePanel, BorderLayout.NORTH);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton) {
                gameListener.onPlay();
            } else if (e.getSource() == loadButton) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));

                int response = fileChooser.showOpenDialog(null);
                if(response == JFileChooser.APPROVE_OPTION){
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    System.out.println(file);
                }
            } else if (e.getSource() == helpButton && helpPanel != null) {
                showHelp();
            } else if (e.getSource() == quitButton) {
                System.exit(0);
            }
        }
    }

    private class HelpPanel extends JPanel implements ActionListener {
        private int currentPage = 1;
        private java.util.List<JPanel> pages = new ArrayList<>();

        private JPanel prevPanel;

        private JLabel pageNumLabel;
        private JButton prevButton = new JButton("prev");
        private JButton nextButton = new JButton("next");
        private JButton backButton = new JButton("back");

        private JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        private JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        private JPanel footerPanel = new JPanel(new GridLayout(1, 3));

        public HelpPanel() {
            this.setLayout(new BorderLayout());

            prevButton.addActionListener(this);
            nextButton.addActionListener(this);
            backButton.addActionListener(this);

            headerPanel.add(backButton);
            headerPanel.setPreferredSize(new Dimension(800, 100));
            this.add(headerPanel, BorderLayout.NORTH);

            JPanel help1 = new JPanel();
            JLabel help1Label = new JLabel("ini help 1");
            help1.add(help1Label);
            pages.add(help1);

            JPanel help2 = new JPanel();
            JLabel help2Label = new JLabel("ini help 2");
            help2.add(help2Label);
            pages.add(help2);

            JPanel help3 = new JPanel();
            JLabel help3Label = new JLabel("ini help 3");
            help3.add(help3Label);
            pages.add(help3);

            contentPanel.setPreferredSize(new Dimension(800, 700));
            contentPanel.add(pages.get(0));
            this.add(contentPanel, BorderLayout.CENTER);


            prevButton.setEnabled(false);
            if (pages.size() == 1) {
                nextButton.setEnabled(false);
            }
            pageNumLabel = new JLabel(currentPage + " / " + pages.size());
            footerPanel.add(prevButton);
            footerPanel.add(pageNumLabel);
            footerPanel.add(nextButton);
            footerPanel.setPreferredSize(new Dimension(800, 100));
            this.add(footerPanel, BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == prevButton) {
                contentPanel.add(pages.get(currentPage - 2));
                contentPanel.remove(pages.get(currentPage - 1));
                revalidate();
                repaint();
                currentPage--;
                if (currentPage == 1) {
                    prevButton.setEnabled(false);
                }
                if (!nextButton.isEnabled()) {
                    nextButton.setEnabled(true);
                }
                pageNumLabel.setText(currentPage + " / " + pages.size());
            } else if (e.getSource() == nextButton) {
                contentPanel.add(pages.get(currentPage));
                contentPanel.remove(pages.get(currentPage - 1));
                revalidate();
                repaint();
                currentPage++;
                if (currentPage == pages.size()) {
                    nextButton.setEnabled(false);
                }
                if (!prevButton.isEnabled()) {
                    prevButton.setEnabled(true);
                }
                pageNumLabel.setText(currentPage + " / " + pages.size());
            } else if (e.getSource() == backButton) {
                showMain();
            }
        }
    }
}


