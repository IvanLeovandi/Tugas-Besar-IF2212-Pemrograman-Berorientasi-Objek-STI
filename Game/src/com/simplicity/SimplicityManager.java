package com.simplicity;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

import com.simplicity.Components.GamePanel;
import com.simplicity.Events.SimCreateEvent;
import com.simplicity.Exceptions.InvalidSimName;
import com.simplicity.Exceptions.OverlapingHouseException;
import com.simplicity.Interfaces.Listeners.GameListener;
import com.simplicity.Interfaces.Listeners.SimCreateListener;
import com.simplicity.UI.SimplicityFrame;

public class SimplicityManager implements GameListener, SimCreateListener {
    private SimplicityFrame frame;
    private JPanel mainMenuPanel;
    private GamePanel gamePanel;
    private World world;
    private JPanel loadingPanel = new JPanel();
    private JLabel loadingTitle = new JLabel("LOADING...");
    private Sim currentSim = null;
    private GameHandler gameHandler = new GameHandler();

    SimplicityManager(World world) {
        this.world = world;
    }

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
        if (world == null) {
            world = new World(64, 64);
        }
        loadingTitle.setFont(new Font(null, Font.BOLD, 30));
        loadingTitle.setPreferredSize(new Dimension(1280, 720));
        loadingPanel.setBackground(Color.BLACK);
        loadingTitle.setForeground(Color.WHITE);
        loadingTitle.setHorizontalAlignment(JLabel.CENTER);

        loadingPanel.add(loadingTitle);
        frame.setCurrentPanel(loadingPanel);
        if (gamePanel == null) {
            gamePanel = new GamePanel(this);
        }

        SwingUtilities.invokeLater(() -> {
            frame.setCurrentPanel(gamePanel);
            gameHandler.startHandler();
        });
    }

    @Override
    public void stopPlay() {
        frame.setCurrentPanel(mainMenuPanel);
    }

    @Override
    public void onCreateSim(SimCreateEvent e) throws InvalidSimName {
        String name = e.getSimName();

        if (name.isEmpty()) {
            throw new InvalidSimName("Name can't be empty!");
        } else if (name.matches(".*[^a-zA-Z].*")) {
            throw new InvalidSimName("Name can only contain letters!");
        } else {
            Random rand = new Random();
            java.util.List<Point> availableLands = world.getAvailableLands();
            int x = availableLands.size();
            Point p = availableLands.get(rand.nextInt(x));
            Sim sim = new Sim(name, p);

            try {
                world.setSim(p, sim);
            } catch (OverlapingHouseException e1) {
                e1.printStackTrace();
            }

            gamePanel.displayWorld();
        }
    }
}
