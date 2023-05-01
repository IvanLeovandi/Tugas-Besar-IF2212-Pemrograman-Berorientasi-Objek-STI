package com.simplicity.Events;

import com.simplicity.Point;
import com.simplicity.World;

public class HousePickEvent extends WorldEvent {
    Point point;

    public HousePickEvent(Object source, World world, Point point) {
        super(source, world);
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }
}
