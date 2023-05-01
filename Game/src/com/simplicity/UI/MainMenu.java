package com.simplicity.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.GUI.RoundedButton;
import com.simplicity.Interfaces.Listeners.GameListener;
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
        private ImageIcon simplicityIcon = new ImageIcon(new ImageIcon(
                "Game/src/com/simplicity/Images/sims_diamond.png")
                .getImage().getScaledInstance(30, 60, Image.SCALE_SMOOTH));
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
            this.setBackground(new Color(0xa6e329));

            titlePanel.setOpaque(false);
            buttonPanel.setOpaque(false);

            titleLabel.setIcon(simplicityIcon);
            titleLabel.setHorizontalTextPosition(JLabel.RIGHT);
            titleLabel.setVerticalTextPosition(JLabel.TOP);
            titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            titleLabel.setForeground(Color.WHITE);

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
                if (response == JFileChooser.APPROVE_OPTION) {
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
            help1.setAlignmentX(CENTER_ALIGNMENT);
            JLabel help1Label = new JLabel(
                    "<html>" +
                            "<p>Pada saat pertama kali memulai permainan akan ditampilkan start screen. Pada start screen ini, pemain akan diberikan 4 opsi, yaitu 'Start Game', 'Load', 'Help', dan 'Quit'.<br/>"
                            +
                            "Berikut adalah penjelasan untuk setiap opsi yang ada : </p>" +
                            "<ul><li> Start Game : Pemain akan memulai game dengan world    baru. World hanya memiliki 1 Sims dan 1 Rumah. Sims akan memiliki Inventory yang berisi 'Single Bed', 'Toilet', 'Stove', 'Table and Chair', dan 'Clock'. </li>"
                            +
                            "<li>Load : Pemain akan diminta untuk memilih save file yang sudah pernah dibuat. Dunia simplicity akan terbentuk sesuai dengan save file yang sudah dibuat.</li>"
                            +
                            "<li>Help  : Pemain akan diberikan tampilan User Manual untuk membantu mengerti cara memainkan Simplicity.</li>"
                            +
                            "<li> Quit : Pemain akan keluar dari permainan Simplicity </li></ul></html>.");

            help1.add(help1Label);
            pages.add(help1);

            JPanel help2 = new JPanel();
            help2.setAlignmentX(CENTER_ALIGNMENT);
            JLabel help2Label = new JLabel(
                    "<html>Waktu dalam Simplicity sedikit berbeda dengan dunia asli. Sehari dalam Simplicity berlangsung selama 12 menit dan waktu hanya berjalan ketika melakukan suatu aksi. Aksi-aksi dalam  dapat dibedakan menjadi : "
                            +
                            "<ul><li>Aksi aktif, yaitu aksi yang membutuhkan waktu, tidak bisa ditinggal, dan mempengaruhi kondisi SIM. Contohnya :</li>"
                            +
                            "<ul><li>Makan (+x Kekenyangan / 1 siklus makan (30 detik) (x bergantung pada jenis makanan))</li>"
                            +
                            "<li> Masak (+10 mood per makanan yang dimasak)</li>" +
                            "<li> Buang Air (-20 kekenyangan dan + 10 mood / 1 siklus (10 detik))</li></ul>" +
                            "<li>Aksi  upgrade, yaitu aksi yang membutuhkan waktu, tetapi bisa ditinggal untuk melakukan aksi lain. Contohnya :</li>"
                            +
                            "<ul><li> Upgrade rumah</li>" +
                            "<li>Beli barang</li></ul>" +
                            "<li>Aksi pasif, yaitu aksi yang dapat dilakukan tanpa menggunakan waktu. Contohnya :</li>"
                            +
                            "<ul><li>Pindah ruangan</li>" +
                            "<li>Memasang baran</li>" +
                            "<li>Melihat jam </li></ul>" +
                            "<li>Aksi menambah Sim, yaitu aksi yang dapat digunakan satu hari sekali untuk melahirkan Sims baru</li></ul>"
                            +
                            "<br/>Terdapat juga beberapa aturan dalam Simplicity, yaitu :" +
                            "<ul><li>Sims dapat mengganti pekerjaan jika sudah bekerja 12 menit dan membayar setengah dari gaji baru</li>"
                            +
                            "<li>Mood dan kesehatan Sims akan menurun jika tidak tidur minimum 3 menit dalam sehari</li>"
                            +
                            "<li>Mood dan kesehatan Sims akan menurun jika tidak buang air 4 menit dari selesai makan</li></ul></html>");
            help2.add(help2Label);
            pages.add(help2);

            JPanel help3 = new JPanel();
            help3.setAlignmentX(CENTER_ALIGNMENT);
            JLabel help3Label = new JLabel(
                    "<html>Game Simplicity tidak memiliki tujuan akhir yang menjadi tanda selesainya game. Akan tetapi, game Simplicity dapat selesai dengan 2 cara, yaitu :"
                            +
                            "<ul><li>Semua Sims di dunia Simplicity sudah meninggal sehingga tidak ada karakter yang dapat dimainkan.</li>"
                            +
                            "<li>Keluar game Simplicity dengan melakukan Save untuk menyimpan progress yang sudah tercapai dan  game dapat dilanjutkan di waktu berikutnya.</html></li></ul>");
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
