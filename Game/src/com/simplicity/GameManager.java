package com.simplicity;

import java.util.*;

public class GameManager {
    private static GameManager instance = new GameManager();
    private Set<Sim> sims = new HashSet<>();
    private World world = new World(64, 64);

    public static GameManager getInstance() {
        return instance;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void addSim(Sim sim) {
        sims.add(sim);
    }

    public boolean removeSim(Sim sim) {
        if (sims.contains(sim)) {
            sims.remove(sim);
            return true;
        } else {
            return false;
        }
    }

    public Set<Sim> getSims() {
        return sims;
    }
}
