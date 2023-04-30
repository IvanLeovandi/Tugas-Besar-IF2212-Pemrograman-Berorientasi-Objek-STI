package com.simplicity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.simplicity.House;
import com.simplicity.Point;
import com.simplicity.World;
import com.simplicity.Interfaces.GameListener;
import com.simplicity.Layouts.GamePanel;
import com.simplicity.UI.SimplicityFrame;

public class SimplicityManager implements GameListener {
    SimplicityFrame frame;
    JPanel mainMenuPanel;
    GamePanel gamePanel;
    World world;

    public void setFrame(SimplicityFrame newFrame) {
        frame = newFrame;
    }

    public SimplicityFrame getFrame() {
        return frame;
    }

    public void setMainMenuPanel(JPanel newMainMenuPanel) {
        mainMenuPanel = newMainMenuPanel;
    }

    public JPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public void setGamePanel(GamePanel newGamePanel) {
        gamePanel = newGamePanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setWorld(World newWorld) {
        world = newWorld;
    }

    public World getWorld() {
        return world;
    }

    public void startWindow() {
        frame.setVisible(true);
    }

    @Override
    public void onPlay() {
        JPanel loadingPanel = new JPanel();
        JLabel loadingTitle = new JLabel("LOADING...");
        loadingTitle.setFont(new Font(null, Font.BOLD, 30));;
        loadingTitle.setPreferredSize(new Dimension(1280, 720));
        loadingPanel.setBackground(Color.BLACK);
        loadingTitle.setForeground(Color.WHITE);
        loadingTitle.setHorizontalAlignment(JLabel.CENTER);

        loadingPanel.add(loadingTitle);
        frame.setCurrentPanel(loadingPanel);
        gamePanel = new GamePanel(world);

        SwingUtilities.invokeLater(() -> {
            frame.setCurrentPanel(gamePanel);
        });
    }
}
