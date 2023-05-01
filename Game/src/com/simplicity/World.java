package com.simplicity;

import java.util.*;

import javax.swing.JPanel;

import com.simplicity.Exceptions.PlacementOutOfBoundException;
import com.simplicity.Interfaces.SimplicityPrintable;
import com.simplicity.Interfaces.WorldObject;
import com.simplicity.Layouts.SimplicityPanel;
import com.simplicity.Layouts.WorldPanel;


public class World implements SimplicityPrintable {
    private Dimension2D size;
    private Map<Point, Sim> map = new HashMap<>();
    private WorldPanel panel;

    public static GameTimer gameTimer = new GameTimer();

    public World(int width, int length) {
        size = new Dimension2D(width, length);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                map.put(new Point(i, j), null); // butuh perubahan
            }
        }
    }

    public void setSim(int x, int y, Sim sim) {
        setSim(new Point(x, y), sim);
    }

    public void setSim(Point location, Sim sim) {
        map.put(location, sim);
        updatePanel();
    }

    public void removeSim(int x, int y) {
        removeSim(new Point(x, y));
    }

    public void removeSim(Point location) {
        map.remove(location);
        updatePanel();
    }

    public Sim getSim(int x, int y) {
        return getSim(new Point(x, y));
    }

    public Sim getSim(Point p) {
        return map.get(p);
    }

    public void checkUpgrade(int duration){
        for (Sim sim : map.values()){
            if (sim.getIsUpgradeHouse().getFirst().equals(true)){
                int x = sim.getIsUpgradeHouse().getSecond() -  duration;
                if (x <= 0){
                    sim.setIsUpgradeHouse(false, 0);
                    Point upgradeRoom = sim.getHouse().getUpgradeState().getFirst();
                    String direction = sim.getHouse().getUpgradeState().getSecond();
                    String name = sim.getHouse().getUpgradeState().getThird();
                    sim.getHouse().upgradeRoom(sim.getHouse().getRoomList().get(upgradeRoom), direction, name);
                }
                else{
                    sim.setIsUpgradeHouse(true, x);
                }
            }
        }
    }

    public void updatePanel() {
        if (this.panel != null) {
            panel.updateDisplay();
        }
    }

    @Override
    public WorldPanel getPanel() {
        if (panel == null) {
            panel = new WorldPanel(64, 64, this);
        }

        return panel;
    }

    @Override
    public void clearPanel() {
        panel = null;
    }
}
