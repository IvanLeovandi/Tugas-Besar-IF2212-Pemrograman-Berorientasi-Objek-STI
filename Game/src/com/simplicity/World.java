package com.simplicity;

import java.util.*;

import com.simplicity.Exceptions.OverlapingWorldObjectException;
import com.simplicity.Exceptions.PlacementOutOfBoundException;
import com.simplicity.Interfaces.WorldObject;


public class World {
    private Dimension2D size;
    private Map<Point, House> map = new HashMap<>();

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
}
