package com.simplicity.Actions;

import com.simplicity.Timer;
import com.simplicity.Sim;

public abstract class Action {
    private int duration;
    private Timer timer;
    private Sim sim;

    public Action(int duration) {
        this.duration = duration;
    }

    public void start(Sim sim) {
        this.sim = sim;
        timer = new Timer(duration) {
            @Override
            public void finish() {
                actionPerformed();
            }
        };
        timer.start();
    }

    public void stop() {
        if (timer != null) {
            timer.interrupt();
            timer = null;
        }
    }

    public abstract void actionPerformed();
}
