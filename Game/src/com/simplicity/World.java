package com.simplicity;

import java.util.*;

import javax.swing.JPanel;

import com.simplicity.Exceptions.OverlapingWorldObjectException;
import com.simplicity.Exceptions.PlacementOutOfBoundException;
import com.simplicity.Interfaces.SimplicityPrintable;
import com.simplicity.Interfaces.WorldObject;
import com.simplicity.Layouts.WorldPanel;


public class World implements SimplicityPrintable {
    private Dimension2D size;
    private Map<Point, House> map = new HashMap<>();
    private JPanel panel;
    private ArrayList<Sim> simList = new ArrayList<>();

    public static GameTimer gameTimer = new GameTimer();

    public World(int width, int length) {
        size = new Dimension2D(width, length);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                map.put(new Point(i, j), null); // butuh perubahan
            }
        }
    }

    public void setHouse(int x, int y, House house) {
        setHouse(new Point(x, y), house);
    }

    public void setHouse(Point location, House house) {
        map.put(location, house);
    }

    public void removeHouse(int x, int y) {
        removeHouse(new Point(x, y));
    }

    public void removeHouse(Point location) {
        map.remove(location);
    }

    public House getHouse(int x, int y) {
        return getHouse(new Point(x, y));
    }

    public House getHouse(Point p) {
        return map.get(p);
    }

    public void checkUpgrade(int duration){
        for (Sim sim : simList){
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

    

    @Override
    public JPanel getPanel() {
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
