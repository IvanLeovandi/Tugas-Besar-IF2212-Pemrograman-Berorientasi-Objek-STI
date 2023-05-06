package com.simplicity;

import java.awt.event.KeyListener;

import com.simplicity.Components.SimplicityComponentHandler;

public class GameHandler implements Runnable {
    private Thread gameThread;
    private int FPS = 60;

    private int getFPS() {
        return FPS;
    }

    private void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public void startHandler() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            SimplicityComponentHandler.update();

            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
