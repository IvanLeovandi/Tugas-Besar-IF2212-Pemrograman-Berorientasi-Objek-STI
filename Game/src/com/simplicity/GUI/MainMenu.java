package com.simplicity.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.util.*;

public class MainMenu extends JPanel {
    JPanel mainPanel = new MainPanel();
    JPanel helpPanel = new HelpPanel();

    public MainMenu() {
        this.add(mainPanel);
    }

    public void showHelp() {
        this.remove(mainPanel);
        this.add(helpPanel);
        revalidate();
        repaint();
    };

    public void showMain() {
        this.remove(helpPanel);
        this.add(mainPanel);
        revalidate();
        repaint();
    }

    private class MainPanel extends JPanel implements ActionListener {
        private ImageIcon simplicityIcon = new ImageIcon(new ImageIcon("Tugas-Besar-IF2212-Pemrograman-Berorientasi-Objek-STI/Game/src/com/simplicity/Images/sims_diamond.png").getImage().getScaledInstance(30, 60, Image.SCALE_SMOOTH));
        private JLabel titleLabel = new JLabel("SIMPLICITY");
        private JButton playButton = new RoundedButton("play game");
        private JButton loadButton = new RoundedButton("load");
        private JButton helpButton = new RoundedButton("help");
        private JButton quitButton = new RoundedButton("quit");

        public MainPanel() {

            this.setLayout(new GridLayout(10, 1));

            titleLabel.setIcon(simplicityIcon);
            titleLabel.setHorizontalTextPosition(JLabel.CENTER);
            titleLabel.setVerticalTextPosition(JLabel.TOP);
            titleLabel.setBounds(500, 100, 300, 200);
            titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));

            playButton.setBounds(515, 300, 200, 100);
            playButton.setBackground(new Color(33, 185, 107));
            playButton.setFocusable(false);
            playButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            playButton.addActionListener(this);

            loadButton.setBounds(515, 450, 200, 100);
            loadButton.setBackground(new Color(33, 185, 107));
            loadButton.setFocusable(false);
            loadButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            loadButton.addActionListener(this);

            helpButton.setBounds(515, 450, 200, 100);
            helpButton.setBackground(new Color(33, 185, 107));
            helpButton.setFocusable(false);
            helpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            helpButton.addActionListener(this);

            quitButton.setBounds(515, 600, 200, 100);
            quitButton.setBackground(new Color(33, 185, 107));
            quitButton.setFocusable(false);
            quitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            quitButton.addActionListener(this);

            this.add(titleLabel);
            this.add(playButton);
            this.add(loadButton);
            this.add(helpButton);
            this.add(quitButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton) {
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


