package com.simplicity.Layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.Room;

public class RoomPanel extends SimplicityPanel {
    private Room currentRoom = null;
    private JPanel roomLayout = new JPanel();
    private JLayeredPane layeredPane = new JLayeredPane();
    private Color backgroundColor = Color.BLACK;

    public RoomPanel() {
        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout());

        roomLayout.setLayout(new GridBagLayout());
        roomLayout.setBackground(backgroundColor);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JPanel tile = new JPanel();
                tile.setBackground(Color.LIGHT_GRAY);
                c.gridx = j;
                c.gridy = i;
                c.insets = new Insets((i == 0 ? 2 : 1), (j == 0 ? 2 : 1), (i == 5 ? 2 : 1), (j == 5 ? 2 : 1));
                roomLayout.add(tile, c);
            }
        }

        layeredPane.add(roomLayout, Integer.valueOf(0));
        this.add(layeredPane, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> {
            resizeRoomPanel();
        });

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                resizeRoomPanel();
            }
        });
    }

    public RoomPanel(Room firstRoom) {
        this();
        setRoom(firstRoom);
    }

    public void setRoom(Room newRoom) {
        this.currentRoom = newRoom;
        this.revalidate();
        this.repaint();
    }

    private void resizeRoomPanel() {
        roomLayout.setBounds(0, 0, getWidth(), getHeight());
        revalidate();
    }
}
