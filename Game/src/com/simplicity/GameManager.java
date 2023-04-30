package com.simplicity;

import java.util.*;

public class GameManager {
    private static GameManager instance = new GameManager();
    private Set<Sim> sims = new HashSet<>();

    public static GameManager getInstance() {
        return instance;
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
