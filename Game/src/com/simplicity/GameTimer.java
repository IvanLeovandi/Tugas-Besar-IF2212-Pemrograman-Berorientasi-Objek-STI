package com.simplicity;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer{
    private Timer timer;
    private int duration;  
    private int day;
    private int second;

    public static int gameTime = 0;

    public GameTimer() {
        this.timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTime++;
                if (gameTime == duration){
                    stopTimer();
                }
            }
        });
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

    public void startTimer(int gameDuration) {
        this.duration = gameDuration;
        int time = gameDuration - gameTime;
        timer.start();
        try {
            Thread.sleep(time*110);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setDay();
        setSecond();
    }
    public void stopTimer() {
        timer.stop();
    }
    
    // public static void main(String[] args) {
    //     GameTimer game = new GameTimer();
    //     game.startTimer(30); // durasi permainan selama 30 detik

    //     game.startTimer(40); // durasi permainan selama 10 detik

    //     System.out.println("Hasil "  + GameTimer.gameTime);
    // }
}
