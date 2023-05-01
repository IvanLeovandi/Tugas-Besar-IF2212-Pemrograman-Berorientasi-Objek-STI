package com.simplicity.Events;

import java.util.EventObject;

public class SimCreateEvent extends EventObject {
    String simName;

    public SimCreateEvent(Object source, String simName) {
        super(source);
        this.simName = simName;
    }

    public String getSimName() {
        return simName;
    }
}
