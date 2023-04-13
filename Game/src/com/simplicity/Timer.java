package com.simplicity;

public class Timer extends Thread {
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

    public void pause() {
        paused = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void finish() {}

    @Override
    public void run() {
        synchronized (lock) {
            try {
                while (durMilli > 0) {
                    lock.wait();
                    Thread.sleep(interval);
                    if (!paused) {
                        durMilli -= interval;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
