package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.House;

public class HousePanel extends JPanel {
    private House currentHouse = null;
    private Color backgroundColor = Color.ORANGE;
    private JPanel houseLayout = new JPanel();
    private JLayeredPane layeredPane = new JLayeredPane();

    public HousePanel() {
        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout());

        houseLayout.setLayout(new GridBagLayout());
        houseLayout.setBackground(backgroundColor);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel roomPanel = new RoomPanel();
                c.gridx = j;
                c.gridy = i;
                c.insets = new Insets((i == 0 ? 6 : 3), (j == 0 ? 6 : 3), (i == 2 ? 6 : 3), (j == 2 ? 6 : 5));
                houseLayout.add(roomPanel, c);
            }
        }

        layeredPane.add(houseLayout, Integer.valueOf(0));
        this.add(layeredPane, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> {
            resizeHousePanel();
        });

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                resizeHousePanel();
            }
        });
    }

    public HousePanel(House firstHouse) {
        this();
        setHouse(firstHouse);
    }

    public void setHouse(House newHouse) {
        this.currentHouse = newHouse;
        this.revalidate();
        this.repaint();
    }

    private void resizeHousePanel() {
        houseLayout.setBounds(0, 0, getWidth(), getHeight());
        revalidate();
    }
}

