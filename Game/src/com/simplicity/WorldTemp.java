package com.simplicity;

import java.util.*;

import com.simplicity.Exceptions.OverlapingWorldObjectException;
import com.simplicity.Exceptions.PlacementOutOfBoundException;
import com.simplicity.Interfaces.WorldObject;


public class WorldTemp {
    private Dimension2D size;
    private Map<Point, WorldObject> map = new HashMap<>();

    public static GameTimer gameTimer = new GameTimer();

    public WorldTemp(int width, int length) {
        size = new Dimension2D(width, length);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                map.put(new Point(i, j), null);
            }
        }
    }

    public void addContent(Point p, WorldObject wo) throws PlacementOutOfBoundException, OverlapingWorldObjectException {
        if (p.getX() < 0 || p.getY() < 0 || wo.getSize().getLength() + p.getX() > size.getLength() || wo.getSize().getWidth() + p.getY() > size.getWidth()) {
            throw new PlacementOutOfBoundException(wo);
        }

        int iMax = size.getWidth();
        int jMax = size.getLength();
        for (int i = 0; i < iMax; i++) {
            for (int j = 0; j < jMax; j++) {
                WorldObject objectCheck = map.get(new Point(i, j));
                if (objectCheck != null) {
                    throw new OverlapingWorldObjectException(wo, objectCheck);
                }
            }
        }

        for (int i = p.getX(); i < p.getX() + wo.getSize().getLength(); i++) {
            for (int j = p.getY(); j < p.getY() + wo.getSize().getWidth(); j++) {
                map.replace(new Point(i, j), wo);
            }
        }
    }

    public void removeContent(Point p) {}

    public void removeContent(WorldObject wo) {}

    public Dimension2D getSize() {
        return size;
    }

    public List<WorldObject> getObjects() {
        Set<WorldObject> pret = new HashSet<>(map.values());
        pret.remove(null);
        return new ArrayList<>(pret);
    }
}
