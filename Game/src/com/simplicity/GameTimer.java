package com.simplicity;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer;  
    private int day;
    private int second;

    public static int gameTime;

    public GameTimer() {
        this.timer = new Timer();
        this.day = 1;
        this.second = 0;
    }

    public void setDay() {
        this.day = gameTime/720 + 1;
    }

    public void setSecond(){
        this.second = gameTime%720;
    }

    public int getDay() {
        return day;
    }

    public int getSecond(){
        return second;
    }

    public void start(int gameDuration) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                GameTimer.gameTime++;
                if(GameTimer.gameTime == gameDuration) {
                    stop();
                }
            }
        }, 1000, 1000); // mengatur interval timer untuk 1 detik
    }
    
    public void stop() {
        timer.cancel();
    }
    
    // public static void main(String[] args) {
    //     GameTimer game = new GameTimer();
    //     game.start(30); // durasi permainan selama 30 detik
    // }
}
