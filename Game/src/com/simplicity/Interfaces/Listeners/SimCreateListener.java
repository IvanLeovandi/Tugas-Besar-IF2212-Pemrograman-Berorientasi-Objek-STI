package com.simplicity.Interfaces.Listeners;

import com.simplicity.Events.SimCreateEvent;
import com.simplicity.Exceptions.InvalidSimName;

public interface SimCreateListener extends SimplicityListener {
    public void onCreateSim(SimCreateEvent e) throws InvalidSimName;
}
