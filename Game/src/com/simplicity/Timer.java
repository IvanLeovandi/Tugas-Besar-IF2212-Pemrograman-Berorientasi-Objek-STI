package com.simplicity;

public class Timer extends Thread {
    private boolean repeats = false;
    private int interval = 16;
    private int durMilli;
    private boolean paused = false;
    private Object lock = new Object();

    public Timer(int durMilli) {
        this.durMilli = durMilli;
    }

    public Timer(int durMilli, int interval) {
        this(durMilli);
        this.interval = interval;
    }

    public Timer(int durMilli, int interval, boolean repeats) {
        this(durMilli, interval);
        this.repeats = repeats;
    }

    public Timer(int durMilli, boolean repeats) {
        this(durMilli);
        this.repeats = repeats;
    }

    public void pause() {
        paused = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public int getRemainingTime() {
        return durMilli;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void finish() {}

    @Override
    public void run() {
        synchronized (lock) {
            try {
                while (repeats) {
                    while (durMilli > 0) {
                        lock.wait();
                        Thread.sleep(interval);
                        if (!paused) {
                            durMilli -= interval;
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
