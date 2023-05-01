package com.simplicity.Events;

import java.util.EventObject;

import com.simplicity.World;

public class WorldEvent extends EventObject {
    World world;

    public WorldEvent(Object source, World world) {
        super(source);
    }

    public World getWorld() {
        return world;
    }
}
