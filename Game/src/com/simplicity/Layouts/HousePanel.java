package com.simplicity.Layouts;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JPanel;

import com.simplicity.House;

public class HousePanel extends JPanel {
    private House currentHouse = null;

    public HousePanel() {

    }

    public HousePanel(House firstHouse) {
        this();
        setHouse(firstHouse);
    }

    public void setHouse(House newHouse) {

        this.revalidate();
        this.repaint();
    }
}
