package com.simplicity.Exceptions;

import com.simplicity.Sim;

public class SimDeathException extends Exception {
    public SimDeathException(Sim sim) {
        super(sim.getName() + " died.");
    }

    public SimDeathException(String message) {
        super(message);
    }
}
